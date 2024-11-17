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
import java.util.stream.Collectors;

@WebServlet("/editarColeccion")
@MultipartConfig // Habilita multipart/form-data
public class EditarColeccionServlet extends HttpServlet {

    private List<Coleccion> colecciones;
    private List<ObraDeArte> obras;

    @Override
    public void init() throws ServletException {
        colecciones = (List<Coleccion>) getServletContext().getAttribute("colecciones");
        obras = (List<ObraDeArte>) getServletContext().getAttribute("obras");

        if (colecciones == null || obras == null) {
            throw new ServletException("Datos no inicializados.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Coleccion coleccion = colecciones.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (coleccion == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Colección no encontrada.");
            return;
        }

        request.setAttribute("coleccion", coleccion);
        request.setAttribute("obras", obras);
        request.getRequestDispatcher("/editarColeccion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Coleccion coleccion = colecciones.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (coleccion == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Colección no encontrada.");
            return;
        }

        // Actualizar los datos de la colección
        coleccion.setNombre(request.getParameter("nombre"));
        coleccion.setDescripcion(request.getParameter("descripcion"));
        coleccion.setResponsable(request.getParameter("responsable"));
        coleccion.setEstilo(request.getParameter("estilo"));
        coleccion.setFechasExhibicion(request.getParameter("fechasExhibicion"));
        coleccion.setSalaAsignada(request.getParameter("salaAsignada"));
        coleccion.setObservaciones(request.getParameter("observaciones"));

        // Obtener las obras seleccionadas
        String[] obrasIds = request.getParameterValues("obrasIncluidas");
        if (obrasIds != null) {
            List<Integer> obrasIncluidas = List.of(obrasIds).stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            coleccion.setObrasIncluidas(obrasIncluidas);
        }

        // Manejar el archivo de imagen
        Part imagenPart = request.getPart("imagen");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            String nombreArchivo = imagenPart.getSubmittedFileName();
            String carpetaDestino = getServletContext().getRealPath("/resources/imagenes/colecciones");

            // Validar que la carpeta existe o crearla
            File carpeta = new File(carpetaDestino);
            if (!carpeta.exists()) {
                carpeta.mkdirs(); // Crea la carpeta y subcarpetas si no existen
            }

            String rutaDestino = carpetaDestino + File.separator + nombreArchivo;

            // Guardar el archivo en la ruta destino
            imagenPart.write(rutaDestino);

            // Actualizar la referencia a la imagen en el objeto
            coleccion.setImagen(nombreArchivo);
        }

        response.sendRedirect("colecciones");
    }

}
