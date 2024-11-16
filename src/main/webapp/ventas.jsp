<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
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
                <tr>
                    <th>ID</th>
                    <th>Obra Vendida</th>
                    <th>Precio</th>
                    <th>Fecha</th>
                    <th>Encargado</th>
                    <th>Cliente</th>
                    <th>Método de Pago</th>
                    <th>Factura Generada</th>
                    <th>Observaciones</th>
                </tr>
                <c:forEach var="venta" items="${ventas}">
                    <tr>
                        <td>${venta.id}</td>
                        <td>${venta.idObra}</td>
                        <td>${venta.precioVenta}</td>
                        <td>${venta.fechaVenta}</td>
                        <td>${venta.encargadoVenta}</td>
                        <td>${venta.idCliente}</td>
                        <td>${venta.metodoPago}</td>
                        <td>${venta.facturaGenerada ? "Sí" : "No"}</td>
                        <td>${venta.observaciones}</td>
                    </tr>
                </c:forEach>
            </table>


            <h2 class="form-title">Agregar una Nueva Venta</h2>
            <form action="ventas" method="post" class="styled-form">
                <div class="form-row">
                    <div class="form-group">
                        <label for="idObra">ID Obra:</label>
                        <input type="number" id="idObra" name="idObra" required><br>
                    </div>
                </div>

                <div class="form-group">
                    <label for="precioVenta">Precio Venta:</label>
                    <input type="number" id="precioVenta" name="precioVenta" step="0.01" required><br>
                </div>
                <div class="form-group">
                    <label for="fechaVenta">Fecha de Venta:</label>
                    <input type="date" id="fechaVenta" name="fechaVenta"><br>

                </div>
                <div class="form-group">
                    <label for="encargadoVenta">Encargado:</label>
                    <input type="text" id="encargadoVenta" name="encargadoVenta"><br>

                </div>

                <div class="form-group">

                    <label for="idCliente">ID Cliente:</label>
                    <input type="number" id="idCliente" name="idCliente"><br>

                </div>

                <div class="form-group">
                    <label for="metodoPago">Método de Pago:</label>
                    <select id="metodoPago" name="metodoPago">
                        <option value="Efectivo">Efectivo</option>
                        <option value="Tarjeta">Tarjeta</option>
                        <option value="Transferencia">Transferencia</option>
                    </select><br>
                </div>




                <div class="form-group">
                    <label for="facturaGenerada">Factura Generada:</label>
                    <input type="checkbox" id="facturaGenerada" name="facturaGenerada"><br>
                </div>
                <div class="form-group">
                    <label for="observaciones">Observaciones:</label>
                    <textarea id="observaciones" name="observaciones"></textarea><br>

                </div>
                <div class="form-group">
                    <label for="imagen">Imagen:</label>
                    <input type="text" id="imagen" name="imagen"><br>
                </div>

                <button type="submit" class="btn-submit">Agregar Venta</button>

            </form>
        </div>


        <footer>
            <div class="footer-bottom">
                <p>&copy; 2024 Galería de Arte. Oscar Tajumbina.</p>
            </div>
        </footer>
    </body>

    </html>