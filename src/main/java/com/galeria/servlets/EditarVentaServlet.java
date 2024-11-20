package com.galeria.servlets;

import com.galeria.models.Venta;
import com.galeria.models.ObraDeArte;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@WebServlet("/editarVenta")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class EditarVentaServlet extends HttpServlet {

    private List<Venta> obtenerVentas(HttpServletRequest request) {
        return (List<Venta>) getServletContext().getAttribute("ventas");
    }

    private List<ObraDeArte> obtenerObras(HttpServletRequest request) {
        return (List<ObraDeArte>) getServletContext().getAttribute("obras");
    }

    private List<Coleccionista> obtenerClientes(HttpServletRequest request) {
        return (List<Coleccionista>) getServletContext().getAttribute("clientes");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener listas del contexto
        List<Venta> ventas = obtenerVentas(request);
        List<ObraDeArte> obras = obtenerObras(request);
        List<Coleccionista> clientes = obtenerClientes(request);

        if (ventas == null || obras == null || clientes == null) {
            request.setAttribute("error", "Los datos necesarios no están inicializados.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Obtener el ID de la venta
        int id = Integer.parseInt(request.getParameter("id"));

        // Buscar la venta
        Venta venta = ventas.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);

        if (venta == null) {
            request.setAttribute("error", "No se encontró la venta con el ID proporcionado.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Pasar datos al JSP
        request.setAttribute("venta", venta);
        request.setAttribute("obras", obras);
        request.setAttribute("clientes", clientes);

        request.getRequestDispatcher("/editarVenta.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener listas del contexto
        List<Venta> ventas = obtenerVentas(request);

        if (ventas == null) {
            request.setAttribute("error", "La lista de ventas no está inicializada.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Obtener el ID de la venta
        int id = Integer.parseInt(request.getParameter("id"));

        // Buscar la venta
        Venta venta = ventas.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);

        if (venta == null) {
            request.setAttribute("error", "No se encontró la venta con el ID proporcionado.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Actualizar los datos de la venta
        venta.setIdObra(Integer.parseInt(request.getParameter("idObra")));
        venta.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
        venta.setPrecioVenta(Double.parseDouble(request.getParameter("precioVenta")));
        venta.setFechaVenta(request.getParameter("fechaVenta"));
        venta.setEncargadoVenta(request.getParameter("encargadoVenta"));
        venta.setMetodoPago(request.getParameter("metodoPago"));
        venta.setFacturaGenerada(request.getParameter("facturaGenerada") != null);
        venta.setObservaciones(request.getParameter("observaciones"));

        // Manejo de la imagen
        Part filePart = request.getPart("imagen");
        String imagenFileName = venta.getImagen(); // Mantener la imagen actual si no se sube una nueva

        if (filePart != null && filePart.getSize() > 0) {
            imagenFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("/") + "resources/imagenes/ventas";
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File file = new File(uploadDir, imagenFileName);
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            venta.setImagen(imagenFileName);
        }

        // Guardar cambios en la lista de ventas
        getServletContext().setAttribute("ventas", ventas);

        // Redirigir al listado de ventas
        response.sendRedirect("ventas");
    }
}
