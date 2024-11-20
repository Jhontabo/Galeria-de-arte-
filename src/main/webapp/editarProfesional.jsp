<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.galeria.models.Profesional" %>

        <% Profesional profesional=(Profesional) request.getAttribute("profesional"); %>

            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Editar Profesional</title>
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
                    input[type="number"],
                    input[type="email"],
                    input[type="file"] {
                        padding: 10px;
                        border: 1px solid #ccc;
                        border-radius: 5px;
                        font-size: 14px;
                    }

                    textarea {
                        resize: vertical;
                        padding: 10px;
                        border: 1px solid #ccc;
                        border-radius: 5px;
                        font-size: 14px;
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
                <h1>Editar Profesional</h1>
                <form action="editarProfesional" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="<%= profesional.getId() %>">

                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombreCompleto">Nombre Completo:</label>
                            <input type="text" id="nombreCompleto" name="nombreCompleto"
                                value="<%= profesional.getNombreCompleto() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="especialidad">Especialidad:</label>
                            <input type="text" id="especialidad" name="especialidad"
                                value="<%= profesional.getEspecialidad() %>" required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="aniosExperiencia">Años de Experiencia:</label>
                            <input type="number" id="aniosExperiencia" name="aniosExperiencia"
                                value="<%= profesional.getAniosExperiencia() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="proyectosPrevios">Proyectos Previos:</label>
                            <input type="text" id="proyectosPrevios" name="proyectosPrevios"
                                value="<%= profesional.getProyectosPrevios() %>">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="contacto">Contacto:</label>
                            <input type="email" id="contacto" name="contacto" value="<%= profesional.getContacto() %>">
                        </div>
                        <div class="form-group">
                            <label for="institucionEducativa">Institución Educativa:</label>
                            <input type="text" id="institucionEducativa" name="institucionEducativa"
                                value="<%= profesional.getInstitucionEducativa() %>">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="premios">Premios:</label>
                            <input type="text" id="premios" name="premios" value="<%= profesional.getPremios() %>">
                        </div>
                        <div class="form-group">
                            <label for="estiloPreferido">Estilo Preferido:</label>
                            <input type="text" id="estiloPreferido" name="estiloPreferido"
                                value="<%= profesional.getEstiloPreferido() %>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="imagen">Imagen:</label>
                        <input type="file" id="imagen" name="imagen" accept="image/*">
                        <%
                            String imagenPath = profesional.getImagen();
                            if (imagenPath != null && !imagenPath.isEmpty()) {
                        %>
                            <!-- Mostrar ruta para depuración -->
                            <p>Ruta generada: resources/imagenes/profesionales/<%= imagenPath %></p>

                            <!-- Mostrar imagen -->
                            <img src="resources/imagenes/profesionales/<%= imagenPath %>"
                                 alt="Imagen de <%= profesional.getNombreCompleto() %>"
                                 onerror="this.onerror=null; this.src='ruta_a_imagen_fallback.jpg';">
                        <% } else { %>
                            <p>No hay imagen disponible.</p>
                        <% } %>
                    </div>




                    <button type="submit">Actualizar Profesional</button>
                </form>
            </body>

            </html>