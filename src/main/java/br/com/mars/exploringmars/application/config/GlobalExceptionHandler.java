package br.com.mars.exploringmars.application.config;

import br.com.mars.exploringmars.domain.exception.BadRequestException;
import br.com.mars.exploringmars.domain.exception.GenericCodeException;
import br.com.mars.exploringmars.domain.exception.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity exception(GenericCodeException e){
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UnprocessableEntityException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity unprocessableEntityException(GenericCodeException e){
        return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity badRequestException(GenericCodeException e){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
