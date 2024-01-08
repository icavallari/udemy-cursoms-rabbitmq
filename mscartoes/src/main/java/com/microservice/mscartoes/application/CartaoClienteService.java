package com.microservice.mscartoes.application;

import com.microservice.mscartoes.domain.CartaoCliente;
import com.microservice.mscartoes.infra.repository.CartaoClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoClienteService {

    private final CartaoClienteRepository repository;

    public List<CartaoCliente> listarCartoesByCpf(String cpf){
        return repository.findByCpf(cpf);
    }

}
