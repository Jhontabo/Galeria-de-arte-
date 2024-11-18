package com.galeria.servlets;

import com.galeria.models.ObraDeArte;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/eliminarObra")
public class EliminarObraServlet extends HttpServlet {

    private List<ObraDeArte> obras;

    @Override
    public void init() throws ServletException {
        obras = (List<ObraDeArte>) getServletContext().getAttribute("obras");
        if (obras == null) {
            throw new ServletException("La lista de obras no está inicializada.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            boolean eliminado = obras.removeIf(obra -> obra.getId() == id);

            if (!eliminado) {
                throw new ServletException("La obra no fue encontrada.");
            }

            resp.sendRedirect("obras");
        } catch (NumberFormatException e) {
            throw new ServletException("ID inválido proporcionado.", e);
        }
    }
}
