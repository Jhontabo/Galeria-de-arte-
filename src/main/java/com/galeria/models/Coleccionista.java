package com.galeria.models;

public class Coleccionista {
    private int id;
    private String nombre;
    private String fechaInicioMembresia;
    private String tipoMembresia; // Básica o Avanzada
    private String preferencias; // Ejemplo: horarios, exposiciones favoritas
    private double descuentos; // Porcentaje de descuento aplicado
    private boolean suscripciones; // Sí o No
    private boolean renovacionAutomatica; // Sí o No
    private String imagen;

    // Constructor vacío
    public Coleccionista() {}

    // Constructor con parámetros
    public Coleccionista(int id, String nombre, String fechaInicioMembresia, String tipoMembresia, String preferencias, double descuentos, boolean suscripciones, boolean renovacionAutomatica, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicioMembresia = fechaInicioMembresia;
        this.tipoMembresia = tipoMembresia;
        this.preferencias = preferencias;
        this.descuentos = descuentos;
        this.suscripciones = suscripciones;
        this.renovacionAutomatica = renovacionAutomatica;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getFechaInicioMembresia() { return fechaInicioMembresia; }
    public void setFechaInicioMembresia(String fechaInicioMembresia) { this.fechaInicioMembresia = fechaInicioMembresia; }

    public String getTipoMembresia() { return tipoMembresia; }
    public void setTipoMembresia(String tipoMembresia) { this.tipoMembresia = tipoMembresia; }

    public String getPreferencias() { return preferencias; }
    public void setPreferencias(String preferencias) { this.preferencias = preferencias; }

    public double getDescuentos() { return descuentos; }
    public void setDescuentos(double descuentos) { this.descuentos = descuentos; }

    public boolean isSuscripciones() { return suscripciones; }
    public void setSuscripciones(boolean suscripciones) { this.suscripciones = suscripciones; }

    public boolean isRenovacionAutomatica() { return renovacionAutomatica; }
    public void setRenovacionAutomatica(boolean renovacionAutomatica) { this.renovacionAutomatica = renovacionAutomatica; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}
