package com.galeria.servlets;

import com.galeria.models.Venta;
import com.galeria.models.ObraDeArte;
import com.galeria.models.Coleccionista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ventas")
@MultipartConfig
public class VentasServlet extends HttpServlet {
    private List<Venta> ventas;
    private List<ObraDeArte> obras;
    private List<Coleccionista> clientes;

    @Override
    public void init() throws ServletException {
        // Inicializar datos de ejemplo para obras
        obras = new ArrayList<>();
        obras.add(new ObraDeArte(1, "Mona Lisa", "Leonardo da Vinci", 1503, "Óleo sobre tabla", "77x53 cm", "Conservada", 1000000.0, "Obra maestra", "monalisa.jpg"));
        obras.add(new ObraDeArte(2, "La noche estrellada", "Vincent van Gogh", 1889, "Óleo sobre lienzo", "73x92 cm", "Conservada", 2000000.0, "Obra icónica", "lanocheestrellada.jpg"));

        // Inicializar datos de ejemplo para coleccionistas
        clientes = new ArrayList<>();
        clientes.add(new Coleccionista(1, "Luis Martínez", "2020-01-15", "Básica", "Arte moderno", 10.0, true, false, "luis.jpg"));
        clientes.add(new Coleccionista(2, "Ana López", "2018-03-10", "Avanzada", "Arte renacentista", 15.0, true, true, "ana.jpg"));

        // Inicializar datos de ejemplo para ventas
        ventas = new ArrayList<>();
        ventas.add(new Venta(1, 1, 1, 1500000.0, "2024-01-01", "Juan Pérez", "Tarjeta", true, "Primera venta registrada", "venta1.jpg"));
        ventas.add(new Venta(2, 2, 2, 2500000.0, "2024-02-15", "María López", "Efectivo", false, "Segunda venta registrada", "venta2.jpg"));

        // Guardar las listas en el contexto de la aplicación para que otros servlets puedan usarlas
        getServletContext().setAttribute("ventas", ventas);
        getServletContext().setAttribute("obras", obras);
        getServletContext().setAttribute("clientes", clientes);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pasar las listas al JSP
        request.setAttribute("ventas", ventas);
        request.setAttribute("obras", obras);
        request.setAttribute("clientes", clientes);

        // Redirigir al JSP de ventas
        request.getRequestDispatcher("/ventas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            ventas.removeIf(v -> v.getId() == id);
        } else {
            // Agregar una nueva venta
            int id = ventas.size() + 1;
            int idObra = Integer.parseInt(request.getParameter("idObra"));
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
            String fechaVenta = request.getParameter("fechaVenta");
            String encargadoVenta = request.getParameter("encargadoVenta");
            String metodoPago = request.getParameter("metodoPago");
            boolean facturaGenerada = request.getParameter("facturaGenerada") != null;
            String observaciones = request.getParameter("observaciones");

            Venta nuevaVenta = new Venta(id, idObra, idCliente, precioVenta, fechaVenta, encargadoVenta, metodoPago, facturaGenerada, observaciones, null);
            ventas.add(nuevaVenta);
        }

        // Redirigir al listado de ventas
        response.sendRedirect("ventas");
    }
}
