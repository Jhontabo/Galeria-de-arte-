package com.galeria.models;

public class Venta {
    private int id;
    private int idObra; // ID de la obra vendida
    private double precioVenta;
    private String fechaVenta;
    private String encargadoVenta; // Nombre o ID del empleado encargado
    private int idCliente; // ID del cliente que compró la obra
    private String metodoPago; // Efectivo, tarjeta, transferencia, etc.
    private boolean facturaGenerada; // Sí o No
    private String observaciones; // Detalles como "entrega a domicilio" o "entrega personal"
    private String imagen; // Imagen asociada a la obra (si es necesario)

    // Constructor vacío
    public Venta() {}

    // Constructor con parámetros
    public Venta(int id, int idObra, double precioVenta, String fechaVenta, String encargadoVenta, int idCliente, String metodoPago, boolean facturaGenerada, String observaciones, String imagen) {
        this.id = id;
        this.idObra = idObra;
        this.precioVenta = precioVenta;
        this.fechaVenta = fechaVenta;
        this.encargadoVenta = encargadoVenta;
        this.idCliente = idCliente;
        this.metodoPago = metodoPago;
        this.facturaGenerada = facturaGenerada;
        this.observaciones = observaciones;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdObra() { return idObra; }
    public void setIdObra(int idObra) { this.idObra = idObra; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public String getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(String fechaVenta) { this.fechaVenta = fechaVenta; }

    public String getEncargadoVenta() { return encargadoVenta; }
    public void setEncargadoVenta(String encargadoVenta) { this.encargadoVenta = encargadoVenta; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public boolean isFacturaGenerada() { return facturaGenerada; }
    public void setFacturaGenerada(boolean facturaGenerada) { this.facturaGenerada = facturaGenerada; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}
