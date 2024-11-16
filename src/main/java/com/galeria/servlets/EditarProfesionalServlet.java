package com.galeria.servlets;

import com.galeria.models.Profesional;
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

@WebServlet("/editarProfesional")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class EditarProfesionalServlet extends HttpServlet {
    private static final String RUTA_IMAGENES = "resources/imagenes/profesionales";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Profesional profesional = ProfesionalesServlet.getProfesionales().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (profesional != null) {
            req.setAttribute("profesional", profesional);
            req.getRequestDispatcher("/editarProfesional.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("profesionales");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Profesional profesional = ProfesionalesServlet.getProfesionales().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (profesional != null) {
            // Actualizar datos del profesional
            profesional.setNombreCompleto(req.getParameter("nombreCompleto"));
            profesional.setEspecialidad(req.getParameter("especialidad"));
            profesional.setAniosExperiencia(Integer.parseInt(req.getParameter("aniosExperiencia")));
            profesional.setProyectosPrevios(req.getParameter("proyectosPrevios"));
            profesional.setContacto(req.getParameter("contacto"));
            profesional.setInstitucionEducativa(req.getParameter("institucionEducativa"));
            profesional.setPremios(req.getParameter("premios"));
            profesional.setEstiloPreferido(req.getParameter("estiloPreferido"));

            // Manejo de imagen
            Part imagenPart = req.getPart("imagen");
            if (imagenPart != null && imagenPart.getSize() > 0) {
                String nombreImagen = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
                String rutaReal = req.getServletContext().getRealPath("/") + RUTA_IMAGENES;
                File carpetaImagenes = new File(rutaReal);

                if (!carpetaImagenes.exists()) {
                    carpetaImagenes.mkdirs();
                }

                File archivoImagen = new File(carpetaImagenes, nombreImagen);
                try (InputStream input = imagenPart.getInputStream()) {
                    Files.copy(input, archivoImagen.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                profesional.setImagen(nombreImagen); // Actualiza la ruta de la imagen
            }

            resp.sendRedirect("profesionales");
        } else {
            resp.sendRedirect("profesionales");
        }
    }
}
