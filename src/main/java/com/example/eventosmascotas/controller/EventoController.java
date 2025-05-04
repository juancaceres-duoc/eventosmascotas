package com.example.eventosmascotas.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.eventosmascotas.hateoas.EventoModelAssembler;
import com.example.eventosmascotas.service.EventoService;
import com.example.eventosmascotas.model.Evento;
import com.example.eventosmascotas.model.ResponseWrapper;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private EventoModelAssembler eventoModelAssembler;

    @GetMapping
    public ResponseEntity<ResponseWrapper<CollectionModel<EntityModel<Evento>>>> getEventos() {
        List<Evento> eventos = eventoService.obtenerTodos();

        if (eventos.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(null);
        }

        List<EntityModel<Evento>> eventosModel = eventos.stream()
                .map(eventoModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Evento>> collection = CollectionModel.of(eventosModel,
            linkTo(methodOn(EventoController.class).getEventos()).withSelfRel());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseWrapper<>("Eventos obtenidos exitosamente", eventos.size(), List.of(collection)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<EntityModel<Evento>>> getEvento(@PathVariable Long id) {
        Evento evento = eventoService.obtenerPorId(id);     

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseWrapper<>("Evento obtenido exitosamente", 1, List.of(eventoModelAssembler.toModel(evento)))
        );
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<EntityModel<Evento>>> crearEvento (@RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.guardar(evento);       

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseWrapper<>("Evento creado exitosamente", 1, List.of(eventoModelAssembler.toModel(nuevoEvento))));  
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<EntityModel<Evento>>> actualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        Evento eventoActualizado = eventoService.actualizar(id, evento);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseWrapper<>("Evento actualizado exitosamente", 1, List.of(eventoModelAssembler.toModel(eventoActualizado))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Evento>> eliminarEvento(@PathVariable Long id) {
        eventoService.eliminar(id);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseWrapper<>("Evento eliminado exitosamente", 1, null));
    }
    
}
