<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.galeria.models.Venta, com.galeria.models.ObraDeArte, com.galeria.models.Coleccionista" %>
<%
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
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #f0f4f8;
            color: #333;
        }

        h1 {
            text-align: center;
            margin: 20px 0;
            font-size: 2rem;
            color: #333;
        }

        form {
            max-width: 800px;
            margin: 20px auto;
            background: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }

        label {
            font-weight: bold;
            color: #555;
        }

        input[type="text"],
        input[type="number"],
        input[type="date"],
        textarea,
        select {
            width: 100%;
            padding: 10px;
            margin: 5px 0 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
        }

        input[type="checkbox"] {
            transform: scale(1.2);
            margin: 0 10px 10px 0;
        }

        button {
            grid-column: span 2;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        select {
            cursor: pointer;
        }

        img {
            max-width: 200px;
            height: auto;
            margin-top: 10px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        @media (max-width: 600px) {
            form {
                grid-template-columns: 1fr;
            }

            button {
                font-size: 0.9rem;
            }
        }
    </style>
</head>
<body>
    <h1>Editar Venta</h1>
    <form action="editarVenta" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<%= venta.getId() %>">

        <div>
            <label for="idObra">Obra:</label>
            <select id="idObra" name="idObra">
                <% for (ObraDeArte obra : obras) { %>
                    <option value="<%= obra.getId() %>" <%= obra.getId() == venta.getIdObra() ? "selected" : "" %>>
                        <%= obra.getTitulo() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div>
            <label for="idCliente">Cliente:</label>
            <select id="idCliente" name="idCliente">
                <% for (Coleccionista cliente : clientes) { %>
                    <option value="<%= cliente.getId() %>" <%= cliente.getId() == venta.getIdCliente() ? "selected" : "" %>>
                        <%= cliente.getNombre() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div>
            <label for="precioVenta">Precio de Venta:</label>
            <input type="number" id="precioVenta" name="precioVenta" value="<%= venta.getPrecioVenta() %>" step="0.01" required>
        </div>

        <div>
            <label for="fechaVenta">Fecha de Venta:</label>
            <input type="date" id="fechaVenta" name="fechaVenta" value="<%= venta.getFechaVenta() %>" required>
        </div>

        <div>
            <label for="encargadoVenta">Encargado de Venta:</label>
            <input type="text" id="encargadoVenta" name="encargadoVenta" value="<%= venta.getEncargadoVenta() %>" required>
        </div>

        <div>
            <label for="metodoPago">Método de Pago:</label>
            <select id="metodoPago" name="metodoPago">
                <option value="Efectivo" <%= "Efectivo".equals(venta.getMetodoPago()) ? "selected" : "" %>>Efectivo</option>
                <option value="Tarjeta" <%= "Tarjeta".equals(venta.getMetodoPago()) ? "selected" : "" %>>Tarjeta</option>
                <option value="Transferencia" <%= "Transferencia".equals(venta.getMetodoPago()) ? "selected" : "" %>>Transferencia</option>
            </select>
        </div>

        <div>
            <label for="facturaGenerada">Factura Generada:</label>
            <input type="checkbox" id="facturaGenerada" name="facturaGenerada" <%= venta.isFacturaGenerada() ? "checked" : "" %>>
        </div>

        <div>
            <label for="observaciones">Observaciones:</label>
            <textarea id="observaciones" name="observaciones"><%= venta.getObservaciones() %></textarea>
        </div>

        <div>
            <label for="imagen">Imagen:</label>
            <input type="file" id="imagen" name="imagen" accept="image/*">
        </div>

        <div>
            <% if (venta.getImagen() != null && !venta.getImagen().isEmpty()) { %>
                <img src="resources/imagenes/ventas/<%= venta.getImagen() %>" alt="Imagen actual de la venta">
            <% } else { %>
                <p>No hay imagen asociada.</p>
            <% } %>
        </div>

        <button type="submit">Actualizar Venta</button>
    </form>
</body>
</html>
