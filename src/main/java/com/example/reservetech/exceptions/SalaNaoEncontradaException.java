package com.example.reservetech.exceptions;

public class SalaNaoEncontradaException extends RuntimeException {
    public SalaNaoEncontradaException(String message) {
        super(message);
    }
}
