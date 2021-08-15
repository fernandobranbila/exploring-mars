package br.com.mars.exploringmars.domain.exception;

public class GenericCodeException extends RuntimeException {

    private final String code;

    public GenericCodeException(String code) {
        super();
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
