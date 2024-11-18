package com.galeria.servlets;

import com.galeria.models.Coleccionista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/coleccionistas")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class ColeccionistasServlet extends HttpServlet {
    private static final String RUTA_IMAGENES = "resources/imagenes/coleccionistas";

    @Override
    public void init() throws ServletException {
        super.init();
        List<Coleccionista> coleccionistas = (List<Coleccionista>) getServletContext().getAttribute("coleccionistas");

        if (coleccionistas == null) {
            coleccionistas = new ArrayList<>();
            coleccionistas.add(new Coleccionista(1, "Luis Martínez", "2020-01-15", "Básica", "Horarios matutinos", 10.0, true, false, "luis.jpg"));
            coleccionistas.add(new Coleccionista(2, "Ana López", "2018-03-10", "Avanzada", "Exposiciones de arte moderno", 15.0, true, true, "ana.jpg"));

            getServletContext().setAttribute("coleccionistas", coleccionistas);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Coleccionista> getColeccionistas(HttpServlet servlet) {
        return (List<Coleccionista>) servlet.getServletContext().getAttribute("coleccionistas");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("coleccionistas", getColeccionistas(this));
        req.getRequestDispatcher("/coleccionistas.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = getColeccionistas(this).size() + 1;
        String nombre = req.getParameter("nombre");
        String fechaInicioMembresia = req.getParameter("fechaInicioMembresia");
        String tipoMembresia = req.getParameter("tipoMembresia");
        String preferencias = req.getParameter("preferencias");

        // Manejar el campo descuentos de manera segura
        String descuentosParam = req.getParameter("descuentos");
        double descuentos = 0.0; // Valor predeterminado
        if (descuentosParam != null && !descuentosParam.trim().isEmpty()) {
            try {
                descuentos = Double.parseDouble(descuentosParam);
            } catch (NumberFormatException e) {
                req.setAttribute("error", "El campo 'descuentos' debe ser un número válido.");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
            }
        }

        boolean suscripciones = "on".equals(req.getParameter("suscripciones"));
        boolean renovacionAutomatica = "on".equals(req.getParameter("renovacionAutomatica"));

        Part imagenPart = req.getPart("imagen");
        String imagenNombre = null;

        if (imagenPart != null && imagenPart.getSize() > 0) {
            imagenNombre = imagenPart.getSubmittedFileName();
            String rutaReal = getServletContext().getRealPath("/") + RUTA_IMAGENES;

            File carpeta = new File(rutaReal);
            if (!carpeta.exists()) carpeta.mkdirs();

            File archivoImagen = new File(carpeta, imagenNombre);
            imagenPart.write(archivoImagen.getAbsolutePath());
        }

        Coleccionista nuevoColeccionista = new Coleccionista(id, nombre, fechaInicioMembresia, tipoMembresia, preferencias, descuentos, suscripciones, renovacionAutomatica, imagenNombre);
        getColeccionistas(this).add(nuevoColeccionista);

        resp.sendRedirect("coleccionistas");
    }

}
