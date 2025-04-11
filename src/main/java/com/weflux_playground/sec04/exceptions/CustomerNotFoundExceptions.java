package com.weflux_playground.sec04.exceptions;

public class CustomerNotFoundExceptions  extends RuntimeException{

    private static final String MESSAGE="Customer[id=%d] not found";

    public CustomerNotFoundExceptions(Integer id){
        super(MESSAGE.formatted(id));

    }
}
