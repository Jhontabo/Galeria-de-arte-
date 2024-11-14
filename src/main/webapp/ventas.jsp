<html>

<head>
    <title>Ventas</title>
    <link rel="stylesheet" href="css/index.css">
</head>

<body>
    <nav class="navbar">
        <h2 class="logo">Galería de Arte</h2>
        <ul>
            <li><a href="index.html" class="active">Inicio</a></li>
            <li><a href="obras">Obras de Arte</a></li>
            <li><a href="profesionales">Profesionales</a></li>
            <li><a href="exposiciones">Exposiciones</a></li>
            <li><a href="coleccionistas">Coleccionistas</a></li>
            <li><a href="ventas">Ventas</a></li>
            <li><a href="colecciones">Colecciones</a></li>
            <li><a href="contacto.html">Contacto</a></li>
        </ul>
    </nav>
    <h1>Lista de Ventas</h1>
    <table border="1">
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

    <h2>Agregar una Nueva Venta</h2>
    <form action="ventas" method="post">
        <label for="idObra">ID Obra:</label>
        <input type="number" id="idObra" name="idObra" required><br>

        <label for="precioVenta">Precio Venta:</label>
        <input type="number" id="precioVenta" name="precioVenta" step="0.01" required><br>

        <label for="fechaVenta">Fecha de Venta:</label>
        <input type="date" id="fechaVenta" name="fechaVenta"><br>

        <label for="encargadoVenta">Encargado:</label>
        <input type="text" id="encargadoVenta" name="encargadoVenta"><br>

        <label for="idCliente">ID Cliente:</label>
        <input type="number" id="idCliente" name="idCliente"><br>

        <label for="metodoPago">Método de Pago:</label>
        <select id="metodoPago" name="metodoPago">
            <option value="Efectivo">Efectivo</option>
            <option value="Tarjeta">Tarjeta</option>
            <option value="Transferencia">Transferencia</option>
        </select><br>

        <label for="facturaGenerada">Factura Generada:</label>
        <input type="checkbox" id="facturaGenerada" name="facturaGenerada"><br>

        <label for="observaciones">Observaciones:</label>
        <textarea id="observaciones" name="observaciones"></textarea><br>

        <label for="imagen">Imagen:</label>
        <input type="text" id="imagen" name="imagen"><br>

        <button type="submit">Agregar Venta</button>
    </form>
</body>

</html>