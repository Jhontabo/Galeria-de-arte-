package com.galeria.servlets;

import com.galeria.models.Coleccionista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/editarColeccionista") // Mapeo del servlet
@MultipartConfig
public class EditarColeccionistaServlet extends HttpServlet {

    private List<Coleccionista> coleccionistas; // Referencia a la lista de coleccionistas

    @Override
    public void init() throws ServletException {
        // Inicializar la lista de coleccionistas si no existe en el contexto
        coleccionistas = (List<Coleccionista>) getServletContext().getAttribute("coleccionistas");
        if (coleccionistas == null) {
            coleccionistas = new ArrayList<>();
            coleccionistas.add(new Coleccionista(1, "Luis Martínez", "2020-01-15", "Básica", "Horarios matutinos", 10.0, true, false, "luis.jpg"));
            coleccionistas.add(new Coleccionista(2, "Ana López", "2018-03-10", "Avanzada", "Exposiciones de arte moderno", 15.0, true, true, "ana.jpg"));
            getServletContext().setAttribute("coleccionistas", coleccionistas);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Validar que el parámetro "id" esté presente y sea un número válido
        String idParam = request.getParameter("id");
        if (idParam == null || !idParam.matches("\\d+")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
            return;
        }

        // Obtener el ID del coleccionista a editar
        int id = Integer.parseInt(idParam);
        Coleccionista coleccionista = coleccionistas.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (coleccionista == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Coleccionista no encontrado");
            return;
        }

        // Pasar los datos del coleccionista a la vista
        request.setAttribute("coleccionista", coleccionista);
        request.getRequestDispatcher("/editarColeccionista.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Validar que el parámetro "id" esté presente y sea un número válido
        String idParam = request.getParameter("id");
        if (idParam == null || !idParam.matches("\\d+")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
            return;
        }

        // Obtener el ID del coleccionista a editar
        int id = Integer.parseInt(idParam);

        // Buscar el coleccionista en la lista
        Coleccionista coleccionista = coleccionistas.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (coleccionista == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Coleccionista no encontrado");
            return;
        }

        // Actualizar los datos del coleccionista
        coleccionista.setNombre(request.getParameter("nombre"));
        coleccionista.setFechaInicioMembresia(request.getParameter("fechaInicioMembresia"));
        coleccionista.setTipoMembresia(request.getParameter("tipoMembresia"));
        coleccionista.setPreferencias(request.getParameter("preferencias"));
        coleccionista.setDescuentos(Double.parseDouble(request.getParameter("descuentos")));
        coleccionista.setSuscripciones("on".equals(request.getParameter("suscripciones")));
        coleccionista.setRenovacionAutomatica("on".equals(request.getParameter("renovacionAutomatica")));

        // Manejo de imagen (opcional)
        String imagen = request.getParameter("imagen");
        if (imagen != null && !imagen.isEmpty()) {
            coleccionista.setImagen(imagen);
        }

        // Redirigir al listado
        response.sendRedirect("coleccionistas");
    }
}
