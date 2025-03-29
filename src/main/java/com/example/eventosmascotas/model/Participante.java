package com.example.eventosmascotas.model;

public class Participante {
    private int id;
    private String nombre;
    private String apellido;   
    private String correo;  
    private String mascota;

    public Participante(int id, String nombre, String apellido, String correo, String mascota) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.mascota = mascota;
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
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getMascota() {
        return mascota;
    }
    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

}
