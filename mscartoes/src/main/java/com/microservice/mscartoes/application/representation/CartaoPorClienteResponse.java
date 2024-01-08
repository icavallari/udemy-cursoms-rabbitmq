package com.microservice.mscartoes.application.representation;

import com.microservice.mscartoes.domain.BandeiraCartao;
import com.microservice.mscartoes.domain.CartaoCliente;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartaoPorClienteResponse {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartaoPorClienteResponse fromModel(CartaoCliente model){
        return new CartaoPorClienteResponse(
                model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getLimite()
        );
    }


}
