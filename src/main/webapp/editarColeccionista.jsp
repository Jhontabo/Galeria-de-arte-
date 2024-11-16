<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.galeria.models.Coleccionista" %>

        <% Coleccionista coleccionista=(Coleccionista) request.getAttribute("coleccionista"); %>

            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Editar Coleccionista</title>
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
                    input[type="number"],
                    input[type="file"],
                    select,
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
                <h1>Editar Coleccionista</h1>
                <form action="coleccionistas" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="id" value="<%= coleccionista.getId() %>">

                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" value="<%= coleccionista.getNombre() %>"
                                required>
                        </div>
                        <div class="form-group">
                            <label for="fechaInicioMembresia">Fecha de Membresía:</label>
                            <input type="date" id="fechaInicioMembresia" name="fechaInicioMembresia"
                                value="<%= coleccionista.getFechaInicioMembresia() %>">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="tipoMembresia">Tipo de Membresía:</label>
                            <select id="tipoMembresia" name="tipoMembresia">
                                <option value="Básica" <%="Básica" .equals(coleccionista.getTipoMembresia())
                                    ? "selected" : "" %>>
                                    Básica</option>
                                <option value="Avanzada" <%="Avanzada" .equals(coleccionista.getTipoMembresia())
                                    ? "selected" : "" %>>
                                    Avanzada</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="preferencias">Preferencias:</label>
                            <input type="text" id="preferencias" name="preferencias"
                                value="<%= coleccionista.getPreferencias() %>">
                        </div>
                    </div>


                    <div class="form-row">
                        <div class="form-group">
                            <label for="descuentos">Descuentos (%):</label>
                            <input type="number" id="descuentos" name="descuentos"
                                value="<%= coleccionista.getDescuentos() %>" step="0.01">
                        </div>
                        <div class="form-group">
                            <label for="suscripciones">Suscripciones:</label>
                            <input type="checkbox" id="suscripciones" name="suscripciones"
                                <%=coleccionista.isSuscripciones() ? "checked" : "" %>>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="renovacionAutomatica">Renovación Automática:</label>
                        <input type="checkbox" id="renovacionAutomatica" name="renovacionAutomatica"
                            <%=coleccionista.isRenovacionAutomatica() ? "checked" : "" %>>
                    </div>

                    <div class="form-group">
                        <label for="imagen">Imagen:</label>
                        <input type="file" id="imagen" name="imagen" accept="image/*">
                        <img src="resources/imagenes/coleccionistas/<%= coleccionista.getImagen() %>"
                            alt="Imagen de <%= coleccionista.getNombre() %>">
                    </div>

                    <button type="submit">Actualizar Coleccionista</button>
                </form>
            </body>

            </html>