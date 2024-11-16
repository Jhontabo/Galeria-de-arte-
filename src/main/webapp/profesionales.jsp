<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.galeria.models.Profesional" %>
        <%@ page import="java.util.List" %>

            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Profesionales del Arte</title>
                <link rel="stylesheet" href="css/tablas.css">
            </head>

            <body>
                <nav class="navbar">
                    <h2 class="logo">Galería de Arte</h2>
                    <ul>
                        <li><a href="index.html">Inicio</a></li>
                        <li><a href="obras">Obras de Arte</a></li>
                        <li><a href="profesionales" class="active">Profesionales</a></li>
                        <li><a href="exposiciones">Exposiciones</a></li>
                        <li><a href="coleccionistas">Coleccionistas</a></li>
                        <li><a href="ventas">Ventas</a></li>
                        <li><a href="colecciones">Colecciones</a></li>
                        <li><a href="contacto.html">Contacto</a></li>
                    </ul>
                </nav>

                <div class="content">
                    <h1 class="page-title">Lista de Profesionales del Arte</h1>
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Especialidad</th>
                                <th>Años de Experiencia</th>
                                <th>Proyectos Previos</th>
                                <th>Contacto</th>
                                <th>Institución Educativa</th>
                                <th>Estilo</th>
                                <th>Imagen</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>

                        <tbody>
                            <% List<Profesional> profesionales = (List<Profesional>)
                                    request.getAttribute("profesionales");
                                    if (profesionales != null && !profesionales.isEmpty()) {
                                    for (Profesional profesional : profesionales) {
                                    %>
                                    <tr>
                                        <td>
                                            <%= profesional.getId() %>
                                        </td>
                                        <td>
                                            <%= profesional.getNombreCompleto() %>
                                        </td>
                                        <td>
                                            <%= profesional.getEspecialidad() %>
                                        </td>
                                        <td>
                                            <%= profesional.getAniosExperiencia() %>
                                        </td>
                                        <td>
                                            <%= profesional.getProyectosPrevios() %>
                                        </td>
                                        <td>
                                            <%= profesional.getContacto() %>
                                        </td>
                                        <td>
                                            <%= profesional.getInstitucionEducativa() %>
                                        </td>
                                        <td>
                                            <%= profesional.getEstiloPreferido() %>
                                        </td>
                                        <td>
                                            <img src="resources/imagenes/profesionales/<%= profesional.getImagen() %>"
                                                alt="Imagen de <%= profesional.getNombreCompleto() %>" width="50">
                                        </td>
                                        <td>
                                            <form action="editarProfesional" method="get" style="display:inline;">
                                                <input type="hidden" name="id" value="<%= profesional.getId() %>">
                                                <button type="submit" class="btn-edit">Editar</button>
                                            </form>
                                            <form action="profesionales" method="post" style="display:inline;">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="id" value="<%= profesional.getId() %>">
                                                <button type="submit" class="btn-delete"
                                                    onclick="return confirm('¿Está seguro de eliminar este profesional?');">Eliminar</button>
                                            </form>


                                        </td>
                                    </tr>
                                    <% } } else { %>
                                        <tr>
                                            <td colspan="11">No hay profesionales registrados.</td>
                                        </tr>
                                        <% } %>
                        </tbody>
                    </table>

                    <h2 class="form-title">Agregar un Nuevo Profesional</h2>
                    <form action="profesionales" method="post" enctype="multipart/form-data" class="styled-form">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="nombreCompleto">Nombre Completo:</label>
                                <input type="text" id="nombreCompleto" name="nombreCompleto" required>
                            </div>
                            <div class="form-group">
                                <label for="especialidad">Especialidad:</label>
                                <input type="text" id="especialidad" name="especialidad" required>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="aniosExperiencia">Años de Experiencia:</label>
                                <input type="number" id="aniosExperiencia" name="aniosExperiencia" required>
                            </div>
                            <div class="form-group">
                                <label for="proyectosPrevios">Proyectos Previos:</label>
                                <input type="text" id="proyectosPrevios" name="proyectosPrevios">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="contacto">Contacto:</label>
                                <input type="email" id="contacto" name="contacto">
                            </div>
                            <div class="form-group">
                                <label for="institucionEducativa">Institución Educativa:</label>
                                <input type="text" id="institucionEducativa" name="institucionEducativa">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="premios">Premios:</label>
                                <input type="text" id="premios" name="premios">
                            </div>
                            <div class="form-group">
                                <label for="estiloPreferido">Estilo:</label>
                                <input type="text" id="estiloPreferido" name="estiloPreferido">
                            </div>

                            <div class="form-group">
                                <label for="imagen">Imagen:</label>
                                <input type="file" id="imagen" name="imagen" accept="image/*">
                            </div>
                        </div>


                        <div class="form-row">
                            <button type="submit" class="btn-submit">Agregar Profesional</button>
                        </div>
                    </form>
                </div>

                <footer>
                    <div class="footer-bottom">
                        <p>&copy; 2024 Galería de Arte. Todos los derechos reservados.</p>
                    </div>
                </footer>
            </body>

            </html>