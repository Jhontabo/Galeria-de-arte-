package com.galeria.servlets;

import com.galeria.models.Venta;
import com.galeria.models.ObraDeArte;
import com.galeria.models.Coleccionista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
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

@WebServlet("/ventas")
@MultipartConfig
public class VentasServlet extends HttpServlet {
    private List<Venta> ventas;
    private List<ObraDeArte> obras;
    private List<Coleccionista> clientes;

    @Override
    public void init() throws ServletException {
        obras = (List<ObraDeArte>) getServletContext().getAttribute("obras");
        clientes = (List<Coleccionista>) getServletContext().getAttribute("clientes");
        ventas = (List<Venta>) getServletContext().getAttribute("ventas");

        if (obras == null) {
            obras = new ArrayList<>();
            obras.add(new ObraDeArte(1, "Mona Lisa", "Leonardo da Vinci", 1503, "Óleo sobre tabla", "77x53 cm", "Conservada", 1000000.0, "Obra maestra", "monalisa.jpg"));
            obras.add(new ObraDeArte(2, "La noche estrellada", "Vincent van Gogh", 1889, "Óleo sobre lienzo", "73x92 cm", "Conservada", 2000000.0, "Obra icónica", "lanocheestrellada.jpg"));
            getServletContext().setAttribute("obras", obras);
        }

        if (clientes == null) {
            clientes = new ArrayList<>();
            clientes.add(new Coleccionista(1, "Luis Martínez", "2020-01-15", "Básica", "Arte moderno", 10.0, true, false, "luis.jpg"));
            clientes.add(new Coleccionista(2, "Ana López", "2018-03-10", "Avanzada", "Arte renacentista", 15.0, true, true, "ana.jpg"));
            getServletContext().setAttribute("clientes", clientes);
        }

        if (ventas == null) {
            ventas = new ArrayList<>();
            ventas.add(new Venta(1, 1, 1, 1500000.0, "2024-01-01", "Juan Pérez", "Tarjeta", true, "Primera venta registrada", "venta1.jpg"));
            ventas.add(new Venta(2, 2, 2, 2500000.0, "2024-02-15", "María López", "Efectivo", false, "Segunda venta registrada", "venta2.jpg"));
            getServletContext().setAttribute("ventas", ventas);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("ventas", ventas);
        req.setAttribute("obras", obras);
        req.setAttribute("clientes", clientes);
        req.getRequestDispatcher("/ventas.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = ventas.size() + 1;
        int idObra = Integer.parseInt(req.getParameter("idObra"));
        int idCliente = Integer.parseInt(req.getParameter("idCliente"));
        double precioVenta = Double.parseDouble(req.getParameter("precioVenta"));
        String fechaVenta = req.getParameter("fechaVenta");
        String encargadoVenta = req.getParameter("encargadoVenta");
        String metodoPago = req.getParameter("metodoPago");
        boolean facturaGenerada = req.getParameter("facturaGenerada") != null;
        String observaciones = req.getParameter("observaciones");

        // Procesar la imagen
        String imagenNombre = null;
        Part filePart = req.getPart("imagen");
        if (filePart != null && filePart.getSize() > 0) {
            imagenNombre = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("/") + "resources/imagenes/ventas";

            // Crear directorio si no existe
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Guardar el archivo
            File file = new File(uploadDir, imagenNombre);
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }

        // Crear nueva venta
        Venta nuevaVenta = new Venta(id, idObra, idCliente, precioVenta, fechaVenta, encargadoVenta, metodoPago, facturaGenerada, observaciones, imagenNombre);
        ventas.add(nuevaVenta);

        // Actualizar la lista en el contexto y redirigir
        getServletContext().setAttribute("ventas", ventas);
        resp.sendRedirect("ventas");
    }

}
