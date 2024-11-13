package com.galeria.servlets;

import com.galeria.models.ObraDeArte;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/obras") // URL para acceder al servlet
public class ObrasServlet extends HttpServlet {
    private List<ObraDeArte> obras; // Lista para simular una base de datos

    @Override
    public void init() throws ServletException {
        // Inicializar datos de ejemplo
        obras = new ArrayList<>();
        obras.add(new ObraDeArte(1, "Mona Lisa", "Leonardo da Vinci", 1503, "Óleo sobre tabla", "77x53 cm", "Exhibición", 85000000.0, "Condición perfecta", "mona_lisa.jpg"));
        obras.add(new ObraDeArte(2, "La noche estrellada", "Vincent van Gogh", 1889, "Óleo sobre lienzo", "73.7x92.1 cm", "Exhibición", 100000000.0, "Conservada en clima controlado", "noche_estrellada.jpg"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostrar lista de obras
        request.setAttribute("obras", obras);
        request.getRequestDispatcher("/obras.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Agregar una nueva obra desde un formulario
        int id = obras.size() + 1; // Generar un ID único
        String titulo = request.getParameter("titulo");
        String artista = request.getParameter("artista");
        int anioCreacion = Integer.parseInt(request.getParameter("anioCreacion"));
        String tecnica = request.getParameter("tecnica");
        String dimensiones = request.getParameter("dimensiones");
        String estado = request.getParameter("estado");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String observaciones = request.getParameter("observaciones");
        String imagen = request.getParameter("imagen");

        // Crear nueva obra
        ObraDeArte nuevaObra = new ObraDeArte(id, titulo, artista, anioCreacion, tecnica, dimensiones, estado, precio, observaciones, imagen);
        obras.add(nuevaObra);

        // Redirigir al listado
        response.sendRedirect("obras");
    }
}
