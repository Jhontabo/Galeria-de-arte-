package com.galeria.models;

import java.util.List;

public class Coleccion {
    private int id;
    private String nombre;
    private String descripcion;
    private String responsable;
    private String estilo;
    private List<Integer> obrasIncluidas; // Lista de IDs de las obras incluidas
    private String fechasExhibicion;
    private String salaAsignada;
    private String observaciones;
    private String imagen;

    // Constructor vacío
    public Coleccion() {}

    // Constructor con parámetros
    public Coleccion(int id, String nombre, String descripcion, String responsable, String estilo, List<Integer> obrasIncluidas, String fechasExhibicion, String salaAsignada, String observaciones, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.estilo = estilo;
        this.obrasIncluidas = obrasIncluidas;
        this.fechasExhibicion = fechasExhibicion;
        this.salaAsignada = salaAsignada;
        this.observaciones = observaciones;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }

    public String getEstilo() { return estilo; }
    public void setEstilo(String estilo) { this.estilo = estilo; }

    public List<Integer> getObrasIncluidas() { return obrasIncluidas; }
    public void setObrasIncluidas(List<Integer> obrasIncluidas) { this.obrasIncluidas = obrasIncluidas; }

    public String getFechasExhibicion() { return fechasExhibicion; }
    public void setFechasExhibicion(String fechasExhibicion) { this.fechasExhibicion = fechasExhibicion; }

    public String getSalaAsignada() { return salaAsignada; }
    public void setSalaAsignada(String salaAsignada) { this.salaAsignada = salaAsignada; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
