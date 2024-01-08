package com.microservice.msavaliadorcredito.application.ex;

import lombok.Getter;

public class ErroComunicacaoMicroservicesExecption extends Exception{

    @Getter
    private Integer status;

    public ErroComunicacaoMicroservicesExecption(String msg, Integer status){
        super(msg);
        this.status = status;
    }

}
