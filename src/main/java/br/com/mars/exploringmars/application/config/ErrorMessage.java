package br.com.mars.exploringmars.application.config;

public class ErrorMessage {

    private final String code;

    private final String message;

    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
