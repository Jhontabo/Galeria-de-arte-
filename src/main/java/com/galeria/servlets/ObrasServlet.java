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
    private static int nextId = 1;

    @Override
    public void init() throws ServletException {
        super.init();

        // Obtener la lista de obras desde el contexto de la aplicación
        List<ObraDeArte> obras = (List<ObraDeArte>) getServletContext().getAttribute("obras");

        // Si la lista no existe, inicializarla
        if (obras == null) {
            obras = new ArrayList<>();
            obras.add(new ObraDeArte(nextId++, "Mona Lisa", "Leonardo da Vinci", 1503, "Óleo sobre tabla", "77 cm × 53 cm", "Exhibición", 780000000, "Obra famosa del Renacimiento", "mona-lisa.jpg"));
            obras.add(new ObraDeArte(nextId++, "La noche estrellada", "Vincent van Gogh", 1889, "Óleo sobre lienzo", "73.7 cm × 92.1 cm", "Exhibición", 110000000, "Inspirada en el sanatorio de Saint-Rémy", "noche-estrellada.jpg"));
            obras.add(new ObraDeArte(nextId++, "El grito", "Edvard Munch", 1893, "Óleo, temple y pastel sobre cartón", "91 cm × 73.5 cm", "Exhibición", 120000000, "Expresión del modernismo", "el-grito.jpg"));

            // Guardar la lista de obras en el contexto de la aplicación
            getServletContext().setAttribute("obras", obras);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener la lista de obras desde el contexto de la aplicación
        List<ObraDeArte> obras = (List<ObraDeArte>) getServletContext().getAttribute("obras");

        // Pasar la lista de obras al JSP
        req.setAttribute("obras", obras);
        req.getRequestDispatcher("/obras.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener la lista de obras desde el contexto de la aplicación
        List<ObraDeArte> obras = (List<ObraDeArte>) getServletContext().getAttribute("obras");

        String titulo = req.getParameter("titulo");
        String artista = req.getParameter("artista");
        int anioCreacion = Integer.parseInt(req.getParameter("anioCreacion"));
        String tecnica = req.getParameter("tecnica");
        String dimensiones = req.getParameter("dimensiones");
        String estado = req.getParameter("estado");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String observaciones = req.getParameter("observaciones");

        // Procesar la imagen
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

        // Crear una nueva obra y agregarla a la lista
        ObraDeArte nuevaObra = new ObraDeArte(nextId++, titulo, artista, anioCreacion, tecnica, dimensiones, estado, precio, observaciones, imagenNombre);
        obras.add(nuevaObra);

        // Redirigir al listado de obras
        resp.sendRedirect("obras");
    }
}
