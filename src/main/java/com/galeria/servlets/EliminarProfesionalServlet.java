package com.galeria.servlets;

import com.galeria.models.Profesional;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/eliminarProfesional")
public class EliminarProfesionalServlet extends HttpServlet {

    private List<Profesional> profesionales;

    @Override
    public void init() throws ServletException {
        super.init();
        profesionales = ProfesionalesServlet.getProfesionales();
        if (profesionales == null) {
            throw new ServletException("La lista de profesionales no está inicializada.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            return;
        }

        boolean eliminado = profesionales.removeIf(profesional -> profesional.getId() == id);

        if (eliminado) {
            resp.sendRedirect("profesionales");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Profesional no encontrado.");
        }
    }
}
