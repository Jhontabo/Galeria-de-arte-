<html>

<head>
    <title>Exposiciones</title>
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
    <h1>Lista de Exposiciones</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Fecha de Inicio</th>
            <th>Fecha de Fin</th>
            <th>Responsable</th>
            <th>Temática</th>
            <th>Sala Asignada</th>
            <th>Descripción</th>
        </tr>
        <c:forEach var="exposicion" items="${exposiciones}">
            <tr>
                <td>${exposicion.id}</td>
                <td>${exposicion.titulo}</td>
                <td>${exposicion.fechaInicio}</td>
                <td>${exposicion.fechaFin}</td>
                <td>${exposicion.responsable}</td>
                <td>${exposicion.tematica}</td>
                <td>${exposicion.salaAsignada}</td>
                <td>${exposicion.descripcion}</td>
            </tr>
        </c:forEach>
    </table>

    <h2>Agregar una Nueva Exposición</h2>
    <form action="exposiciones" method="post">
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required><br>

        <label for="fechaInicio">Fecha de Inicio:</label>
        <input type="date" id="fechaInicio" name="fechaInicio" required><br>

        <label for="fechaFin">Fecha de Fin:</label>
        <input type="date" id="fechaFin" name="fechaFin" required><br>

        <label for="responsable">Responsable:</label>
        <input type="text" id="responsable" name="responsable"><br>

        <label for="tematica">Temática:</label>
        <input type="text" id="tematica" name="tematica"><br>

        <label for="salaAsignada">Sala Asignada:</label>
        <input type="text" id="salaAsignada" name="salaAsignada"><br>

        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion"></textarea><br>

        <label for="imagen">Imagen:</label>
        <input type="text" id="imagen" name="imagen"><br>

        <button type="submit">Agregar Exposición</button>
    </form>
</body>

</html>