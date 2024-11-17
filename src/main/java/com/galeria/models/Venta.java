package com.galeria.models;

public class Venta {
    private int id;
    private int idObra; // ID de la obra vendida
    private int idCliente; // ID del cliente
    private double precioVenta;
    private String fechaVenta;
    private String encargadoVenta; // Nombre del encargado de la venta
    private String metodoPago; // Efectivo, Tarjeta, Transferencia
    private boolean facturaGenerada; // Si se generó o no una factura
    private String observaciones;
    private String imagen; // Imagen relacionada con la venta

    // Constructor vacío
    public Venta() {}

    // Constructor con parámetros
    public Venta(int id, int idObra, int idCliente, double precioVenta, String fechaVenta, String encargadoVenta,
                 String metodoPago, boolean facturaGenerada, String observaciones, String imagen) {
        this.id = id;
        this.idObra = idObra;
        this.idCliente = idCliente;
        this.precioVenta = precioVenta;
        this.fechaVenta = fechaVenta;
        this.encargadoVenta = encargadoVenta;
        this.metodoPago = metodoPago;
        this.facturaGenerada = facturaGenerada;
        this.observaciones = observaciones;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getEncargadoVenta() {
        return encargadoVenta;
    }

    public void setEncargadoVenta(String encargadoVenta) {
        this.encargadoVenta = encargadoVenta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public boolean isFacturaGenerada() {
        return facturaGenerada;
    }

    public void setFacturaGenerada(boolean facturaGenerada) {
        this.facturaGenerada = facturaGenerada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
