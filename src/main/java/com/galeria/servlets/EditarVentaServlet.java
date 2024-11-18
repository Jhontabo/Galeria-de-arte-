package com.galeria.servlets;

import com.galeria.models.Venta;
import com.galeria.models.ObraDeArte;
import com.galeria.models.Coleccionista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/editarVenta")
public class EditarVentaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        // Recuperar listas del contexto
        List<Venta> ventas = (List<Venta>) getServletContext().getAttribute("ventas");
        List<ObraDeArte> obras = (List<ObraDeArte>) getServletContext().getAttribute("obras");
        List<Coleccionista> clientes = (List<Coleccionista>) getServletContext().getAttribute("clientes");

        if (ventas == null || obras == null || clientes == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Datos necesarios no inicializados.");
            return;
        }

        // Buscar la venta por ID
        Venta venta = ventas.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);

        if (venta == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Venta no encontrada.");
            return;
        }

        // Pasar datos al JSP
        req.setAttribute("venta", venta);
        req.setAttribute("obras", obras);
        req.setAttribute("clientes", clientes);

        req.getRequestDispatcher("/editarVenta.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener el ID de la venta a editar
        int id = Integer.parseInt(req.getParameter("id"));

        // Recuperar la lista de ventas del contexto de la aplicación
        List<Venta> ventas = (List<Venta>) getServletContext().getAttribute("ventas");

        // Buscar la venta con el ID especificado
        Venta venta = ventas.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);

        // Validar si la venta fue encontrada
        if (venta == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Venta no encontrada.");
            return;
        }

        // Actualizar los datos de la venta
        venta.setPrecioVenta(Double.parseDouble(req.getParameter("precioVenta")));
        venta.setFechaVenta(req.getParameter("fechaVenta"));
        venta.setEncargadoVenta(req.getParameter("encargadoVenta"));
        venta.setMetodoPago(req.getParameter("metodoPago"));
        venta.setFacturaGenerada("on".equals(req.getParameter("facturaGenerada")));
        venta.setObservaciones(req.getParameter("observaciones"));

        // Redirigir al listado de ventas
        resp.sendRedirect("ventas");
    }
}
