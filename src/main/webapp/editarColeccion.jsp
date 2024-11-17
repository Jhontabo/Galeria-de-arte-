<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.galeria.models.Coleccion" %>
<%@ page import="com.galeria.models.ObraDeArte" %>
<%@ page import="java.util.List" %>

<%
    Coleccion coleccion = (Coleccion) request.getAttribute("coleccion");
    List<ObraDeArte> obras = (List<ObraDeArte>) request.getAttribute("obras");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Colección</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            color: #333;
        }

        h1 {
            text-align: center;
            color: #007bff;
            margin-top: 20px;
        }

        form {
            max-width: 800px;
            margin: 20px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .form-row {
            display: flex;
            justify-content: space-between;
            gap: 15px;
            margin-bottom: 15px;
        }

        .form-group {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"],
        input[type="date"],
        textarea,
        select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        textarea {
            resize: vertical;
        }

        img {
            display: block;
            margin: 10px auto;
            max-width: 200px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        button {
            display: block;
            width: 100%;
            padding: 12px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        .form-group img {
            margin-top: 10px;
            max-width: 100%;
            height: auto;
            border: 1px solid #ddd;
            padding: 5px;
            border-radius: 8px;
        }
    </style>
</head>
<body>
    <h1>Editar Colección</h1>
    <form action="editarColeccion" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<%= coleccion.getId() %>">

        <div class="form-row">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%= coleccion.getNombre() %>" required>
            </div>
            <div class="form-group">
                <label for="responsable">Responsable:</label>
                <input type="text" id="responsable" name="responsable" value="<%= coleccion.getResponsable() %>">
            </div>
        </div>

        <div class="form-group">
            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" required><%= coleccion.getDescripcion() %></textarea>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="estilo">Estilo:</label>
                <input type="text" id="estilo" name="estilo" value="<%= coleccion.getEstilo() %>">
            </div>
            <div class="form-group">
                <label for="fechasExhibicion">Fecha de Exhibición:</label>
                <input type="date" id="fechasExhibicion" name="fechasExhibicion" value="<%= coleccion.getFechasExhibicion() %>">
            </div>
        </div>

        <div class="form-group">
            <label for="obrasIncluidas">Obras Incluidas:</label>
            <select id="obrasIncluidas" name="obrasIncluidas" multiple>
                <% for (ObraDeArte obra : obras) { %>
                    <option value="<%= obra.getId() %>" <%= coleccion.getObrasIncluidas().contains(obra.getId()) ? "selected" : "" %>>
                        <%= obra.getTitulo() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="salaAsignada">Sala Asignada:</label>
                <input type="text" id="salaAsignada" name="salaAsignada" value="<%= coleccion.getSalaAsignada() %>">
            </div>
            <div class="form-group">
                <label for="imagen">Imagen:</label>
                <input type="file" id="imagen" name="imagen" accept="image/*">
                <% if (coleccion.getImagen() != null) { %>
                    <img src="resources/imagenes/colecciones/<%= coleccion.getImagen() %>" alt="Imagen de la colección">
                <% } %>
            </div>
        </div>

        <div class="form-group">
            <label for="observaciones">Observaciones:</label>
            <textarea id="observaciones" name="observaciones"><%= coleccion.getObservaciones() %></textarea>
        </div>

        <button type="submit">Actualizar Colección</button>
    </form>
</body>
</html>
