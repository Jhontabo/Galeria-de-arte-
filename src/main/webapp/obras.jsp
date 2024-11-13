<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Obras de Arte</title>
</head>
<body>
    <h1>Lista de Obras de Arte</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Artista</th>
            <th>Año</th>
            <th>Técnica</th>
            <th>Dimensiones</th>
            <th>Estado</th>
            <th>Precio</th>
            <th>Observaciones</th>
        </tr>
        <c:forEach var="obra" items="${obras}">
            <tr>
                <td>${obra.id}</td>
                <td>${obra.titulo}</td>
                <td>${obra.artista}</td>
                <td>${obra.anioCreacion}</td>
                <td>${obra.tecnica}</td>
                <td>${obra.dimensiones}</td>
                <td>${obra.estado}</td>
                <td>${obra.precio}</td>
                <td>${obra.observaciones}</td>
            </tr>
        </c:forEach>
    </table>

    <h2>Agregar una Nueva Obra</h2>
    <form action="obras" method="post">
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required><br>

        <label for="artista">Artista:</label>
        <input type="text" id="artista" name="artista" required><br>

        <label for="anioCreacion">Año de Creación:</label>
        <input type="number" id="anioCreacion" name="anioCreacion" required><br>

        <label for="tecnica">Técnica:</label>
        <input type="text" id="tecnica" name="tecnica" required><br>

        <label for="dimensiones">Dimensiones:</label>
        <input type="text" id="dimensiones" name="dimensiones"><br>

        <label for="estado">Estado:</label>
        <select id="estado" name="estado">
            <option value="Exhibición">En Exhibición</option>
            <option value="Almacén">En Almacén</option>
            <option value="Vendida">Vendida</option>
        </select><br>

        <label for="precio">Precio:</label>
        <input type="number" id="precio" name="precio" step="0.01"><br>

        <label for="observaciones">Observaciones:</label>
        <textarea id="observaciones" name="observaciones"></textarea><br>

        <label for="imagen">Imagen:</label>
        <input type="text" id="imagen" name="imagen"><br>

        <button type="submit">Agregar Obra</button>
    </form>
</body>
</html>
