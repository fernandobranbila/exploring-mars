package br.com.mars.exploringmars.domain.exception;

public class BadRequestException extends GenericCodeException {

    public BadRequestException(String code) {
        super(code);
    }
}
