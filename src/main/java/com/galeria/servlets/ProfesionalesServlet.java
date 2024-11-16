package com.galeria.servlets;

import com.galeria.models.ProfesionalArte;
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

@WebServlet("/profesionales") // URL para acceder al servlet
public class ProfesionalesServlet extends HttpServlet {
    private List<ProfesionalArte> profesionales; // Lista para simular una base de datos

    @Override
    public void init() throws ServletException {
        // Inicializar datos de ejemplo
        profesionales = new ArrayList<>();
        profesionales.add(new ProfesionalArte(1, "María López", "Pintura", 15, "Retratos realistas", "maria@example.com", "Escuela de Bellas Artes", "Premio Nacional de Arte 2020", "Realismo", "maria.jpg"));
        profesionales.add(new ProfesionalArte(2, "Carlos Pérez", "Escultura", 10, "Monumentos históricos", "carlos@example.com", "Universidad de Arte Moderno", "Reconocimiento Internacional 2018", "Clásico", "carlos.jpg"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostrar lista de profesionales
        request.setAttribute("profesionales", profesionales);
        request.getRequestDispatcher("/profesionales.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Agregar un nuevo profesional desde un formulario
        int id = profesionales.size() + 1; // Generar un ID único
        String nombreCompleto = request.getParameter("nombreCompleto");
        String especialidad = request.getParameter("especialidad");
        int aniosExperiencia = Integer.parseInt(request.getParameter("aniosExperiencia"));
        String proyectosPrevios = request.getParameter("proyectosPrevios");
        String contacto = request.getParameter("contacto");
        String institucionEducativa = request.getParameter("institucionEducativa");
        String premios = request.getParameter("premios");
        String estiloPreferido = request.getParameter("estiloPreferido");
        String imagen = request.getParameter("imagen");

        // Crear un nuevo profesional
        ProfesionalArte nuevoProfesional = new ProfesionalArte(id, nombreCompleto, especialidad, aniosExperiencia, proyectosPrevios, contacto, institucionEducativa, premios, estiloPreferido, imagen);
        profesionales.add(nuevoProfesional);

        // Redirigir al listado
        response.sendRedirect("profesionales");
    }
}
