<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.galeria.models.ObraDeArte" %>
            <%@ page import="com.galeria.models.Coleccionista" %>
                <%@ page import="com.galeria.models.Venta" %>

                    <% List<Venta> ventas = (List<Venta>) request.getAttribute("ventas");
                            List<ObraDeArte> obras = (List<ObraDeArte>) request.getAttribute("obras");
                                    List<Coleccionista> clientes = (List<Coleccionista>)
                                            request.getAttribute("clientes");
                                            %>

                                            <!DOCTYPE html>
                                            <html lang="es">

                                            <head>
                                                <meta charset="UTF-8">
                                                <title>Ventas</title>
                                                <link rel="stylesheet" href="css/tablas.css">
                                            </head>

                                            <body>
                                                <nav class="navbar">
                                                    <h2 class="logo">Galería de Arte</h2>
                                                    <ul>
                                                        <li><a href="index.html">Inicio</a></li>
                                                        <li><a href="obras">Obras de Arte</a></li>
                                                        <li><a href="profesionales">Profesionales</a></li>
                                                        <li><a href="exposiciones">Exposiciones</a></li>
                                                        <li><a href="coleccionistas">Coleccionistas</a></li>
                                                        <li><a href="ventas" class="active">Ventas</a></li>
                                                        <li><a href="colecciones">Colecciones</a></li>
                                                        <li><a href="contacto.html">Contacto</a></li>
                                                    </ul>
                                                </nav>

                                                <div class="content">
                                                    <h1>Lista de Ventas</h1>
                                                    <table class="styled-table">
                                                        <thead>
                                                            <tr>
                                                                <th>ID</th>
                                                                <th>Obra Vendida</th>
                                                                <th>Cliente</th>
                                                                <th>Precio</th>
                                                                <th>Fecha</th>
                                                                <th>Encargado</th>
                                                                <th>Método de Pago</th>
                                                                <th>Factura Generada</th>
                                                                <th>Observaciones</th>
                                                                <th>Imagen</th>
                                                                <th>Acciones</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <% if (ventas !=null && !ventas.isEmpty()) { %>
                                                                <% for (Venta venta : ventas) { %>
                                                                    <tr>
                                                                        <td>
                                                                            <%= venta.getId() %>
                                                                        </td>
                                                                        <td>
                                                                            <% ObraDeArte obra=obras.stream() .filter(o
                                                                                -> o.getId() == venta.getIdObra())
                                                                                .findFirst()
                                                                                .orElse(null);
                                                                                out.print(obra != null ?
                                                                                obra.getTitulo() : "Desconocida");
                                                                                %>
                                                                        </td>
                                                                        <td>
                                                                            <% Coleccionista cliente=clientes.stream()
                                                                                .filter(c -> c.getId() ==
                                                                                venta.getIdCliente())
                                                                                .findFirst()
                                                                                .orElse(null);
                                                                                out.print(cliente != null ?
                                                                                cliente.getNombre() : "Desconocido");
                                                                                %>
                                                                        </td>
                                                                        <td>
                                                                            <%= venta.getPrecioVenta() %> USD
                                                                        </td>
                                                                        <td>
                                                                            <%= venta.getFechaVenta() %>
                                                                        </td>
                                                                        <td>
                                                                            <%= venta.getEncargadoVenta() %>
                                                                        </td>
                                                                        <td>
                                                                            <%= venta.getMetodoPago() %>
                                                                        </td>
                                                                        <td>
                                                                            <%= venta.isFacturaGenerada() ? "Sí" : "No"
                                                                                %>
                                                                        </td>
                                                                        <td>
                                                                            <%= venta.getObservaciones() %>
                                                                        </td>
                                                                        <td>
                                                                            <% if (venta.getImagen() !=null &&
                                                                                !venta.getImagen().isEmpty()) { %>
                                                                                <img src="resources/imagenes/ventas/<%= venta.getImagen() %>"
                                                                                    alt="Imagen de venta" width="50">
                                                                                <% } else { %>
                                                                                    No disponible
                                                                                    <% } %>
                                                                        </td>
                                                                        <td>
                                                                            <form action="editarVenta" method="get"
                                                                                style="display:inline;">
                                                                                <input type="hidden" name="id"
                                                                                    value="<%= venta.getId() %>">
                                                                                <button type="submit"
                                                                                    class="btn-edit">Editar</button>
                                                                            </form>
                                                                            <form action="ventas" method="post"
                                                                                style="display:inline;">
                                                                                <input type="hidden" name="action"
                                                                                    value="delete">
                                                                                <input type="hidden" name="id"
                                                                                    value="<%= venta.getId() %>">
                                                                                <button type="submit" class="btn-delete"
                                                                                    onclick="return confirm('¿Está seguro de eliminar esta venta?');">Eliminar</button>
                                                                            </form>
                                                                        </td>

                                                                    </tr>
                                                                    <% } %>
                                                                        <% } else { %>
                                                                            <tr>
                                                                                <td colspan="11">No hay ventas
                                                                                    registradas.</td>
                                                                            </tr>
                                                                            <% } %>
                                                        </tbody>
                                                    </table>

                                                    <h2 class="form-title">Agregar una Nueva Venta</h2>
                                                    <form action="ventas" method="post" class="styled-form"
                                                        enctype="multipart/form-data">
                                                        <div class="form-row">
                                                            <div class="form-group">
                                                                <label for="idObra">Obra:</label>
                                                                <select id="idObra" name="idObra" required>
                                                                    <% for (ObraDeArte obra : obras) { %>
                                                                        <option value="<%= obra.getId() %>">
                                                                            <%= obra.getTitulo() %>
                                                                        </option>
                                                                        <% } %>
                                                                </select>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="idCliente">Cliente:</label>
                                                                <select id="idCliente" name="idCliente" required>
                                                                    <% for (Coleccionista cliente : clientes) { %>
                                                                        <option value="<%= cliente.getId() %>">
                                                                            <%= cliente.getNombre() %>
                                                                        </option>
                                                                        <% } %>
                                                                </select>
                                                            </div>
                                                        </div>

                                                        <div class="form-row">
                                                            <div class="form-group">
                                                                <label for="precioVenta">Precio Venta:</label>
                                                                <input type="number" id="precioVenta" name="precioVenta"
                                                                    step="0.01" required>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="fechaVenta">Fecha de Venta:</label>
                                                                <input type="date" id="fechaVenta" name="fechaVenta"
                                                                    required>
                                                            </div>
                                                        </div>
                                                        <div class="form-row">

                                                            <div class="form-group">
                                                                <label for="encargadoVenta">Encargado:</label>
                                                                <input type="text" id="encargadoVenta"
                                                                    name="encargadoVenta">
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="metodoPago">Método de Pago:</label>
                                                                <select id="metodoPago" name="metodoPago">
                                                                    <option value="Efectivo">Efectivo</option>
                                                                    <option value="Tarjeta">Tarjeta</option>
                                                                    <option value="Transferencia">Transferencia</option>
                                                                </select>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="facturaGenerada">Factura Generada:</label>
                                                                <input type="checkbox" id="facturaGenerada"
                                                                    name="facturaGenerada">
                                                            </div>

                                                        </div>

                                                        <div class="form-row">
                                                            <div class="form-group">
                                                                <label for="observaciones">Observaciones:</label>
                                                                <textarea id="observaciones"
                                                                    name="observaciones"></textarea>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="imagen">Imagen:</label>
                                                                <input type="file" id="imagen" name="imagen"
                                                                    accept="image/*">
                                                            </div>
                                                        </div>


                                                        <button type="submit" class="btn-submit">Agregar Venta</button>
                                                    </form>
                                                </div>

                                                <footer>
                                                    <div class="footer-bottom">
                                                        <p>&copy; 2024 Galería de Arte. Todos los derechos reservados.
                                                        </p>
                                                    </div>
                                                </footer>
                                            </body>

                                            </html>