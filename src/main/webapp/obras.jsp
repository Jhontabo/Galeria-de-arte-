<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Obras de Arte</title>
        <link rel="stylesheet" href="css/tablas.css">
    </head>

    <body>
        <nav class="navbar">
            <h2 class="logo">Galería de Arte</h2>
            <ul>
                <li><a href="index.html" class="active">Inicio</a></li>
                <li><a href="obras">Obras de Arte</a></li>
                <li><a href="profesionales">Profesionales</a></li>
                <li><a href="exposiciones">Exposiciones</a></li>
                <li><a href="coleccionistas">Coleccionistas</a></li>
                <li><a href="ventas">Ventas</a></li>
                <li><a href="colecciones">Colecciones</a></li>
                <li><a href="contacto.html">Contacto</a></li>
            </ul>
        </nav>

        <div class="content">
            <h1 class="page-title">Lista de Obras de Arte</h1>
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Artista</th>
                        <th>Año</th>
                        <th>Técnica</th>
                        <th>Dimensiones</th>
                        <th>Estado</th>
                        <th>Precio</th>
                        <th>Observaciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="obra" items="${obras}">
                        <tr>
                            <td>${obra.id}</td>
                            <td>${obra.titulo}</td>
                            <td>${obra.artista}</td>
                            <td>${obra.anioCreacion}</td>
                            <td>${obra.tecnica}</td>
                            <td>${obra.dimensiones}</td>
                            <td>${obra.estado}</td>
                            <td>${obra.precio}</td>
                            <td>${obra.observaciones}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <h2 class="form-title">Agregar una Nueva Obra</h2>
            <form action="obras" method="post" class="styled-form">
                <div class="form-row">
                    <div class="form-group">
                        <label for="titulo">Título:</label>
                        <input type="text" id="titulo" name="titulo" required>
                    </div>
                    <div class="form-group">
                        <label for="artista">Artista:</label>
                        <input type="text" id="artista" name="artista" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="anioCreacion">Año de Creación:</label>
                        <input type="number" id="anioCreacion" name="anioCreacion" required>
                    </div>
                    <div class="form-group">
                        <label for="tecnica">Técnica:</label>
                        <input type="text" id="tecnica" name="tecnica" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="dimensiones">Dimensiones:</label>
                        <input type="text" id="dimensiones" name="dimensiones">
                    </div>
                    <div class="form-group">
                        <label for="estado">Estado:</label>
                        <select id="estado" name="estado">
                            <option value="Exhibición">En Exhibición</option>
                            <option value="Almacén">En Almacén</option>
                            <option value="Vendida">Vendida</option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="precio">Precio:</label>
                        <input type="number" id="precio" name="precio" step="0.01">
                    </div>
                    <div class="form-group">
                        <label for="observaciones">Observaciones:</label>
                        <textarea id="observaciones" name="observaciones"></textarea>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="imagen">Imagen:</label>
                        <input type="text" id="imagen" name="imagen">
                    </div>
                </div>

                <div class="form-row">
                    <button type="submit" class="btn-submit">Agregar Obra</button>
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