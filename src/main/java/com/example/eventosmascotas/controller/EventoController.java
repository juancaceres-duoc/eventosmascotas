package com.example.eventosmascotas.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.eventosmascotas.service.EventoService;
import com.example.eventosmascotas.model.Evento;
import com.example.eventosmascotas.model.ResponseWrapper;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getEventos() {
        return eventoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Evento getEvento(@PathVariable Long id) {
        return eventoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Evento>> crearEvento (@RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.guardar(evento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>("Evento creado exitosamente", 1, List.of(nuevoEvento)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Evento>> actualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        Evento eventoActualizado = eventoService.actualizar(id, evento);
        return ResponseEntity.ok(new ResponseWrapper<>("Evento actualizado exitosamente", 1, List.of(eventoActualizado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Evento>> eliminarEvento(@PathVariable Long id) {
        eventoService.eliminar(id);
        return ResponseEntity.ok(new ResponseWrapper<>("Evento eliminado exitosamente", 1, null));
    }
    
}
