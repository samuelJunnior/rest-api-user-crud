package br.com.samueljunnior.core.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;

public interface IMessageProperties extends Serializable {

    String key();

    String[] getArgs();

    IMessageProperties bind(String... args);

    default String message(){
        return  this.getArgs().length == 0 ?
                MessageSourceInstance.getInstance().message(this.key()) :
                MessageSourceInstance.getInstance().message(this.key(), this.getArgs());
    }

    default ResponseStatusException businessException() {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, this.message());
    }

    default ResponseStatusException businessException(Throwable cause) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, this.message(), cause);
    }

    default ResponseStatusException resourceNotFoundException() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, this.message());
    }

    default ResponseStatusException resourceNotFoundException(Throwable cause) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, this.message(), cause);
    }

}
