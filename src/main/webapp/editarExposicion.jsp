<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.galeria.models.Exposicion" %>

        <% Exposicion exposicion=(Exposicion) request.getAttribute("exposicion"); %>

            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Editar Exposición</title>
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
                    input[type="file"],
                    textarea {
                        padding: 10px;
                        border: 1px solid #ccc;
                        border-radius: 5px;
                        font-size: 14px;
                        width: 100%;
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
                </style>
            </head>

            <body>
                <h1>Editar Exposición</h1>
                <form action="editarExposicion" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="<%= exposicion.getId() %>">

                    <div class="form-row">
                        <div class="form-group">
                            <label for="titulo">Título:</label>
                            <input type="text" id="titulo" name="titulo" value="<%= exposicion.getTitulo() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="tematica">Temática:</label>
                            <input type="text" id="tematica" name="tematica" value="<%= exposicion.getTematica() %>">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="fechaInicio">Fecha Inicio:</label>
                            <input type="date" id="fechaInicio" name="fechaInicio"
                                value="<%= exposicion.getFechaInicio() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="fechaFin">Fecha Fin:</label>
                            <input type="date" id="fechaFin" name="fechaFin" value="<%= exposicion.getFechaFin() %>"
                                required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="responsable">Responsable:</label>
                            <input type="text" id="responsable" name="responsable"
                                value="<%= exposicion.getResponsable() %>">
                        </div>
                        <div class="form-group">
                            <label for="salaAsignada">Sala Asignada:</label>
                            <input type="text" id="salaAsignada" name="salaAsignada"
                                value="<%= exposicion.getSalaAsignada() %>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="descripcion">Descripción:</label>
                        <textarea id="descripcion" name="descripcion"><%= exposicion.getDescripcion() %></textarea>
                    </div>

                    <div class="form-group">
                        <label for="imagen">Imagen:</label>
                        <input type="file" id="imagen" name="imagen" accept="image/*">
                        <img src="resources/imagenes/exposiciones/<%= exposicion.getImagen() %>"
                            alt="Imagen de <%= exposicion.getTitulo() %>">
                    </div>

                    <button type="submit">Actualizar Exposición</button>
                </form>
            </body>

            </html>