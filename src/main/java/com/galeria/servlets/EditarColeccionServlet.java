package com.galeria.servlets;

import com.galeria.models.Coleccion;
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
import java.util.List;

@WebServlet("/editarColeccion")
@MultipartConfig
public class EditarColeccionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Coleccion> colecciones = (List<Coleccion>) getServletContext().getAttribute("colecciones");
        List<ObraDeArte> obras = (List<ObraDeArte>) getServletContext().getAttribute("obras");

        int id = Integer.parseInt(req.getParameter("id"));
        Coleccion coleccion = colecciones.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (coleccion == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Colección no encontrada.");
            return;
        }

        req.setAttribute("coleccion", coleccion);
        req.setAttribute("obras", obras);
        req.getRequestDispatcher("/editarColeccion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Coleccion> colecciones = (List<Coleccion>) getServletContext().getAttribute("colecciones");

        int id = Integer.parseInt(req.getParameter("id"));
        Coleccion coleccion = colecciones.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (coleccion == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Colección no encontrada.");
            return;
        }

        // Actualizar datos de la colección
        coleccion.setNombre(req.getParameter("nombre"));
        coleccion.setDescripcion(req.getParameter("descripcion"));
        coleccion.setResponsable(req.getParameter("responsable"));
        coleccion.setEstilo(req.getParameter("estilo"));
        coleccion.setFechasExhibicion(req.getParameter("fechasExhibicion"));
        coleccion.setSalaAsignada(req.getParameter("salaAsignada"));
        coleccion.setObservaciones(req.getParameter("observaciones"));

        // Procesar la imagen
        Part imagenPart = req.getPart("imagen");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            String uploadsDir = getServletContext().getRealPath("") + File.separator + "resources"
                    + File.separator + "imagenes" + File.separator + "colecciones";

            File uploads = new File(uploadsDir);
            if (!uploads.exists()) {
                uploads.mkdirs();
            }

            String imagenNombre = "coleccion_" + id + "_" + imagenPart.getSubmittedFileName();
            imagenPart.write(uploadsDir + File.separator + imagenNombre);
            coleccion.setImagen(imagenNombre); // Actualizar imagen
        }

        resp.sendRedirect("colecciones");
    }
}
