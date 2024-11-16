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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/obras")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class ObrasServlet extends HttpServlet {
    private static final List<ObraDeArte> obras = new ArrayList<>();
    private static int nextId = 1;

    public static List<ObraDeArte> getObras() {
        return obras;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("obras", obras);
        req.getRequestDispatcher("/obras.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            obras.removeIf(obra -> obra.getId() == id);
            resp.sendRedirect("obras");
        } else {
            String titulo = req.getParameter("titulo");
            String artista = req.getParameter("artista");
            int anioCreacion = Integer.parseInt(req.getParameter("anioCreacion"));
            String tecnica = req.getParameter("tecnica");
            String dimensiones = req.getParameter("dimensiones");
            String estado = req.getParameter("estado");
            double precio = Double.parseDouble(req.getParameter("precio"));
            String observaciones = req.getParameter("observaciones");

            Part imagenPart = req.getPart("imagen");
            String imagenNombre = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
            String rutaReal = req.getServletContext().getRealPath("/") + "resources/imagenes/obras";
            File carpeta = new File(rutaReal);

            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            File archivoImagen = new File(carpeta, imagenNombre);
            try (InputStream input = imagenPart.getInputStream()) {
                Files.copy(input, archivoImagen.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            ObraDeArte nuevaObra = new ObraDeArte(nextId++, titulo, artista, anioCreacion, tecnica, dimensiones, estado, precio, observaciones, imagenNombre);
            obras.add(nuevaObra);
            resp.sendRedirect("obras");
        }
    }
}
