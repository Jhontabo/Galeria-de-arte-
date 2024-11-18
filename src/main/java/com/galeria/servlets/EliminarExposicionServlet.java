package com.galeria.servlets;

import com.galeria.models.Exposicion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/eliminarExposicion")
public class EliminarExposicionServlet extends HttpServlet {

    private List<Exposicion> exposiciones;

    @Override
    public void init() throws ServletException {
        exposiciones = ExposicionesServlet.getExposiciones();
        if (exposiciones == null) {
            throw new ServletException("La lista de exposiciones no está inicializada.");
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

        boolean eliminado = exposiciones.removeIf(expo -> expo.getId() == id);

        if (eliminado) {
            resp.sendRedirect("exposiciones");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Exposición no encontrada.");
        }
    }
}
