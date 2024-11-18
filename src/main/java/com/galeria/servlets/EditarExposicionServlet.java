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

@WebServlet("/editarExposicion")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class EditarExposicionServlet extends HttpServlet {
    private static final String RUTA_IMAGENES = "resources/imagenes/exposiciones";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Exposicion exposicion = ExposicionesServlet.getExposiciones().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (exposicion != null) {
            req.setAttribute("exposicion", exposicion);
            req.getRequestDispatcher("/editarExposicion.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("exposiciones");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Exposicion exposicion = ExposicionesServlet.getExposiciones().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (exposicion != null) {
            exposicion.setTitulo(req.getParameter("titulo"));
            exposicion.setFechaInicio(req.getParameter("fechaInicio"));
            exposicion.setFechaFin(req.getParameter("fechaFin"));
            exposicion.setResponsable(req.getParameter("responsable"));
            exposicion.setTematica(req.getParameter("tematica"));
            exposicion.setSalaAsignada(req.getParameter("salaAsignada"));
            exposicion.setDescripcion(req.getParameter("descripcion"));

            Part imagenPart = req.getPart("imagen");
            if (imagenPart != null && imagenPart.getSize() > 0) {
                String nuevaImagen = guardarImagen(imagenPart);
                exposicion.setImagen(nuevaImagen);
            }

            resp.sendRedirect("exposiciones");
        } else {
            resp.sendRedirect("exposiciones");
        }
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
