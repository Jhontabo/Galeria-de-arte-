package com.galeria.servlets;

import com.galeria.models.Coleccionista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/eliminarColeccionista")
public class EliminarColeccionistaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            return;
        }

        List<Coleccionista> coleccionistas = ColeccionistasServlet.getColeccionistas(this);

        boolean eliminado = coleccionistas.removeIf(coleccionista -> coleccionista.getId() == id);

        if (eliminado) {
            resp.sendRedirect("coleccionistas");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Coleccionista no encontrado.");
        }
    }
}
