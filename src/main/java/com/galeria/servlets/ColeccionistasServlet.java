package com.galeria.servlets;

import com.galeria.models.Coleccionista;

import java.io.File;
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

@WebServlet("/coleccionistas")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class ColeccionistasServlet extends HttpServlet {
    private List<Coleccionista> coleccionistas;

    @Override
    public void init() throws ServletException {
        // Inicializar la lista de coleccionistas con datos de ejemplo
        if (coleccionistas == null) {
            coleccionistas = new ArrayList<>();
            coleccionistas.add(new Coleccionista(1, "Luis Martínez", "2020-01-15", "Básica", "Horarios matutinos", 10.0, true, false, "luis.jpg"));
            coleccionistas.add(new Coleccionista(2, "Ana López", "2018-03-10", "Avanzada", "Exposiciones de arte moderno", 15.0, true, true, "ana.jpg"));
        }
    }

    private void ensureColeccionistasInitialized() throws ServletException {
        if (coleccionistas == null) {
            init();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ensureColeccionistasInitialized();

        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            // Editar un coleccionista
            int id = Integer.parseInt(request.getParameter("id"));
            Coleccionista coleccionista = coleccionistas.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (coleccionista != null) {
                request.setAttribute("coleccionista", coleccionista);
                request.getRequestDispatcher("/editarColeccionista.jsp").forward(request, response);
            } else {
                response.sendRedirect("coleccionistas");
            }
        } else {
            // Mostrar lista de coleccionistas
            request.setAttribute("coleccionistas", coleccionistas);
            request.getRequestDispatcher("/coleccionistas.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ensureColeccionistasInitialized();

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            // Eliminar un coleccionista
            int id = Integer.parseInt(request.getParameter("id"));
            coleccionistas.removeIf(coleccionista -> coleccionista.getId() == id);
            response.sendRedirect("coleccionistas");
        } else if ("edit".equals(action)) {
            // Editar un coleccionista
            int id = Integer.parseInt(request.getParameter("id"));
            Coleccionista coleccionista = coleccionistas.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (coleccionista != null) {
                coleccionista.setNombre(request.getParameter("nombre"));
                coleccionista.setFechaInicioMembresia(request.getParameter("fechaInicioMembresia"));
                coleccionista.setTipoMembresia(request.getParameter("tipoMembresia"));
                coleccionista.setPreferencias(request.getParameter("preferencias"));
                coleccionista.setDescuentos(Double.parseDouble(request.getParameter("descuentos")));
                coleccionista.setSuscripciones("on".equals(request.getParameter("suscripciones")));
                coleccionista.setRenovacionAutomatica("on".equals(request.getParameter("renovacionAutomatica")));

                Part imagenPart = request.getPart("imagen");
                if (imagenPart != null && imagenPart.getSize() > 0) {
                    String imagenNombre = imagenPart.getSubmittedFileName();
                    String rutaReal = getServletContext().getRealPath("/") + "resources/imagenes/coleccionistas";

                    File carpeta = new File(rutaReal);
                    if (!carpeta.exists()) carpeta.mkdirs();

                    File archivoImagen = new File(carpeta, imagenNombre);
                    imagenPart.write(archivoImagen.getAbsolutePath());
                    coleccionista.setImagen(imagenNombre);
                }
            }

            response.sendRedirect("coleccionistas");
        } else {
            // Agregar un nuevo coleccionista
            int id = coleccionistas.size() + 1;
            String nombre = request.getParameter("nombre");
            String fechaInicioMembresia = request.getParameter("fechaInicioMembresia");
            String tipoMembresia = request.getParameter("tipoMembresia");
            String preferencias = request.getParameter("preferencias");
            double descuentos = Double.parseDouble(request.getParameter("descuentos"));
            boolean suscripciones = "on".equals(request.getParameter("suscripciones"));
            boolean renovacionAutomatica = "on".equals(request.getParameter("renovacionAutomatica"));

            Part imagenPart = request.getPart("imagen");
            String imagenNombre = null;
            if (imagenPart != null && imagenPart.getSize() > 0) {
                imagenNombre = imagenPart.getSubmittedFileName();
                String rutaReal = getServletContext().getRealPath("/") + "resources/imagenes/coleccionistas";

                File carpeta = new File(rutaReal);
                if (!carpeta.exists()) carpeta.mkdirs();

                File archivoImagen = new File(carpeta, imagenNombre);
                imagenPart.write(archivoImagen.getAbsolutePath());
            }

            Coleccionista nuevoColeccionista = new Coleccionista(id, nombre, fechaInicioMembresia, tipoMembresia, preferencias, descuentos, suscripciones, renovacionAutomatica, imagenNombre);
            coleccionistas.add(nuevoColeccionista);

            response.sendRedirect("coleccionistas");
        }
    }
}
