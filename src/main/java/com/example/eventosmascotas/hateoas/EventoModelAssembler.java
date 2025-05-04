package com.example.eventosmascotas.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.eventosmascotas.controller.EventoController;
import com.example.eventosmascotas.model.Evento; 

@Component
public class EventoModelAssembler implements RepresentationModelAssembler<Evento, EntityModel<Evento>> {

    @Override
    @NonNull
    public EntityModel<Evento> toModel(@NonNull Evento evento) {
        return EntityModel.of(
            evento,            
            linkTo(methodOn(EventoController.class).getEvento(evento.getId())).withSelfRel(),
            linkTo(methodOn(EventoController.class).getEventos()).withRel("all"),
            linkTo(methodOn(EventoController.class).actualizarEvento(evento.getId(), null)).withRel("update"),
            linkTo(methodOn(EventoController.class).eliminarEvento(evento.getId())).withRel("delete")            
        );
    }
}
