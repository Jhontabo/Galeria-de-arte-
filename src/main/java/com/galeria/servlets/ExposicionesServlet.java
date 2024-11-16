package com.galeria.servlets;

import com.galeria.models.Exposicion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/exposiciones")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class ExposicionesServlet extends HttpServlet {
    private static final List<Exposicion> exposiciones = new ArrayList<>();
    private static int nextId = 1; // Generador de ID simple
    private static final String RUTA_IMAGENES = "resources/imagenes/exposiciones";

    public static List<Exposicion> getExposiciones() {
        return exposiciones;
    }

    @Override
    public void init() throws ServletException {
        super.init();

        // Agregar datos por defecto
        exposiciones.add(new Exposicion(nextId++, "Arte Moderno", "2024-01-15", "2024-03-15",
                "Luis Fernández", "Arte Contemporáneo", "Sala A",
                "Exposición dedicada al arte moderno con obras destacadas de artistas nacionales.", null));

        exposiciones.add(new Exposicion(nextId++, "Renacimiento Italiano", "2024-04-01", "2024-06-01",
                "Sofía García", "Pintura Renacentista", "Sala B",
                "Una colección única de obras del Renacimiento italiano.", null));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("exposiciones", exposiciones);
        req.getRequestDispatcher("/exposiciones.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            // Manejo de eliminación
            int id = Integer.parseInt(req.getParameter("id"));
            exposiciones.removeIf(expo -> expo.getId() == id);
            resp.sendRedirect("exposiciones");
        } else {
            // Agregar una nueva exposición
            String titulo = req.getParameter("titulo");
            String fechaInicio = req.getParameter("fechaInicio");
            String fechaFin = req.getParameter("fechaFin");
            String responsable = req.getParameter("responsable");
            String tematica = req.getParameter("tematica");
            String salaAsignada = req.getParameter("salaAsignada");
            String descripcion = req.getParameter("descripcion");

            // Manejo de imagen
            Part imagenPart = req.getPart("imagen");
            String nombreImagen = null;
            if (imagenPart != null && imagenPart.getSize() > 0) {
                nombreImagen = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
                String rutaReal = req.getServletContext().getRealPath("/") + RUTA_IMAGENES;
                File carpetaImagenes = new File(rutaReal);

                if (!carpetaImagenes.exists()) {
                    carpetaImagenes.mkdirs(); // Crear la carpeta si no existe
                }

                File archivoImagen = new File(carpetaImagenes, nombreImagen);
                try (InputStream input = imagenPart.getInputStream()) {
                    Files.copy(input, archivoImagen.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }

            Exposicion nuevaExposicion = new Exposicion(nextId++, titulo, fechaInicio, fechaFin, responsable,
                    tematica, salaAsignada, descripcion, nombreImagen);
            exposiciones.add(nuevaExposicion);
            resp.sendRedirect("exposiciones");
        }
    }
}
