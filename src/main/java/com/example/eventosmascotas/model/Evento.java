package com.example.eventosmascotas.model;
import java.util.List;

public class Evento {
    private int id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private List<Participante> participantes;
    
    public Evento(int id, String nombre, String descripcion, String fecha, List<Participante> participantes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.participantes = participantes;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
