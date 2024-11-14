<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>

    <head>
        <title>Coleccionistas</title>
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
        <h1>Lista de Coleccionistas</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Fecha de Membresía</th>
                <th>Tipo de Membresía</th>
                <th>Preferencias</th>
                <th>Historial de Compras</th>
                <th>Descuentos</th>
                <th>Suscripciones</th>
                <th>Renovación Automática</th>
            </tr>
            <c:forEach var="coleccionista" items="${coleccionistas}">
                <tr>
                    <td>${coleccionista.id}</td>
                    <td>${coleccionista.nombre}</td>
                    <td>${coleccionista.fechaInicioMembresia}</td>
                    <td>${coleccionista.tipoMembresia}</td>
                    <td>${coleccionista.preferencias}</td>
                    <td>${coleccionista.historialCompras}</td>
                    <td>${coleccionista.descuentos}</td>
                    <td>${coleccionista.suscripciones ? "Sí" : "No"}</td>
                    <td>${coleccionista.renovacionAutomatica ? "Sí" : "No"}</td>
                </tr>
            </c:forEach>
        </table>

        <h2>Agregar un Nuevo Coleccionista</h2>
        <form action="coleccionistas" method="post">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required><br>

            <label for="fechaInicioMembresia">Fecha de Membresía:</label>
            <input type="date" id="fechaInicioMembresia" name="fechaInicioMembresia"><br>

            <label for="tipoMembresia">Tipo de Membresía:</label>
            <select id="tipoMembresia" name="tipoMembresia">
                <option value="Básica">Básica</option>
                <option value="Avanzada">Avanzada</option>
            </select><br>

            <label for="preferencias">Preferencias:</label>
            <input type="text" id="preferencias" name="preferencias"><br>

            <label for="historialCompras">Historial de Compras:</label>
            <input type="text" id="historialCompras" name="historialCompras"><br>

            <label for="descuentos">Descuentos:</label>
            <input type="number" id="descuentos" name="descuentos" step="0.01"><br>

            <label for="suscripciones">Suscripciones:</label>
            <input type="checkbox" id="suscripciones" name="suscripciones"><br>

            <label for="renovacionAutomatica">Renovación Automática:</label>
            <input type="checkbox" id="renovacionAutomatica" name="renovacionAutomatica"><br>

            <label for="imagen">Imagen:</label>
            <input type="text" id="i