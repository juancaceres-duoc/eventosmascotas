package com.example.eventosmascotas.service;
import com.example.eventosmascotas.model.Evento;
import com.example.eventosmascotas.model.Participante;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class EventoService {
    private List<Evento> eventos;
    private List<Participante> participantes;

    public EventoService() {
        generarEventos();
    }

    private void generarParticipantes(){
        participantes = new ArrayList<>();
        participantes.add(new Participante(1, "Juan", "Perez", "juanperezz@gmail.com", "Cachupin"));
        participantes.add(new Participante(2, "Maria", "Lopez", "marialopez@gmail.com", "Firulais"));
        participantes.add(new Participante(3, "Carlos", "Gomez", "carlosgomez@gmail.com", "Pelusa"));
        participantes.add(new Participante(4, "Ana", "Martinez", "anamartinez@gmail.com", "Manchas"));
        participantes.add(new Participante(5, "Luis", "Hernandez", "luishernandez@gmail.com", "Rex"));
        participantes.add(new Participante(6, "Sofia", "Diaz", "sofiadiaz@gmail.com", "Luna"));
        participantes.add(new Participante(7, "Diego", "Torres", "diegotorres@gmail.com", "Max"));
        participantes.add(new Participante(8, "Juan", "Caceres", "juantcaceres@gmail.com", "Coppo"));
        participantes.add(new Participante(9, "Camila", "Fuentes", "camilafuentes@gmail.com", "Rocky"));
        participantes.add(new Participante(10, "Pedro", "Alvarez", "pedroalvarez@gmail.com", "Toby"));

    }

    public List<Evento> generarEventos() {
        eventos = new ArrayList<>();        
        List<Participante> participantesEvento1 = generarParticipantesAleatorios();
        Evento evento1 = new Evento(1, "Concurso de Mascotas", "Un concurso para mostrar las habilidades de tus mascotas", "2023-10-01", participantesEvento1);
        eventos.add(evento1);

        List<Participante> participantesEvento2 = generarParticipantesAleatorios();
        Evento evento2 = new Evento(2, "Paseo de Mascotas", "Un paseo para disfrutar con tus mascotas", "2023-10-15", participantesEvento2);
        eventos.add(evento2);

        List<Participante> participantesEvento3 = generarParticipantesAleatorios();
        Evento evento3 = new Evento(3, "Fiesta de Mascotas", "Una fiesta para celebrar el amor por las mascotas", "2023-10-30", participantesEvento3);
        eventos.add(evento3);

        List<Participante> participantesEvento4 = generarParticipantesAleatorios();
        Evento evento4 = new Evento(4, "Fiesta de cumpleaños para Mascotas", "Un evento especial donde puedes celebrar con el regalón de la familia y sus amigos", "2023-11-05", participantesEvento4);
        eventos.add(evento4);

        return eventos;
    }

    private List<Participante> generarParticipantesAleatorios() {
        generarParticipantes();
        List<Participante> participantesEvento = new ArrayList<>();

        while(participantesEvento.size() < 4) {
            int randomIndex = (int) (Math.random() * participantes.size());
            Participante participanteAleatorio = participantes.get(randomIndex);
            // Verificar si el participante ya está en la lista
            if (!participantesEvento.contains(participanteAleatorio)) {
                participantesEvento.add(participanteAleatorio);
            }
            
        }
        return participantesEvento;
    }




    
}
