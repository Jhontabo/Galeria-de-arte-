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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profesionales")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class ProfesionalesServlet extends HttpServlet {
    private static final List<Profesional> profesionales = new ArrayList<>();
    private static int nextId = 1; // Generador de ID simple
    private static final String RUTA_IMAGENES = "resources/imagenes/profesionales";


    public static List<Profesional> getProfesionales() {
        return profesionales;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("profesionales", profesionales);

        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Profesional profesional = profesionales.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (profesional != null) {
                req.setAttribute("profesional", profesional);
                req.getRequestDispatcher("/editarProfesional.jsp").forward(req, resp);
                return;
            }
        }

        req.getRequestDispatcher("/profesionales.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            String idParam = req.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                try {
                    int id = Integer.parseInt(idParam);
                    // Eliminar profesional por ID
                    profesionales.removeIf(profesional -> profesional.getId() == id);
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "El ID proporcionado no es válido.");
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                    return;
                }
            }
        } else {
            agregarProfesional(req, resp); // Lógica existente para agregar profesionales
        }

        resp.sendRedirect("profesionales");
    }


    private void agregarProfesional(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Parámetros del formulario
        String nombreCompleto = req.getParameter("nombreCompleto");
        String especialidad = req.getParameter("especialidad");
        int aniosExperiencia = Integer.parseInt(req.getParameter("aniosExperiencia"));
        String proyectosPrevios = req.getParameter("proyectosPrevios");
        String contacto = req.getParameter("contacto");
        String institucionEducativa = req.getParameter("institucionEducativa");
        String premios = req.getParameter("premios");
        String estiloPreferido = req.getParameter("estiloPreferido");

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

        // Crear un nuevo profesional
        Profesional nuevoProfesional = new Profesional(
                nextId++,
                nombreCompleto,
                especialidad,
                aniosExperiencia,
                proyectosPrevios,
                contacto,
                institucionEducativa,
                premios,
                estiloPreferido,
                nombreImagen // Ruta de la imagen
        );

        profesionales.add(nuevoProfesional);
        resp.sendRedirect("profesionales");
    }

    private void actualizarProfesional(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));

        Profesional profesional = profesionales.stream()
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

                profesional.setImagen(nombreImagen); // Actualizar la ruta de la imagen
            }

            resp.sendRedirect("profesionales");
        } else {
            resp.sendRedirect("profesionales");
        }
    }
}
