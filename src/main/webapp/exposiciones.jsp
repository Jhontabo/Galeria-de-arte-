<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.galeria.models.Exposicion" %>

            <% List<Exposicion> exposiciones = (List<Exposicion>) request.getAttribute("exposiciones");
                    %>

                    <!DOCTYPE html>
                    <html lang="es">

                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Exposiciones</title>
                        <link rel="stylesheet" href="css/tablas.css">
                    </head>

                    <body>
                        <nav class="navbar">
                            <h2 class="logo">Galería de Arte</h2>
                            <ul>
                                <li><a href="index.html">Inicio</a></li>
                                <li><a href="obras">Obras de Arte</a></li>
                                <li><a href="profesionales">Profesionales</a></li>
                                <li><a href="exposiciones" class="active">Exposiciones</a></li>
                                <li><a href="coleccionistas">Coleccionistas</a></li>
                                <li><a href="ventas">Ventas</a></li>
                                <li><a href="colecciones">Colecciones</a></li>
                                <li><a href="contacto.html">Contacto</a></li>
                            </ul>
                        </nav>

                        <div class="content">
                            <h1 class="page-title">Lista de Exposiciones</h1>
                            <table class="styled-table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Título</th>
                                        <th>Fechas</th>
                                        <th>Responsable</th>
                                        <th>Temática</th>
                                        <th>Sala</th>
                                        <th>Descripción</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (exposiciones !=null && !exposiciones.isEmpty()) { %>
                                        <% for (Exposicion exposicion : exposiciones) { %>
                                            <tr>
                                                <td>
                                                    <%= exposicion.getId() %>
                                                </td>
                                                <td>
                                                    <%= exposicion.getTitulo() %>
                                                </td>
                                                <td>
                                                    <%= exposicion.getFechaInicio() %> - <%= exposicion.getFechaFin() %>
                                                </td>
                                                <td>
                                                    <%= exposicion.getResponsable() %>
                                                </td>
                                                <td>
                                                    <%= exposicion.getTematica() %>
                                                </td>
                                                <td>
                                                    <%= exposicion.getSalaAsignada() %>
                                                </td>
                                                <td>
                                                    <%= exposicion.getDescripcion() %>
                                                </td>
                                                <td class="actions">
                                                    <form action="editarExposicion" method="get"
                                                        style="display:inline;">
                                                        <input type="hidden" name="id"
                                                            value="<%= exposicion.getId() %>">
                                                        <button type="submit" class="btn-edit">Editar</button>
                                                    </form>
                                                    <form action="exposiciones" method="post" style="display:inline;">
                                                        <input type="hidden" name="action" value="delete">
                                                        <input type="hidden" name="id"
                                                            value="<%= exposicion.getId() %>">
                                                        <button type="submit" class="btn-delete"
                                                            onclick="return confirm('¿Está seguro de eliminar esta exposición?');">Eliminar</button>
                                                    </form>
                                                </td>
                                            </tr>
                                            <% } %>
                                                <% } else { %>
                                                    <tr>
                                                        <td colspan="8">No hay exposiciones registradas.</td>
                                                    </tr>
                                                    <% } %>
                                </tbody>
                            </table>

                            <h2 class="form-title">Agregar Nueva Exposición</h2>
                            <form class="styled-form" action="exposiciones" method="post" enctype="multipart/form-data">
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="titulo">Título:</label>
                                        <input type="text" id="titulo" name="titulo" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="tematica">Temática:</label>
                                        <input type="text" id="tematica" name="tematica">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="fechaInicio">Fecha Inicio:</label>
                                        <input type="date" id="fechaInicio" name="fechaInicio" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="fechaFin">Fecha Fin:</label>
                                        <input type="date" id="fechaFin" name="fechaFin" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="responsable">Responsable:</label>
                                        <input type="text" id="responsable" name="responsable">
                                    </div>
                                    <div class="form-group">
                                        <label for="salaAsignada">Sala Asignada:</label>
                                        <input type="text" id="salaAsignada" name="salaAsignada">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="descripcion">Descripción:</label>
                                        <textarea id="descripcion" name="descripcion"
                                            placeholder="Incluye información relevante"></textarea>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="imagen">Imagen:</label>
                                        <input type="file" id="imagen" name="imagen" accept="image/*">
                                    </div>
                                </div>
                                <button type="submit" class="btn-submit">Guardar</button>
                            </form>
                        </div>

                        <footer>
                            <div class="footer-bottom">
                                <p>&copy; 2024 Galería de Arte. Todos los derechos reservados.</p>
                            </div>
                        </footer>
                    </body>

                    </html>