package com.galeria.models;

public class ObraDeArte {
    private int id;
    private String titulo;
    private String artista;
    private int anioCreacion;
    private String tecnica;
    private String dimensiones;
    private String estado;
    private double precio;
    private String observaciones;
    private String imagen;

    // Constructor vacío
    public ObraDeArte() {}

    // Constructor con parámetros
    public ObraDeArte(int id, String titulo, String artista, int anioCreacion, String tecnica, String dimensiones, String estado, double precio, String observaciones, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.anioCreacion = anioCreacion;
        this.tecnica = tecnica;
        this.dimensiones = dimensiones;
        this.estado = estado;
        this.precio = precio;
        this.observaciones = observaciones;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }

    public int getAnioCreacion() { return anioCreacion; }
    public void setAnioCreacion(int anioCreacion) { this.anioCreacion = anioCreacion; }

    public String getTecnica() { return tecnica; }
    public void setTecnica(String tecnica) { this.tecnica = tecnica; }

    public String getDimensiones() { return dimensiones; }
    public void setDimensiones(String dimensiones) { this.dimensiones = dimensiones; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}
