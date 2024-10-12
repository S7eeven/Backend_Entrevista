package com.steven.entrevista.springboot.springboot_entrevista.exception;

public class ResourceNotFoundException extends RuntimeException {
     public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
