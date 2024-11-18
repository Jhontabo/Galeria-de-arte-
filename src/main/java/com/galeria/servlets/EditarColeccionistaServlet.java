package com.galeria.servlets;

import com.galeria.models.Coleccionista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/editarColeccionista")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class EditarColeccionistaServlet extends HttpServlet {
    private static final String RUTA_IMAGENES = "resources/imagenes/coleccionistas";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Coleccionista> coleccionistas = ColeccionistasServlet.getColeccionistas(this);

        Coleccionista coleccionista = coleccionistas.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (coleccionista != null) {
            req.setAttribute("coleccionista", coleccionista);
            req.getRequestDispatcher("/editarColeccionista.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("coleccionistas");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Coleccionista> coleccionistas = ColeccionistasServlet.getColeccionistas(this);

        Coleccionista coleccionista = coleccionistas.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (coleccionista != null) {
            coleccionista.setNombre(req.getParameter("nombre"));
            coleccionista.setFechaInicioMembresia(req.getParameter("fechaInicioMembresia"));
            coleccionista.setTipoMembresia(req.getParameter("tipoMembresia"));
            coleccionista.setPreferencias(req.getParameter("preferencias"));
            coleccionista.setDescuentos(Double.parseDouble(req.getParameter("descuentos")));
            coleccionista.setSuscripciones("on".equals(req.getParameter("suscripciones")));
            coleccionista.setRenovacionAutomatica("on".equals(req.getParameter("renovacionAutomatica")));

            Part imagenPart = req.getPart("imagen");
            if (imagenPart != null && imagenPart.getSize() > 0) {
                String imagenNombre = imagenPart.getSubmittedFileName();
                String rutaReal = getServletContext().getRealPath("/") + RUTA_IMAGENES;

                File carpeta = new File(rutaReal);
                if (!carpeta.exists()) carpeta.mkdirs();

                File archivoImagen = new File(carpeta, imagenNombre);
                imagenPart.write(archivoImagen.getAbsolutePath());
                coleccionista.setImagen(imagenNombre);
            }

            resp.sendRedirect("coleccionistas");
        } else {
            resp.sendRedirect("coleccionistas");
        }
    }
}
