package br.com.mars.exploringmars.domain.exception;

public class UnprocessableEntityException extends RuntimeException {

    private final String code;

    public UnprocessableEntityException(String code) {
        this.code = code;
    }
}
