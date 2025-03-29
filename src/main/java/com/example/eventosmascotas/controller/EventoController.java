package com.example.eventosmascotas.controller;
import org.springframework.web.bind.annotation.*;
import com.example.eventosmascotas.service.EventoService;
import com.example.eventosmascotas.model.Evento;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventoController {
    private final EventoService eventoService;
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/eventos")
    public List<Evento> getEventos() {
        return eventoService.generarEventos();
    }

    @GetMapping("/eventos/{id}")
    public Evento getEvento(@PathVariable int id) {
        List<Evento> eventos = eventoService.generarEventos();
        for (Evento evento : eventos) {
            if (evento.getId() == id) {
                return evento;
            }
        }
        return null; // o lanzar una excepción si no se encuentra el evento
    }
    
}
