package com.example.eventosmascotas.repository;
import com.example.eventosmascotas.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    
}
