package com.galeria.servlets;

import com.galeria.models.Profesional;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profesionales")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class ProfesionalesServlet extends HttpServlet {
    private static final List<Profesional> profesionales = new ArrayList<>();
    private static int nextId = 1;

    @Override
    public void init() throws ServletException {
        super.init();

        if (profesionales.isEmpty()) {
            // Datos iniciales
            profesionales.add(new Profesional(nextId++, "Carlos Martínez", "Restaurador", 10,
                    "Restauración de cuadros renacentistas", "carlos.martinez@gmail.com",
                    "Universidad de Bellas Artes", "Premio Nacional de Restauración", "Clásico", "carlos.jpg"));

            profesionales.add(new Profesional(nextId++, "Ana López", "Curadora", 5,
                    "Curaduría de arte moderno", "ana.lopez@gmail.com",
                    "Instituto de Arte Moderno", "Premio Joven Curadora", "Contemporáneo", "ana.jpg"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Solo lista profesionales; delega las ediciones al servlet dedicado.
        req.setAttribute("profesionales", profesionales);
        req.getRequestDispatcher("/profesionales.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            agregarProfesional(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
        }
    }

    private void agregarProfesional(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String nombreCompleto = req.getParameter("nombreCompleto");
        String especialidad = req.getParameter("especialidad");
        int aniosExperiencia = Integer.parseInt(req.getParameter("aniosExperiencia"));
        String proyectosPrevios = req.getParameter("proyectosPrevios");
        String contacto = req.getParameter("contacto");
        String institucionEducativa = req.getParameter("institucionEducativa");
        String premios = req.getParameter("premios");
        String estiloPreferido = req.getParameter("estiloPreferido");

        Profesional nuevoProfesional = new Profesional(nextId++, nombreCompleto, especialidad, aniosExperiencia,
                proyectosPrevios, contacto, institucionEducativa, premios, estiloPreferido, null);

        profesionales.add(nuevoProfesional);
        resp.sendRedirect("profesionales");
    }

    public static List<Profesional> getProfesionales() {
        return profesionales;
    }
}
