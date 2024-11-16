<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.galeria.models.ObraDeArte" %>

        <% ObraDeArte obra=(ObraDeArte) request.getAttribute("obra"); %>

            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Editar Obra</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        margin: 0;
                        padding: 0;
                        background-color: #f9f9f9;
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
                        display: grid;
                        grid-template-columns: 1fr 1fr;
                        gap: 20px;
                    }

                    form img {
                        grid-column: span 2;
                        margin: 0 auto;
                    }

                    label {
                        font-weight: bold;
                        color: #555;
                        display: block;
                        margin-bottom: 5px;
                    }

                    input[type="text"],
                    input[type="number"],
                    textarea,
                    select,
                    input[type="file"] {
                        width: 100%;
                        padding: 10px;
                        margin-bottom: 10px;
                        border: 1px solid #ccc;
                        border-radius: 5px;
                        box-sizing: border-box;
                    }

                    textarea {
                        resize: vertical;
                    }

                    button {
                        grid-column: span 2;
                        padding: 10px;
                        background-color: #007bff;
                        color: #fff;
                        border: none;
                        border-radius: 5px;
                        font-size: 16px;
                        cursor: pointer;
                    }

                    button:hover {
                        background-color: #0056b3;
                    }
                </style>
            </head>

            <body>
                <h1>Editar Obra</h1>
                <form action="editarObra" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="<%= obra.getId() %>">

                    <div>
                        <label for="titulo">Título:</label>
                        <input type="text" id="titulo" name="titulo" value="<%= obra.getTitulo() %>" required>
                    </div>

                    <div>
                        <label for="artista">Artista:</label>
                        <input type="text" id="artista" name="artista" value="<%= obra.getArtista() %>" required>
                    </div>

                    <div>
                        <label for="anioCreacion">Año de Creación:</label>
                        <input type="number" id="anioCreacion" name="anioCreacion" value="<%= obra.getAnioCreacion() %>"
                            required>
                    </div>

                    <div>
                        <label for="tecnica">Técnica:</label>
                        <input type="text" id="tecnica" name="tecnica" value="<%= obra.getTecnica() %>" required>
                    </div>

                    <div>
                        <label for="dimensiones">Dimensiones:</label>
                        <input type="text" id="dimensiones" name="dimensiones" value="<%= obra.getDimensiones() %>">
                    </div>

                    <div>
                        <label for="estado">Estado:</label>
                        <select id="estado" name="estado">
                            <option value="Exhibición" <%=obra.getEstado().equals("Exhibición") ? "selected" : "" %>>En
                                Exhibición</option>
                            <option value="Almacén" <%=obra.getEstado().equals("Almacén") ? "selected" : "" %>>En
                                Almacén</option>
                            <option value="Vendida" <%=obra.getEstado().equals("Vendida") ? "selected" : "" %>>Vendida
                            </option>
                        </select>
                    </div>

                    <div>
                        <label for="precio">Precio:</label>
                        <input type="number" id="precio" name="precio" value="<%= obra.getPrecio() %>" step="0.01">
                    </div>

                    <div style="grid-column: span 2;">
                        <label for="observaciones">Observaciones:</label>
                        <textarea id="observaciones" name="observaciones"
                            rows="3"><%= obra.getObservaciones() %></textarea>
                    </div>

                    <div>
                        <label for="imagen">Imagen:</label>
                        <input type="file" id="imagen" name="imagen" accept="image/*">
                    </div>

                    <img src="resources/imagenes/obras/<%= obra.getImagen() %>" alt="Imagen de <%= obra.getTitulo() %>">

                    <button type="submit">Actualizar Obra</button>
                </form>
            </body>

            </html>