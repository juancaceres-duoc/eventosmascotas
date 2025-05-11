package com.example.eventosmascotas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.example.eventosmascotas.service.EventoService;
import com.example.eventosmascotas.model.Evento;
import com.example.eventosmascotas.model.Participante;
import com.example.eventosmascotas.repository.EventoRepository;
import com.example.eventosmascotas.repository.ParticipanteRepository;

public class EventoServiceTest {
    private EventoService eventoService;
    private EventoRepository eventoRepository;
    private ParticipanteRepository participanteRepository;

    @BeforeEach
    public void setUp() {
        eventoRepository = mock(EventoRepository.class);
        participanteRepository = mock(ParticipanteRepository.class);
        eventoService = new EventoService(eventoRepository, participanteRepository);      
    }

    @Test
    public void testObtenerTodos() {     

        Evento evento1 = new Evento();
        evento1.setId(1L);
        evento1.setNombre("Evento 1");
        evento1.setDescripcion("Descripcion 1");
        evento1.setFecha(LocalDate.parse("2023-10-01"));
        evento1.setParticipantes(Arrays.asList(
            new Participante(1L, "Juan", "Soto", "juan@email.com", "Firulais"),
            new Participante(2L, "Ana", "Molina", "ana@email.com", "Cuchito")
            ));

        Evento evento2 = new Evento();
        evento2.setId(2L);
        evento2.setNombre("Evento 2");
        evento2.setDescripcion("Descripcion 2");
        evento2.setFecha(LocalDate.parse("2023-10-02"));
        evento2.setParticipantes(Arrays.asList(            
            new Participante(3L, "Luis", "Gomez", "luis@email.com", "Luna"),
            new Participante(4L, "Maria", "Lopez", "maria@email.com", "Rocco")
            ));

        when(eventoRepository.findAll()).thenReturn(List.of(evento1, evento2));
        List<Evento> resultado = eventoService.obtenerTodos();

        assertEquals(2, resultado.size());
        assertEquals("Evento 1", resultado.get(0).getNombre());
        assertEquals("Evento 2", resultado.get(1).getNombre());
    }

    @Test
    public void testObtenerPorId() {

        Evento evento = new Evento();
        evento.setId(1L);
        evento.setNombre("Evento 1");
        evento.setDescripcion("Descripcion 1");
        evento.setFecha(LocalDate.parse("2023-10-01"));
        evento.setParticipantes(Arrays.asList(
            new Participante(1L, "Pedro", "Arias", "pedro@email.com", "Luna"),
            new Participante(2L, "Sofia", "Martinez", "sofia@email.com", "Misifus")
            ));

        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
        Evento resultado = eventoService.obtenerPorId(1L);
       
        assertEquals("Evento 1", resultado.getNombre());
        assertEquals("Descripcion 1", resultado.getDescripcion());
        assertEquals("2023-10-01", resultado.getFecha().toString());
        assertEquals(2, resultado.getParticipantes().size());
        assertEquals("Pedro", resultado.getParticipantes().get(0).getNombre());
    }

    @Test
    public void testGuardar() {
        Evento evento = new Evento();
        evento.setId(1L);
        evento.setNombre("Evento 1");
        evento.setDescripcion("Descripcion 1");
        evento.setFecha(LocalDate.parse("2023-10-01"));
        List<Participante> participantes = Arrays.asList(
        new Participante(1L, "Pedro", "Arias", "pedro@gmail.com", "Luna"),
        new Participante(2L, "Sofia", "Martinez", "sofia@gmail.com", "Misifus")
        );
        evento.setParticipantes(participantes);
        
        when(participanteRepository.findAllById(any())).thenReturn(participantes);
        when(eventoRepository.save(evento)).thenReturn(evento);
        Evento resultado = eventoService.guardar(evento);

        assertEquals("Evento 1", resultado.getNombre());    
        assertEquals("Descripcion 1", resultado.getDescripcion());
        assertEquals("2023-10-01", resultado.getFecha().toString());
        assertEquals(2, resultado.getParticipantes().size());
        assertEquals("Pedro", resultado.getParticipantes().get(0).getNombre());     
        assertEquals("Sofia", resultado.getParticipantes().get(1).getNombre()); 
    }

    @Test
    public void testEliminar() {
        Evento evento = new Evento();
        evento.setId(1L);
        evento.setNombre("Evento 1");
        evento.setDescripcion("Descripcion 1");
        evento.setFecha(LocalDate.parse("2023-10-01"));
        evento.setParticipantes(Arrays.asList(
            new Participante(1L, "Pedro", "Arias", "pedro@gmail.com", "Luna"),
            new Participante(2L, "Sofia", "Martinez", "sofia@gmail.com", "Misifus")
            ));

        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
        eventoService.eliminar(1L);

        verify(eventoRepository, times(1)).delete(evento);
        verify(eventoRepository, times(1)).findById(1L);
    }

}
