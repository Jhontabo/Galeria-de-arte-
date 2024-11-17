<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.galeria.models.Coleccion" %>
<%@ page import="com.galeria.models.ObraDeArte" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <title>Colecciones - Galería de Arte</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/tablas.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Roboto:wght@400;500&display=swap" rel="stylesheet">
</head>

<body>

    <nav class="navbar">
        <h2 class="logo">Galería de Arte</h2>
        <ul>
            <li><a href="index.html">Inicio</a></li>
            <li><a href="obras">Obras de Arte</a></li>
            <li><a href="profesionales">Profesionales</a></li>
            <li><a href="exposiciones">Exposiciones</a></li>
            <li><a href="coleccionistas">Coleccionistas</a></li>
            <li><a href="ventas">Ventas</a></li>
            <li><a href="colecciones" class="active">Colecciones</a></li>
            <li><a href="contacto.html">Contacto</a></li>
        </ul>
    </nav>

    <div class="content">
        <h1 class="page-title">Lista de Colecciones</h1>

        <table class="styled-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Responsable</th>
                    <th>Estilo</th>
                    <th>Obras Incluidas</th>
                    <th>Fechas de Exhibición</th>
                    <th>Sala Asignada</th>
                    <th>Observaciones</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<Coleccion> colecciones = (List<Coleccion>) request.getAttribute("colecciones");
                List<ObraDeArte> obras = (List<ObraDeArte>) request.getAttribute("obras");
                if (colecciones != null && !colecciones.isEmpty()) {
                    for (Coleccion coleccion : colecciones) {
                %>
                <tr>
                    <td><%= coleccion.getId() %></td>
                    <td><%= coleccion.getNombre() %></td>
                    <td><%= coleccion.getDescripcion() %></td>
                    <td><%= coleccion.getResponsable() %></td>
                    <td><%= coleccion.getEstilo() %></td>
                    <td>
                        <%
                        if (coleccion.getObrasIncluidas() != null) {
                            for (Integer obraId : coleccion.getObrasIncluidas()) {
                                String obraNombre = obras.stream()
                                    .filter(o -> o.getId() == obraId)
                                    .map(o -> o.getTitulo())
                                    .findFirst()
                                    .orElse("Obra desconocida");
                        %>
                            <%= obraNombre %>,
                        <%
                            }
                        } else {
                        %>
                            No hay obras asignadas
                        <%
                        }
                        %>
                    </td>
                    <td><%= coleccion.getFechasExhibicion() %></td>
                    <td><%= coleccion.getSalaAsignada() %></td>
                    <td><%= coleccion.getObservaciones() %></td>
                    <td>
                        <form action="editarColeccion" method="get" style="display:inline;">
                            <input type="hidden" name="id" value="<%= coleccion.getId() %>">
                            <button type="submit" class="btn-edit">Editar</button>
                        </form>
                        <form action="colecciones" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= coleccion.getId() %>">
                            <button type="submit" class="btn-delete" onclick="return confirm('¿Eliminar esta colección?');">Eliminar</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="10">No hay colecciones registradas.</td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>

        <h2 class="form-title">Agregar Colección</h2>

        <form action="colecciones" method="post" enctype="multipart/form-data" class="styled-form">
            <div class="form-row">
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>
                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" required></textarea>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="responsable">Responsable:</label>
                    <input type="text" id="responsable" name="responsable">
                </div>
                <div class="form-group">
                    <label for="estilo">Estilo:</label>
                    <input type="text" id="estilo" name="estilo">
                </div>
            </div>
            <div class="form-group">
                <label for="obrasIncluidas">Obras Incluidas:</label>
                <select id="obrasIncluidas" name="obrasIncluidas" multiple>
                    <%
                    if (obras != null) {
                        for (ObraDeArte obra : obras) {
                    %>
                    <option value="<%= obra.getId() %>"><%= obra.getTitulo() %></option>
                    <%
                        }
                    }
                    %>
                </select>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="fechasExhibicion">Fechas de Exhibición:</label>
                    <input type="date" id="fechasExhibicion" name="fechasExhibicion">
                </div>
                <div class="form-group">
                    <label for="salaAsignada">Sala Asignada:</label>
                    <input type="text" id="salaAsignada" name="salaAsignada">
                </div>
            </div>
            <div class="form-group">
                <label for="observaciones">Observaciones:</label>
                <textarea id="observaciones" name="observaciones"></textarea>
            </div>
            <div class="form-group">
                <label for="imagen">Imagen:</label>
                <input type="file" id="imagen" name="imagen" accept="image/*">
            </div>
            <button type="submit" class="btn-submit">Agregar Colección</button>
        </form>

    </div>

    <footer>
        <div class="footer-bottom">
            <p>&copy; 2024 Galería de Arte. Todos los derechos reservados.</p>
        </div>
    </footer>
</body>

</html>
