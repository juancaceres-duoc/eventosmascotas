package com.example.eventosmascotas.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eventosmascotas.model.Evento;


public interface EventoRepository extends JpaRepository<Evento, Long> {    
    
}
