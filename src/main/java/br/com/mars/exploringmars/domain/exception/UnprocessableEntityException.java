package br.com.mars.exploringmars.domain.exception;

public class UnprocessableEntityException extends GenericCodeException {

    public UnprocessableEntityException(String code) {
        super(code);
    }
}
