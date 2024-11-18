package com.galeria.servlets;

import com.galeria.models.Coleccion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/eliminarColeccion")
public class EliminarColeccionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); // Redirigir las solicitudes GET al método POST
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Coleccion> colecciones = (List<Coleccion>) getServletContext().getAttribute("colecciones");

        int id = Integer.parseInt(req.getParameter("id"));
        boolean eliminado = colecciones.removeIf(c -> c.getId() == id);

        if (eliminado) {
            resp.sendRedirect("colecciones");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Colección no encontrada.");
        }
    }
}
