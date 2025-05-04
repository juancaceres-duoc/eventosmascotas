package com.example.eventosmascotas.service;
import com.example.eventosmascotas.model.Evento;
import com.example.eventosmascotas.model.Participante;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import com.example.eventosmascotas.repository.EventoRepository;
import com.example.eventosmascotas.repository.ParticipanteRepository;
import com.example.eventosmascotas.exception.EventoNotFoundException;

@Service
public class EventoService {
    
    private EventoRepository eventoRepository;    
    private ParticipanteRepository participanteRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> obtenerTodos() {
        return eventoRepository.findAll();
    }
    public Evento obtenerPorId(Long id) {
        return eventoRepository.findById(id).orElseThrow(()-> new EventoNotFoundException(id));
    }

    public Evento guardar(Evento evento) {
        // Si no se proporcionan participantes, generarlos aleatoriamente
        if (evento.getParticipantes() == null || evento.getParticipantes().isEmpty()) {
            evento.setParticipantes(generarParticipantesAleatorios());
        }
    
        // Obtener los IDs de los participantes
        List<Long> participanteIds = new ArrayList<>();
        for (Participante participante : evento.getParticipantes()) {
            participanteIds.add(participante.getId());
        }
    
        // Obtener los participantes desde la base
        List<Participante> participantes = participanteRepository.findAllById(participanteIds);
    
        // Asignar los participantes validados al evento
        evento.setParticipantes(participantes);
    
        // Guardar el evento
        return eventoRepository.save(evento);
    } 

    public void eliminar(Long id) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new EventoNotFoundException(id));
        eventoRepository.delete(evento);
    }

    public Evento actualizar(Long id, Evento evento) {
        Evento eventoExistente = eventoRepository.findById(id).orElseThrow(() -> new EventoNotFoundException(id));
    
        eventoExistente.setNombre(evento.getNombre());
        eventoExistente.setDescripcion(evento.getDescripcion());
        eventoExistente.setFecha(evento.getFecha());
    
        // Si se proporcionan participantes, actualizarlos
        if (evento.getParticipantes() != null) {
            List<Long> participanteIds = new ArrayList<>();
            for (Participante participante : evento.getParticipantes()) {
                participanteIds.add(participante.getId());
            }
    
            List<Participante> participantesActualizados = participanteRepository.findAllById(participanteIds);
            eventoExistente.setParticipantes(participantesActualizados);
        }
    
        return eventoRepository.save(eventoExistente);
    }
    
    
    private List<Participante> generarParticipantesAleatorios() {
        List<Participante> todosLosParticipantes = participanteRepository.findAll();
        List<Participante> seleccionados = new ArrayList<>();
        List<Participante> candidatos = new ArrayList<>(todosLosParticipantes); // Copia para no afectar la original

        Random rand = new Random();
        while (seleccionados.size() < 4 && !candidatos.isEmpty()) {
            int randomIndex = rand.nextInt(candidatos.size());
            Participante participanteAleatorio = candidatos.remove(randomIndex); // Evita repeticiones
            seleccionados.add(participanteAleatorio);
        }

        return seleccionados;
    }    
}