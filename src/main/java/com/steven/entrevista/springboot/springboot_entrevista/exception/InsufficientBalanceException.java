package com.steven.entrevista.springboot.springboot_entrevista.exception;

public class InsufficientBalanceException extends RuntimeException {
     public InsufficientBalanceException(String mensaje) {
        super(mensaje);
    }
}
