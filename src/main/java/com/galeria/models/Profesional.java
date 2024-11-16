package com.galeria.models;

public class Profesional {
    private int id;
    private String nombreCompleto;
    private String especialidad;
    private int aniosExperiencia;
    private String proyectosPrevios;
    private String contacto;
    private String institucionEducativa;
    private String premios;
    private String estiloPreferido;
    private String imagen;

    public Profesional(int id, String nombreCompleto, String especialidad, int aniosExperiencia, String proyectosPrevios,
                       String contacto, String institucionEducativa, String premios, String estiloPreferido, String imagen) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.aniosExperiencia = aniosExperiencia;
        this.proyectosPrevios = proyectosPrevios;
        this.contacto = contacto;
        this.institucionEducativa = institucionEducativa;
        this.premios = premios;
        this.estiloPreferido = estiloPreferido;
        this.imagen = imagen;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getProyectosPrevios() {
        return proyectosPrevios;
    }

    public void setProyectosPrevios(String proyectosPrevios) {
        this.proyectosPrevios = proyectosPrevios;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getInstitucionEducativa() {
        return institucionEducativa;
    }

    public void setInstitucionEducativa(String institucionEducativa) {
        this.institucionEducativa = institucionEducativa;
    }

    public String getPremios() {
        return premios;
    }

    public void setPremios(String premios) {
        this.premios = premios;
    }

    public String getEstiloPreferido() {
        return estiloPreferido;
    }

    public void setEstiloPreferido(String estiloPreferido) {
        this.estiloPreferido = estiloPreferido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
