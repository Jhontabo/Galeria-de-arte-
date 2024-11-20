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
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            return;
        }

        // Buscar el profesional usando el método estático correctamente
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
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            return;
        }

        Profesional profesional = ProfesionalesServlet.getProfesionales().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (profesional == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Profesional no encontrado.");
            return;
        }

        try {
            // Actualizar datos del profesional
            String nombreCompleto = req.getParameter("nombreCompleto");
            String especialidad = req.getParameter("especialidad");
            int aniosExperiencia = Integer.parseInt(req.getParameter("aniosExperiencia"));

            profesional.setNombreCompleto(nombreCompleto);
            profesional.setEspecialidad(especialidad);
            profesional.setAniosExperiencia(aniosExperiencia);
            profesional.setProyectosPrevios(req.getParameter("proyectosPrevios"));
            profesional.setContacto(req.getParameter("contacto"));
            profesional.setInstitucionEducativa(req.getParameter("institucionEducativa"));
            profesional.setPremios(req.getParameter("premios"));
            profesional.setEstiloPreferido(req.getParameter("estiloPreferido"));

            // Procesar imagen si se subió una nueva
            Part imagenPart = req.getPart("imagen");
            if (imagenPart != null && imagenPart.getSize() > 0) {
                String nombreImagen = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = req.getServletContext().getRealPath("/") + RUTA_IMAGENES;
                File carpetaImagenes = new File(uploadPath);

                // Crear directorio si no existe
                if (!carpetaImagenes.exists() && !carpetaImagenes.mkdirs()) {
                    throw new IOException("No se pudo crear el directorio para imágenes.");
                }

                // Guardar la imagen en el servidor
                File archivoImagen = new File(carpetaImagenes, nombreImagen);
                Files.copy(imagenPart.getInputStream(), archivoImagen.toPath(), StandardCopyOption.REPLACE_EXISTING);
                profesional.setImagen(nombreImagen); // Actualizar la imagen del profesional
            }

            // Redirigir a la lista de profesionales después de editar
            resp.sendRedirect("profesionales");

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Años de experiencia inválidos.");
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la imagen.");
        }
    }
}
