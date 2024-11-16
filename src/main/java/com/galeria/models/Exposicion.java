package com.galeria.models;

import java.util.List;

public class Exposicion {
    private int id;
    private String titulo;
    private String fechaInicio;
    private String fechaFin;
    private String responsable;
    private String tematica;
    private List<Integer> obrasIncluidas; // IDs de las obras asociadas a la exposición
    private String salaAsignada;
    private String descripcion;
    private String imagen;

    // Constructor vacío
    public Exposicion() {}

    // Constructor con parámetros
    public Exposicion(int id, String titulo, String fechaInicio, String fechaFin, String responsable, String tematica, List<Integer> obrasIncluidas, String salaAsignada, String descripcion, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.responsable = responsable;
        this.tematica = tematica;
        this.obrasIncluidas = obrasIncluidas;
        this.salaAsignada = salaAsignada;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }

    public String getTematica() { return tematica; }
    public void setTematica(String tematica) { this.tematica = tematica; }

    public List<Integer> getObrasIncluidas() { return obrasIncluidas; }
    public void setObrasIncluidas(List<Integer> obrasIncluidas) { this.obrasIncluidas = obrasIncluidas; }

    public String getSalaAsignada() { return salaAsignada; }
    public void setSalaAsignada(String salaAsignada) { this.salaAsignada = salaAsignada; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}
