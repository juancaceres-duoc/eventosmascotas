package com.example.eventosmascotas.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventoNotFoundException extends RuntimeException {
    public EventoNotFoundException(Long id) {
        super("No se encontró el evento con id: " + id);
    }
    
}
