package com.galeria.servlets;

import com.galeria.models.Coleccionista;
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

@WebServlet("/coleccionistas") // URL para acceder al servlet
public class ColeccionistasServlet extends HttpServlet {
    private List<Coleccionista> coleccionistas; // Lista para simular una base de datos

    @Override
    public void init() throws ServletException {
        // Inicializar datos de ejemplo
        coleccionistas = new ArrayList<>();
        coleccionistas.add(new Coleccionista(1, "Luis Martínez", "2020-01-15", "Básica", "Horarios matutinos", "Obra A, Obra B", 10.0, true, false, "luis.jpg"));
        coleccionistas.add(new Coleccionista(2, "Ana López", "2018-03-10", "Avanzada", "Exposiciones de arte moderno", "Obra C, Obra D", 15.0, true, true, "ana.jpg"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostrar lista de coleccionistas
        request.setAttribute("coleccionistas", coleccionistas);
        request.getRequestDispatcher("/coleccionistas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Agregar un nuevo coleccionista desde un formulario
        int id = coleccionistas.size() + 1; // Generar un ID único
        String nombre = request.getParameter("nombre");
        String fechaInicioMembresia = request.getParameter("fechaInicioMembresia");
        String tipoMembresia = request.getParameter("tipoMembresia");
        String preferencias = request.getParameter("preferencias");
        String historialCompras = request.getParameter("historialCompras");
        double descuentos = Double.parseDouble(request.getParameter("descuentos"));
        boolean suscripciones = Boolean.parseBoolean(request.getParameter("suscripciones"));
        boolean renovacionAutomatica = Boolean.parseBoolean(request.getParameter("renovacionAutomatica"));
        String imagen = request.getParameter("imagen");

        // Crear un nuevo coleccionista
        Coleccionista nuevoColeccionista = new Coleccionista(id, nombre, fechaInicioMembresia, tipoMembresia, preferencias, historialCompras, descuentos, suscripciones, renovacionAutomatica, imagen);
        coleccionistas.add(nuevoColeccionista);

        // Redirigir al listado
        response.sendRedirect("coleccionistas");
    }
}
