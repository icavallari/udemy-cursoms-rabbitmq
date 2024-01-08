package com.microservice.mscartoes.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Cartao {

    public Cartao(String nome,
                  BandeiraCartao bandeira,
                  BigDecimal renda,
                  BigDecimal limiteBasico) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;
    @Column(precision = 10, scale = 2)
    private BigDecimal renda;
    @Column(precision = 10, scale = 2)
    private BigDecimal limiteBasico;

}
