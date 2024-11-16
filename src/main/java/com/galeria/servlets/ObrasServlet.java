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
    private static int nextId = 1; // Generador simple de ID
    private static final String RUTA_IMAGENES = "src/main/webapp/resources/imagenes/obras";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("obras", obras);
        req.getRequestDispatcher("/obras.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Recoge los datos del formulario
            String titulo = req.getParameter("titulo");
            String artista = req.getParameter("artista");
            int anioCreacion = Integer.parseInt(req.getParameter("anioCreacion"));
            String tecnica = req.getParameter("tecnica");
            String dimensiones = req.getParameter("dimensiones");
            String estado = req.getParameter("estado");
            double precio = Double.parseDouble(req.getParameter("precio"));
            String observaciones = req.getParameter("observaciones");

            // Log de datos recibidos
            System.out.println("Datos recibidos:");
            System.out.println("Título: " + titulo);
            System.out.println("Artista: " + artista);
            System.out.println("Año de creación: " + anioCreacion);
            System.out.println("Técnica: " + tecnica);
            System.out.println("Dimensiones: " + dimensiones);
            System.out.println("Estado: " + estado);
            System.out.println("Precio: " + precio);
            System.out.println("Observaciones: " + observaciones);

            // Manejo de la imagen
            Part imagenPart = req.getPart("imagen");
            String imagenNombre = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();

            // Ruta real en el servidor
            String rutaReal = req.getServletContext().getRealPath("/") + "resources/imagenes/obras";
            File carpeta = new File(rutaReal);

            if (!carpeta.exists()) {
                carpeta.mkdirs(); // Crea la carpeta si no existe
                System.out.println("Carpeta creada en: " + rutaReal);
            }

            File archivoImagen = new File(carpeta, imagenNombre);
            try (InputStream input = imagenPart.getInputStream()) {
                Files.copy(input, archivoImagen.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Imagen guardada: " + archivoImagen.getAbsolutePath());
            }

            // Crea una nueva obra de arte
            ObraDeArte nuevaObra = new ObraDeArte(nextId++, titulo, artista, anioCreacion, tecnica, dimensiones, estado, precio, observaciones, imagenNombre);

            // Agrega la nueva obra a la lista
            obras.add(nuevaObra);
            System.out.println("Obra añadida a la lista: " + nuevaObra);

            // Verifica el contenido de la lista
            System.out.println("Contenido actual de obras:");
            for (ObraDeArte obra : obras) {
                System.out.println(obra);
            }

            // Redirige al listado para actualizar la tabla
            resp.sendRedirect("obras");

        } catch (Exception e) {
            System.out.println("Error al procesar el formulario: " + e.getMessage());
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar la solicitud.");
        }
    }

}
