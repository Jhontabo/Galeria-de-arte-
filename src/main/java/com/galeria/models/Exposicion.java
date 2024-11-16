package com.galeria.models;

import java.util.List;

public class Exposicion {
    private int id;
    private String titulo;
    private String fechaInicio;
    private String fechaFin;
    private String responsable;
    private String tematica;
    private String salaAsignada;
    private String descripcion;
    private String imagen;
    private List<Integer> obrasIncluidas; // Lista de IDs de obras incluidas

    // Constructor principal
    public Exposicion(int id, String titulo, String fechaInicio, String fechaFin, String responsable,
                      String tematica, String salaAsignada, String descripcion, String imagen,
                      List<Integer> obrasIncluidas) {
        this.id = id;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.responsable = responsable;
        this.tematica = tematica;
        this.salaAsignada = salaAsignada;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.obrasIncluidas = obrasIncluidas;
    }

    // Constructor sin obras incluidas (por compatibilidad o inicialización simple)
    public Exposicion(int id, String titulo, String fechaInicio, String fechaFin, String responsable,
                      String tematica, String salaAsignada, String descripcion, String imagen) {
        this(id, titulo, fechaInicio, fechaFin, responsable, tematica, salaAsignada, descripcion, imagen, null);
    }

    // Método para editar los atributos de la exposición
    public void editar(String titulo, String fechaInicio, String fechaFin, String responsable,
                       String tematica, String salaAsignada, String descripcion, String imagen,
                       List<Integer> obrasIncluidas) {
        this.titulo = titulo != null ? titulo : this.titulo;
        this.fechaInicio = fechaInicio != null ? fechaInicio : this.fechaInicio;
        this.fechaFin = fechaFin != null ? fechaFin : this.fechaFin;
        this.responsable = responsable != null ? responsable : this.responsable;
        this.tematica = tematica != null ? tematica : this.tematica;
        this.salaAsignada = salaAsignada != null ? salaAsignada : this.salaAsignada;
        this.descripcion = descripcion != null ? descripcion : this.descripcion;
        this.imagen = imagen != null ? imagen : this.imagen;
        if (obrasIncluidas != null) {
            this.obrasIncluidas = obrasIncluidas;
        }
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getSalaAsignada() {
        return salaAsignada;
    }

    public void setSalaAsignada(String salaAsignada) {
        this.salaAsignada = salaAsignada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Integer> getObrasIncluidas() {
        return obrasIncluidas;
    }

    public void setObrasIncluidas(List<Integer> obrasIncluidas) {
        this.obrasIncluidas = obrasIncluidas;
    }
}
