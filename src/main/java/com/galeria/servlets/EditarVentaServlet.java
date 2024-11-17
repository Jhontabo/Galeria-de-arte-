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
import java.util.List;

@WebServlet("/editarVenta")
@MultipartConfig // Permite manejar multipart/form-data para carga de archivos
public class EditarVentaServlet extends HttpServlet {

    private List<Venta> ventas;
    private List<ObraDeArte> obras;
    private List<Coleccionista> clientes;

    @Override
    public void init() throws ServletException {
        ventas = (List<Venta>) getServletContext().getAttribute("ventas");
        obras = (List<ObraDeArte>) getServletContext().getAttribute("obras");
        clientes = (List<Coleccionista>) getServletContext().getAttribute("clientes");

        if (ventas == null || obras == null || clientes == null) {
            throw new ServletException("Las listas de datos no se han inicializado correctamente.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el ID de la venta desde los parámetros
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID de la venta es requerido");
            return;
        }

        int id = Integer.parseInt(idParam);

        // Buscar la venta
        Venta venta = ventas.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);

        if (venta == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Venta no encontrada");
            return;
        }

        // Pasar los datos necesarios al JSP
        request.setAttribute("venta", venta);
        request.setAttribute("obras", obras);
        request.setAttribute("clientes", clientes);

        // Redirigir al JSP de edición
        request.getRequestDispatcher("/editarVenta.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idObra = Integer.parseInt(request.getParameter("idObra"));
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
        String fechaVenta = request.getParameter("fechaVenta");
        String encargadoVenta = request.getParameter("encargadoVenta");
        String metodoPago = request.getParameter("metodoPago");
        boolean facturaGenerada = request.getParameter("facturaGenerada") != null;
        String observaciones = request.getParameter("observaciones");

        // Procesar la imagen
        Part imagenPart = request.getPart("imagen");
        String nombreImagen = null;
        if (imagenPart != null && imagenPart.getSize() > 0) {
            nombreImagen = imagenPart.getSubmittedFileName();
            String rutaImagenes = getServletContext().getRealPath("/") + "resources/imagenes/ventas";
            File carpetaImagenes = new File(rutaImagenes);

            // Crear carpeta si no existe
            if (!carpetaImagenes.exists()) {
                carpetaImagenes.mkdirs();
            }

            // Guardar la imagen
            File archivoImagen = new File(carpetaImagenes, nombreImagen);
            imagenPart.write(archivoImagen.getAbsolutePath());
        }

        // Actualizar la venta
        Venta venta = ventas.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);

        if (venta != null) {
            venta.setIdObra(idObra);
            venta.setIdCliente(idCliente);
            venta.setPrecioVenta(precioVenta);
            venta.setFechaVenta(fechaVenta);
            venta.setEncargadoVenta(encargadoVenta);
            venta.setMetodoPago(metodoPago);
            venta.setFacturaGenerada(facturaGenerada);
            venta.setObservaciones(observaciones);

            // Actualizar la imagen si se cargó una nueva
            if (nombreImagen != null) {
                venta.setImagen(nombreImagen);
            }
        }

        // Redirigir al listado de ventas
        response.sendRedirect("ventas");
    }
}
