<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Exposiciones</title>
        <link
            href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Roboto:wght@300;400;500;700&display=swap"
            rel="stylesheet">

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
            <h1 class="page-title">Lista de Exposiciones</h1>

            <table class="styled-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Fecha de Inicio</th>
                        <th>Fecha de Fin</th>
                        <th>Responsable</th>
                        <th>Temática</th>
                        <th>Sala Asignada</th>
                        <th>Descripción</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="exposicion" items="${exposiciones}">
                        <tr>
                            <td data-label="ID">${exposicion.id}</td>
                            <td data-label="Título">${exposicion.titulo}</td>
                            <td data-label="Fecha de Inicio">${exposicion.fechaInicio}</td>
                            <td data-label="Fecha de Fin">${exposicion.fechaFin}</td>
                            <td data-label="Responsable">${exposicion.responsable}</td>
                            <td data-label="Temática">${exposicion.tematica}</td>
                            <td data-label="Sala Asignada">${exposicion.salaAsignada}</td>
                            <td data-label="Descripción">${exposicion.descripcion}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <h2 class="form-title">Agregar una Nueva Exposición</h2>
            <form class="styled-form" action="exposiciones" method="post">
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
                        <label for="fechaInicio">Fecha de Inicio:</label>
                        <input type="date" id="fechaInicio" name="fechaInicio" required>
                    </div>
                    <div class="form-group">
                        <label for="fechaFin">Fecha de Fin:</label>
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
                        <textarea id="descripcion" name="descripcion"></textarea>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="imagen">Imagen:</label>
                        <input type="text" id="imagen" name="imagen">
                    </div>
                </div>

                <button type="submit" class="btn-submit">Agregar Exposición</button>
            </form>
        </div>

        <footer>
            <div class="footer-bottom">
                <p>&copy; 2024 Galería de Arte. Oscar Tajumbina.</p>
            </div>
        </footer>
    </body>

    </html>