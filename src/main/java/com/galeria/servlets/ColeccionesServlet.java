package com.galeria.servlets;

import com.galeria.models.Coleccion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/colecciones") // URL para acceder al servlet
public class ColeccionesServlet extends HttpServlet {
    private List<Coleccion> colecciones; // Lista para simular una base de datos

    @Override
    public void init() throws ServletException {
        // Inicializar datos de ejemplo
        colecciones = new ArrayList<>();
        colecciones.add(new Coleccion(1, "Impresionismo", "Colección de obras impresionistas", "Luis Ortega", "Impresionismo", List.of(1, 2), "Permanente", "Sala A", "Conservada en ambiente controlado", "impresionismo.jpg"));
        colecciones.add(new Coleccion(2, "Arte Contemporáneo", "Colección de arte moderno y contemporáneo", "Ana Gómez", "Contemporáneo", List.of(3, 4), "2024-01-01 - 2024-12-31", "Sala B", "Exhibición temporal", "contemporaneo.jpg"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostrar lista de colecciones
        request.setAttribute("colecciones", colecciones);
        request.getRequestDispatcher("/colecciones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Agregar una nueva colección desde un formulario
        int id = colecciones.size() + 1; // Generar un ID único
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String responsable = request.getParameter("responsable");
        String estilo = request.getParameter("estilo");
        String fechasExhibicion = request.getParameter("fechasExhibicion");
        String salaAsignada = request.getParameter("salaAsignada");
        String observaciones = request.getParameter("observaciones");
        String imagen = request.getParameter("imagen");

        // Crear una nueva colección
        Coleccion nuevaColeccion = new Coleccion(id, nombre, descripcion, responsable, estilo, new ArrayList<>(), fechasExhibicion, salaAsignada, observaciones, imagen);
        colecciones.add(nuevaColeccion);

        // Redirigir al listado
        response.sendRedirect("colecciones");
    }
}
