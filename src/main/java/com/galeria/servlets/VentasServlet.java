package com.galeria.servlets;

import com.galeria.models.Venta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/ventas") // URL para acceder al servlet
public class VentasServlet extends HttpServlet {
    private List<Venta> ventas; // Lista para simular una base de datos

    @Override
    public void init() throws ServletException {
        // Inicializar datos de ejemplo
        ventas = new ArrayList<>();
        ventas.add(new Venta(1, 1, 5000000.0, "2024-01-15", "Juan Pérez", 101, "Tarjeta", true, "Entrega a domicilio", "obra1.jpg"));
        ventas.add(new Venta(2, 2, 7500000.0, "2024-01-20", "Laura Gómez", 102, "Transferencia", true, "Retiro en tienda", "obra2.jpg"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostrar lista de ventas
        request.setAttribute("ventas", ventas);
        request.getRequestDispatcher("/ventas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Agregar una nueva venta desde un formulario
        int id = ventas.size() + 1; // Generar un ID único
        int idObra = Integer.parseInt(request.getParameter("idObra"));
        double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
        String fechaVenta = request.getParameter("fechaVenta");
        String encargadoVenta = request.getParameter("encargadoVenta");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String metodoPago = request.getParameter("metodoPago");
        boolean facturaGenerada = Boolean.parseBoolean(request.getParameter("facturaGenerada"));
        String observaciones = request.getParameter("observaciones");
        String imagen = request.getParameter("imagen");

        // Crear una nueva venta
        Venta nuevaVenta = new Venta(id, idObra, precioVenta, fechaVenta, encargadoVenta, idCliente, metodoPago, facturaGenerada, observaciones, imagen);
        ventas.add(nuevaVenta);

        // Redirigir al listado
        response.sendRedirect("ventas");
    }
}
