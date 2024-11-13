<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profesionales del Arte</title>
</head>
<body>
    <h1>Lista de Profesionales del Arte</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Especialidad</th>
            <th>Años de Experiencia</th>
            <th>Proyectos Previos</th>
            <th>Contacto</th>
            <th>Institución Educativa</th>
            <th>Premios</th>
            <th>Estilo</th>
        </tr>
        <c:forEach var="profesional" items="${profesionales}">
            <tr>
                <td>${profesional.id}</td>
                <td>${profesional.nombreCompleto}</td>
                <td>${profesional.especialidad}</td>
                <td>${profesional.aniosExperiencia}</td>
                <td>${profesional.proyectosPrevios}</td>
                <td>${profesional.contacto}</td>
                <td>${profesional.institucionEducativa}</td>
                <td>${profesional.premios}</td>
                <td>${profesional.estiloPreferido}</td>
            </tr>
        </c:forEach>
    </table>

    <h2>Agregar un Nuevo Profesional</h2>
    <form action="profesionales" method="post">
        <label for="nombreCompleto">Nombre Completo:</label>
        <input type="text" id="nombreCompleto" name="nombreCompleto" required><br>

        <label for="especialidad">Especialidad:</label>
        <input type="text" id="especialidad" name="especialidad" required><br>

        <label for="aniosExperiencia">Años de Experiencia:</label>
        <input type="number" id="aniosExperiencia" name="aniosExperiencia" required><br>

        <label for="proyectosPrevios">Proyectos Previos:</label>
        <input type="text" id="proyectosPrevios" name="proyectosPrevios"><br>

        <label for="contacto">Contacto:</label>
        <input type="email" id="contacto" name="contacto"><br>

        <label for="institucionEducativa">Institución Educativa:</label>
        <input type="text" id="institucionEducativa" name="institucionEducativa"><br>

        <label for="premios">Premios:</label>
        <input type="text" id="premios" name="premios"><br>

        <label for="estiloPreferido">Estilo:</label>
        <input type="text" id="estiloPreferido" name="estiloPreferido"><br>

        <label for="imagen">Imagen:</label>
        <input type="text" id="imagen" name="imagen"><br>

        <button type="submit">Agregar Profesional</button>
    </form>
</body>
</html>
