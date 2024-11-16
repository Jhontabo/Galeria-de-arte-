package com.galeria.servlets;

import com.galeria.models.ObraDeArte;
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

@WebServlet(name = "editarObraServlet", urlPatterns = {"/editarObra"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class editarObraServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el ID de la obra a editar
        int id = Integer.parseInt(request.getParameter("id"));

        // Buscar la obra correspondiente
        ObraDeArte obra = ObrasServlet.getObras().stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);

        if (obra == null) {
            request.setAttribute("error", "No se encontró la obra con el ID proporcionado.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Pasar la obra como atributo al JSP
        request.setAttribute("obra", obra);
        request.getRequestDispatcher("/editarObra.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el ID de la obra
        int id = Integer.parseInt(request.getParameter("id"));

        // Buscar la obra correspondiente
        ObraDeArte obra = ObrasServlet.getObras().stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);

        if (obra == null) {
            request.setAttribute("error", "No se encontró la obra con el ID proporcionado.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Actualizar los datos de la obra
        obra.setTitulo(request.getParameter("titulo"));
        obra.setArtista(request.getParameter("artista"));
        obra.setTecnica(request.getParameter("tecnica"));
        obra.setDimensiones(request.getParameter("dimensiones"));
        obra.setEstado(request.getParameter("estado"));
        obra.setObservaciones(request.getParameter("observaciones"));
        obra.setPrecio(Double.parseDouble(request.getParameter("precio")));
        obra.setAnioCreacion(Integer.parseInt(request.getParameter("anioCreacion")));

        // Manejo de imagen
        Part filePart = request.getPart("imagen");
        String imagenFileName = obra.getImagen(); // Mantener la imagen actual si no se sube una nueva

        if (filePart != null && filePart.getSize() > 0) {
            imagenFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + "resources/imagenes/obras";
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File file = new File(uploadDir, imagenFileName);
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            obra.setImagen(imagenFileName);
        }

        // Indicar éxito y redirigir
        response.sendRedirect("obras");
    }
}
