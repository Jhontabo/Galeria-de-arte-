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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/colecciones")
@MultipartConfig
public class ColeccionesServlet extends HttpServlet {

    private List<Coleccion> colecciones;
    private List<ObraDeArte> obras;

    @Override
    public void init() throws ServletException {
        obras = new ArrayList<>();
        obras.add(new ObraDeArte(1, "Mona Lisa", "Leonardo da Vinci", 1503, "Óleo", "77x53 cm", "Conservada", 1000000, "Famosa obra", "monalisa.jpg"));
        obras.add(new ObraDeArte(2, "La noche estrellada", "Vincent van Gogh", 1889, "Óleo", "73x92 cm", "Conservada", 2000000, "Obra maestra", "noche.jpg"));

        colecciones = new ArrayList<>();
        colecciones.add(new Coleccion(1, "Renacimiento", "Obras renacentistas", "Luis Martínez", "Clásico", Arrays.asList(1), "2024-01-01 a 2024-03-01", "Sala 1", "Exhibición principal", "imagen.jpg"));

        getServletContext().setAttribute("colecciones", colecciones);
        getServletContext().setAttribute("obras", obras);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("colecciones", colecciones);
        request.setAttribute("obras", obras);
        request.getRequestDispatcher("/colecciones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            handleDelete(request, response);
        } else {
            handleAdd(request, response);
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        colecciones.removeIf(coleccion -> coleccion.getId() == id);
        response.sendRedirect("colecciones");
    }

    private void handleAdd(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = colecciones.size() + 1;
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String responsable = request.getParameter("responsable");
        String estilo = request.getParameter("estilo");
        String fechasExhibicion = request.getParameter("fechasExhibicion");
        String salaAsignada = request.getParameter("salaAsignada");
        String observaciones = request.getParameter("observaciones");

        Part imagenPart = request.getPart("imagen");
        String imagenNombre = null;
        if (imagenPart != null && imagenPart.getSize() > 0) {
            String uploadsDir = getServletContext().getRealPath("") + File.separator + "resources"
                    + File.separator + "imagenes" + File.separator + "colecciones";

            File uploads = new File(uploadsDir);
            if (!uploads.exists()) {
                uploads.mkdirs();
            }

            imagenNombre = "coleccion_" + id + "_" + imagenPart.getSubmittedFileName();
            imagenPart.write(uploadsDir + File.separator + imagenNombre);
        }

        String[] obrasIds = request.getParameterValues("obrasIncluidas");
        List<Integer> obrasIncluidas = new ArrayList<>();
        if (obrasIds != null) {
            for (String obraId : obrasIds) {
                obrasIncluidas.add(Integer.parseInt(obraId));
            }
        }

        Coleccion nuevaColeccion = new Coleccion(
                id,
                nombre,
                descripcion,
                responsable,
                estilo,
                obrasIncluidas,
                fechasExhibicion,
                salaAsignada,
                observaciones,
                imagenNombre
        );

        colecciones.add(nuevaColeccion);
        response.sendRedirect("colecciones");
    }
}
