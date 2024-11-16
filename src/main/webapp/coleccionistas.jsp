<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.galeria.models.Coleccionista" %>

            <% List<Coleccionista> coleccionistas = (List<Coleccionista>) request.getAttribute("coleccionistas");
                    %>

                    <!DOCTYPE html>
                    <html lang="es">

                    <head>
                        <title>Coleccionistas - Galería de Arte</title>
                        <meta charset="UTF-8">
                        <link
                            href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Roboto:wght@400;500&display=swap"
                            rel="stylesheet">
                        <link rel="stylesheet" href="css/tablas.css">
                    </head>

                    <body>
                        <div class="container">
                            <nav class="navbar">
                                <h2 class="logo">Galería de Arte</h2>
                                <ul>
                                    <li><a href="index.html">Inicio</a></li>
                                    <li><a href="obras">Obras de Arte</a></li>
                                    <li><a href="profesionales">Profesionales</a></li>
                                    <li><a href="exposiciones">Exposiciones</a></li>
                                    <li><a href="coleccionistas" class="active">Coleccionistas</a></li>
                                    <li><a href="ventas">Ventas</a></li>
                                    <li><a href="colecciones">Colecciones</a></li>
                                    <li><a href="contacto.html">Contacto</a></li>
                                </ul>
                            </nav>

                            <div class="content">
                                <h1 class="page-title">Lista de Coleccionistas</h1>

                                <table class="styled-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre</th>
                                            <th>Fecha de Membresía</th>
                                            <th>Tipo de Membresía</th>
                                            <th>Preferencias</th>
                                            <th>Descuentos</th>
                                            <th>Suscripciones</th>
                                            <th>Renovación Automática</th>
                                            <th>Imagen</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% if (coleccionistas !=null && !coleccionistas.isEmpty()) { %>
                                            <% for (Coleccionista coleccionista : coleccionistas) { %>
                                                <tr>
                                                    <td>
                                                        <%= coleccionista.getId() %>
                                                    </td>
                                                    <td>
                                                        <%= coleccionista.getNombre() %>
                                                    </td>
                                                    <td>
                                                        <%= coleccionista.getFechaInicioMembresia() %>
                                                    </td>
                                                    <td>
                                                        <%= coleccionista.getTipoMembresia() %>
                                                    </td>
                                                    <td>
                                                        <%= coleccionista.getPreferencias() %>
                                                    </td>
                                                    <td>
                                                        <%= coleccionista.getDescuentos() %> %
                                                    </td>
                                                    <td>
                                                        <%= coleccionista.isSuscripciones() ? "Sí" : "No" %>
                                                    </td>
                                                    <td>
                                                        <%= coleccionista.isRenovacionAutomatica() ? "Sí" : "No" %>
                                                    </td>
                                                    <td>
                                                        <img src="resources/imagenes/coleccionistas/<%= coleccionista.getImagen() %>"
                                                            alt="Imagen de <%= coleccionista.getNombre() %>" width="50">
                                                    </td>
                                                    <td>
                                                        <form action="editarColeccionista" method="get"
                                                            style="display:inline;">
                                                            <input type="hidden" name="id"
                                                                value="<%= coleccionista.getId() %>">
                                                            <button type="submit" class="btn-edit">Editar</button>
                                                        </form>
                                                        <form action="coleccionistas" method="post"
                                                            style="display:inline;">
                                                            <input type="hidden" name="action" value="delete">
                                                            <input type="hidden" name="id"
                                                                value="<%= coleccionista.getId() %>">
                                                            <button type="submit" class="btn-delete"
                                                                onclick="return confirm('¿Está seguro de eliminar este coleccionista?');">
                                                                Eliminar
                                                            </button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <% } %>
                                                    <% } else { %>
                                                        <tr>
                                                            <td colspan="10">No hay coleccionistas registrados.</td>
                                                        </tr>
                                                        <% } %>
                                    </tbody>
                                </table>

                                <section class="form-section">
                                    <h2 class="form-title">Agregar un Nuevo Coleccionista</h2>
                                    <form action="coleccionistas" method="post" class="styled-form"
                                        enctype="multipart/form-data">
                                        <div class="form-row">
                                            <div class="form-group">
                                                <label for="nombre">Nombre:</label>
                                                <input type="text" id="nombre" name="nombre" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="fechaInicioMembresia">Fecha de Membresía:</label>
                                                <input type="date" id="fechaInicioMembresia"
                                                    name="fechaInicioMembresia">
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group">
                                                <label for="tipoMembresia">Tipo de Membresía:</label>
                                                <select id="tipoMembresia" name="tipoMembresia">
                                                    <option value="Básica">Básica</option>
                                                    <option value="Avanzada">Avanzada</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="preferencias">Preferencias:</label>
                                                <input type="text" id="preferencias" name="preferencias">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="descuentos">Descuentos (%):</label>
                                            <input type="number" id="descuentos" name="descuentos" step="0.01">
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group checkbox-group">
                                                <label for="suscripciones">Suscripciones:</label>
                                                <input type="checkbox" id="suscripciones" name="suscripciones">
                                            </div>
                                            <div class="form-group checkbox-group">
                                                <label for="renovacionAutomatica">Renovación Automática:</label>
                                                <input type="checkbox" id="renovacionAutomatica"
                                                    name="renovacionAutomatica">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="imagen">Imagen:</label>
                                            <input type="file" id="imagen" name="imagen" accept="image/*">
                                        </div>
                                        <button type="submit" class="btn-submit">Agregar Coleccionista</button>
                                    </form>
                                </section>
                            </div>
                        </div>

                        <footer>
                            <div class="footer-bottom">
                                <p>&copy; 2024 Galería de Arte. Todos los derechos reservados.</p>
                            </div>
                        </footer>
                    </body>

                    </html>