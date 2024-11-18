package com.galeria.servlets;

import com.galeria.models.Venta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/eliminarVenta")
public class EliminarVentaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Venta> ventas = (List<Venta>) getServletContext().getAttribute("ventas");

        boolean eliminado = ventas.removeIf(v -> v.getId() == id);

        if (!eliminado) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Venta no encontrada.");
        } else {
            resp.sendRedirect("ventas");
        }
    }
}
