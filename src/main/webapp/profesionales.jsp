<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profesionales del Arte</title>
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
                        <th>Premios</th>
                        <th>Estilo</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="profesional" items="${profesionales}">
                        <tr>
                            <td data-label="ID">${profesional.id}</td>
                            <td data-label="Nombre">${profesional.nombreCompleto}</td>
                            <td data-label="Especialidad">${profesional.especialidad}</td>
                            <td data-label="Años de Experiencia">${profesional.aniosExperiencia}</td>
                            <td data-label="Proyectos Previos">${profesional.proyectosPrevios}</td>
                            <td data-label="Contacto">${profesional.contacto}</td>
                            <td data-label="Institución Educativa">${profesional.institucionEducativa}</td>
                            <td data-label="Premios">${profesional.premios}</td>
                            <td data-label="Estilo">${profesional.estiloPreferido}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <h2 class="form-title">Agregar un Nuevo Profesional</h2>
            <form class="styled-form" action="profesionales" method="post">
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
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="imagen">Imagen:</label>
                        <input type="text" id="imagen" name="imagen">
                    </div>
                </div>

                <button type="submit" class="btn-submit">Agregar Profesional</button>
            </form>
        </div>

        <footer>
            <div class="footer-bottom">
                <p>&copy; 2024 Galería de Arte. Oscar Tajumbina.</p>
            </div>
        </footer>
    </body>

    </html>