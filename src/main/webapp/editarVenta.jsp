<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.galeria.models.Venta" %>
<%@ page import="com.galeria.models.ObraDeArte" %>
<%@ page import="com.galeria.models.Coleccionista" %>
<%@ page import="java.util.List" %>

<%
    // Obtener datos del servlet
    Venta venta = (Venta) request.getAttribute("venta");
    List<ObraDeArte> obras = (List<ObraDeArte>) request.getAttribute("obras");
    List<Coleccionista> clientes = (List<Coleccionista>) request.getAttribute("clientes");

    if (venta == null || obras == null || clientes == null) {
        throw new IllegalStateException("Datos necesarios para la edición no disponibles.");
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Venta</title>
    <link rel="stylesheet" href="css/tablas.css">
</head>
<body>
    <h1>Editar Venta</h1>
    <form action="editarVenta" method="post" enctype="multipart/form-data" class="styled-form">
        <!-- Campo oculto para enviar el ID de la venta -->
        <input type="hidden" name="id" value="<%= venta.getId() %>">

        <!-- Selección de la obra vendida -->
        <div class="form-group">
            <label for="idObra">Obra Vendida:</label>
            <select id="idObra" name="idObra" required>
                <% for (ObraDeArte obra : obras) { %>
                    <option value="<%= obra.getId() %>" <%= obra.getId() == venta.getIdObra() ? "selected" : "" %>>
                        <%= obra.getTitulo() %>
                    </option>
                <% } %>
            </select>
        </div>

        <!-- Selección del cliente -->
        <div class="form-group">
            <label for="idCliente">Cliente:</label>
            <select id="idCliente" name="idCliente" required>
                <% for (Coleccionista cliente : clientes) { %>
                    <option value="<%= cliente.getId() %>" <%= cliente.getId() == venta.getIdCliente() ? "selected" : "" %>>
                        <%= cliente.getNombre() %>
                    </option>
                <% } %>
            </select>
        </div>

        <!-- Precio de la venta -->
        <div class="form-group">
            <label for="precioVenta">Precio Venta:</label>
            <input type="number" id="precioVenta" name="precioVenta" step="0.01" value="<%= venta.getPrecioVenta() %>" required>
        </div>

        <!-- Fecha de la venta -->
        <div class="form-group">
            <label for="fechaVenta">Fecha de Venta:</label>
            <input type="date" id="fechaVenta" name="fechaVenta" value="<%= venta.getFechaVenta() %>" required>
        </div>

        <!-- Encargado de la venta -->
        <div class="form-group">
            <label for="encargadoVenta">Encargado:</label>
            <input type="text" id="encargadoVenta" name="encargadoVenta" value="<%= venta.getEncargadoVenta() %>" required>
        </div>

        <!-- Método de pago -->
        <div class="form-group">
            <label for="metodoPago">Método de Pago:</label>
            <select id="metodoPago" name="metodoPago">
                <option value="Efectivo" <%= "Efectivo".equals(venta.getMetodoPago()) ? "selected" : "" %>>Efectivo</option>
                <option value="Tarjeta" <%= "Tarjeta".equals(venta.getMetodoPago()) ? "selected" : "" %>>Tarjeta</option>
                <option value="Transferencia" <%= "Transferencia".equals(venta.getMetodoPago()) ? "selected" : "" %>>Transferencia</option>
            </select>
        </div>

        <!-- Factura generada -->
        <div class="form-group">
            <label for="facturaGenerada">Factura Generada:</label>
            <input type="checkbox" id="facturaGenerada" name="facturaGenerada" <%= venta.isFacturaGenerada() ? "checked" : "" %>>
        </div>

        <!-- Observaciones -->
        <div class="form-group">
            <label for="observaciones">Observaciones:</label>
            <textarea id="observaciones" name="observaciones"><%= venta.getObservaciones() %></textarea>
        </div>

        <!-- Imagen actual -->
        <div class="form-group">
            <label>Imagen actual:</label>
            <% if (venta.getImagen() != null && !venta.getImagen().isEmpty()) { %>
                <img src="resources/imagenes/ventas/<%= venta.getImagen() %>" alt="Imagen de la venta" width="100">
            <% } else { %>
                <p>No hay imagen disponible.</p>
            <% } %>
        </div>

        <!-- Campo para cargar nueva imagen -->
        <div class="form-group">
            <label for="imagen">Actualizar Imagen:</label>
            <input type="file" id="imagen" name="imagen" accept="image/*">
        </div>

        <!-- Botón de envío -->
        <button type="submit" class="btn-submit">Actualizar Venta</button>
    </form>
</body>
</html>
