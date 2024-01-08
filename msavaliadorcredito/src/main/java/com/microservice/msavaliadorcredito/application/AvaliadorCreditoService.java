package com.microservice.msavaliadorcredito.application;

import com.microservice.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import com.microservice.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesExecption;
import com.microservice.msavaliadorcredito.application.ex.ErroSolicitacaoCartaoException;
import com.microservice.msavaliadorcredito.domain.model.*;
import com.microservice.msavaliadorcredito.infra.clients.CartoesResourceClient;
import com.microservice.msavaliadorcredito.infra.clients.ClienteResourceClient;
import com.microservice.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesResource;
    private final CartoesResourceClient cartoesResource;
    private final SolicitacaoEmissaoCartaoPublisher solicitacaoEmissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesExecption {

        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesResource.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> dadosCartoes = cartoesResource.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(dadosCartoes.getBody())
                    .build();

        } catch (FeignException.FeignClientException e){
            int status = e.status();

            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }

            throw new ErroComunicacaoMicroservicesExecption(e.getMessage(), status);
        }

    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesExecption{

        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesResource.dadosCliente(cpf);
            ResponseEntity<List<Cartao>> dadosCartoesResponse = cartoesResource.getCartoesRendaAte(renda);

            List<Cartao> cartoes = dadosCartoesResponse.getBody();
            var listaCartoesAprovados = cartoes.stream()
                    .map(cartao -> {

                        DadosCliente dadosCliente = dadosClienteResponse.getBody();

                        BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                        BigDecimal limiteBasico = cartao.getLimiteBasico();
                        var fator = idadeBD.divide(BigDecimal.TEN);

                        CartaoAprovado aprovado = new CartaoAprovado();
                        aprovado.setCartao(cartao.getNome());
                        aprovado.setBandeira(cartao.getBandeira());
                        aprovado.setLimiteAprovado(fator.multiply(limiteBasico));

                        return aprovado;
                    })
                    .collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(listaCartoesAprovados);

        } catch (FeignException.FeignClientException e){
            int status = e.status();

            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }

            throw new ErroComunicacaoMicroservicesExecption(e.getMessage(), status);
        }

    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados){
        try {
            solicitacaoEmissaoCartaoPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);

        } catch (Exception e){
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }

}
