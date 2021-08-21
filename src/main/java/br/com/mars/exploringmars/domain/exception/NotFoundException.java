package br.com.mars.exploringmars.domain.exception;

public class NotFoundException extends GenericCodeException {

    public NotFoundException(String code) {
        super(code);
    }
}
