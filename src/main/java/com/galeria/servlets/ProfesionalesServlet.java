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
import java.nio.file.Paths;
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
    private static int nextId = 1;

    /**
     * Método para obtener la lista de profesionales.
     * Este método es estático para permitir su acceso desde otros servlets o clases.
     */
    public static List<Profesional> getProfesionales() {
        return profesionales;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        if (profesionales.isEmpty()) {
            profesionales.add(new Profesional(nextId++, "Carlos Martínez", "Restaurador", 10,
                    "Restauración de cuadros renacentistas", "carlos.martinez@gmail.com",
                    "Universidad de Bellas Artes", "Premio Nacional de Restauración", "Clásico", "carlos.jpg"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("profesionales", profesionales);
        req.getRequestDispatcher("/profesionales.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            try {
                agregarProfesional(req, resp);
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error al agregar el profesional: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
        }
    }

    private void agregarProfesional(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String nombreCompleto = req.getParameter("nombreCompleto");
        String especialidad = req.getParameter("especialidad");
        String proyectosPrevios = req.getParameter("proyectosPrevios");
        String contacto = req.getParameter("contacto");
        String institucionEducativa = req.getParameter("institucionEducativa");
        String premios = req.getParameter("premios");
        String estiloPreferido = req.getParameter("estiloPreferido");
        String imagenNombre = null;

        // Validación y conversión de años de experiencia
        int aniosExperiencia;
        try {
            if (nombreCompleto == null || nombreCompleto.trim().isEmpty() ||
                    especialidad == null || especialidad.trim().isEmpty()) {
                throw new IllegalArgumentException("Los campos 'Nombre Completo' y 'Especialidad' son obligatorios.");
            }

            aniosExperiencia = Integer.parseInt(req.getParameter("aniosExperiencia"));
            if (aniosExperiencia < 0) {
                throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo 'Años de Experiencia' debe ser un número válido.");
        }

        // Procesar imagen si existe
        Part imagenPart = req.getPart("imagen");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            try {
                imagenNombre = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("/") + "resources/imagenes/profesionales";
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                    throw new IOException("No se pudo crear el directorio para imágenes.");
                }

                File archivoImagen = new File(uploadDir, imagenNombre);
                imagenPart.write(archivoImagen.getAbsolutePath());
                System.out.println("Imagen guardada en: " + archivoImagen.getAbsolutePath());
            } catch (IOException e) {
                throw new IOException("Error al procesar la imagen: " + e.getMessage());
            }
        }

        // Crear y agregar el nuevo profesional
        Profesional nuevoProfesional = new Profesional(nextId++, nombreCompleto, especialidad, aniosExperiencia,
                proyectosPrevios, contacto, institucionEducativa, premios, estiloPreferido, imagenNombre);
        profesionales.add(nuevoProfesional);
        System.out.println("Profesional agregado: " + nuevoProfesional);

        // Redirección a la lista de profesionales
        resp.sendRedirect("profesionales");
    }
}
