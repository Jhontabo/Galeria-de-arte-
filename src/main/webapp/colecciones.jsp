<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Colecciones</title>
</head>
<body>
    <h1>Lista de Colecciones</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Responsable</th>
            <th>Estilo</th>
            <th>Obras Incluidas</th>
            <th>Fechas de Exhibición</th>
            <th>Sala Asignada</th>
            <th>Observaciones</th>
        </tr>
        <c:forEach var="coleccion" items="${colecciones}">
            <tr>
                <td>${coleccion.id}</td>
                <td>${coleccion.nombre}</td>
                <td>${coleccion.descripcion}</td>
                <td>${coleccion.responsable}</td>
                <td>${coleccion.estilo}</td>
                <td>
                    <c:forEach var="obra" items="${coleccion.obrasIncluidas}">
                        ${obra}, 
                    </c:forEach>
                </td>
                <td>${coleccion.fechasExhibicion}</td>
                <td>${coleccion.salaAsignada}</td>
                <td>${coleccion.observaciones}</td>
            </tr>
        </c:forEach>
    </table>

    <h2>Agregar una Nueva Colección</h2>
    <form action="colecciones" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>

        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" required></textarea><br>

        <label for="responsable">Responsable:</label>
        <input type="text" id="responsable" name="responsable"><br>

        <label for="estilo">Estilo:</label>
        <input type="text" id="estilo" name="estilo"><br>

        <label for="fechasExhibicion">Fechas de Exhibición:</label>
        <input type="text" id="fechasExhibicion" name="fechasExhibicion"><br>

        <label for="salaAsignada">Sala Asignada:</label>
        <input type="text" id="salaAsignada" name="salaAsignada"><br>

        <label for="observaciones">Observaciones:</label>
        <textarea id="observaciones" name="observaciones"></textarea><br>

        <label for="imagen">Imagen:</label>
        <input type="text" id="imagen" name="imagen"><br>

        <button type="submit">Agregar Colección</button>
    </form>
</body>
</html>
