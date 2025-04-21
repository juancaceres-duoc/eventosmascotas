package com.example.eventosmascotas.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.List;

@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    private Long id;

    private String nombre;
    private String descripcion;
    private String fecha;

    @ManyToMany
    @JoinTable(
        name = "evento_participante",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "participante_id")
    )

    private List<Participante> participantes;
    
    public Evento(Long id, String nombre, String descripcion, String fecha, List<Participante> participantes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.participantes = participantes;
    }

    public Evento() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public List<Participante> getParticipantes() {
        return participantes;
    }
    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }
    public void addParticipante(Participante participante) {
        this.participantes.add(participante);
    }
    
}
