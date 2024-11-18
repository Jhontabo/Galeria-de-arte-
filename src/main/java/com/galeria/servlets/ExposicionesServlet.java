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
    private static int nextId = 1;
    private static final String RUTA_IMAGENES = "resources/imagenes/exposiciones";

    public static List<Exposicion> getExposiciones() {
        return exposiciones;
    }

    @Override
    public void init() throws ServletException {
        if (exposiciones.isEmpty()) {
            exposiciones.add(new Exposicion(nextId++, "Arte Moderno", "2024-01-15", "2024-03-15",
                    "Luis Fernández", "Arte Contemporáneo", "Sala A",
                    "Exposición dedicada al arte moderno con obras destacadas de artistas nacionales.", "arte-moderno.jpg"));

            exposiciones.add(new Exposicion(nextId++, "Renacimiento Italiano", "2024-04-01", "2024-06-01",
                    "Sofía García", "Pintura Renacentista", "Sala B",
                    "Una colección única de obras del Renacimiento italiano.", "renacimiento-italiano.jpg"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("exposiciones", exposiciones);
        req.getRequestDispatcher("/exposiciones.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titulo = req.getParameter("titulo");
        String fechaInicio = req.getParameter("fechaInicio");
        String fechaFin = req.getParameter("fechaFin");
        String responsable = req.getParameter("responsable");
        String tematica = req.getParameter("tematica");
        String salaAsignada = req.getParameter("salaAsignada");
        String descripcion = req.getParameter("descripcion");

        Part imagenPart = req.getPart("imagen");
        String nombreImagen = guardarImagen(imagenPart);

        Exposicion nuevaExposicion = new Exposicion(nextId++, titulo, fechaInicio, fechaFin, responsable,
                tematica, salaAsignada, descripcion, nombreImagen);
        exposiciones.add(nuevaExposicion);
        resp.sendRedirect("exposiciones");
    }

    private String guardarImagen(Part imagenPart) throws IOException {
        if (imagenPart != null && imagenPart.getSize() > 0) {
            String nombreImagen = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
            String rutaReal = getServletContext().getRealPath("/") + RUTA_IMAGENES;
            File carpeta = new File(rutaReal);

            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            File archivoImagen = new File(carpeta, nombreImagen);
            try (InputStream input = imagenPart.getInputStream()) {
                Files.copy(input, archivoImagen.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            return nombreImagen;
        }
        return null;
    }
}
