package com.galeria.servlets;

import com.galeria.models.Exposicion;
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

@WebServlet("/exposiciones") // URL para acceder al servlet
public class ExposicionesServlet extends HttpServlet {
    private List<Exposicion> exposiciones; // Lista para simular una base de datos

    @Override
    public void init() throws ServletException {
        // Inicializar datos de ejemplo
        exposiciones = new ArrayList<>();
        exposiciones.add(new Exposicion(1, "Renaissance Art", "2024-01-01", "2024-06-30", "John Doe", "Renacimiento Europeo", List.of(1, 2), "Sala A", "Exposición sobre arte renacentista", "renaissance.jpg"));
        exposiciones.add(new Exposicion(2, "Modern Art", "2024-07-01", "2024-12-31", "Jane Smith", "Arte Moderno", List.of(3, 4), "Sala B", "Exposición de arte moderno y contemporáneo", "modern.jpg"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostrar lista de exposiciones
        request.setAttribute("exposiciones", exposiciones);
        request.getRequestDispatcher("/exposiciones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Agregar una nueva exposición desde un formulario
        int id = exposiciones.size() + 1; // Generar un ID único
        String titulo = request.getParameter("titulo");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        String responsable = request.getParameter("responsable");
        String tematica = request.getParameter("tematica");
        String salaAsignada = request.getParameter("salaAsignada");
        String descripcion = request.getParameter("descripcion");
        String imagen = request.getParameter("imagen");

        // Crear una nueva exposición
        Exposicion nuevaExposicion = new Exposicion(id, titulo, fechaInicio, fechaFin, responsable, tematica, new ArrayList<>(), salaAsignada, descripcion, imagen);
        exposiciones.add(nuevaExposicion);

        // Redirigir al listado
        response.sendRedirect("exposiciones");
    }
}
