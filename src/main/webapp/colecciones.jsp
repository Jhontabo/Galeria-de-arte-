<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
        <title>Colecciones</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/index.css">

    </head>

    <body>
        <div class="container">

            <nav class="navbar">
                <h2 class="logo">Galeria de Arte</h2>
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
            <header>
                <h1>Lista de Colecciones</h1>
            </header>

            <table class="styled-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Descripcion</th>
                        <th>Responsable</th>
                        <th>Estilo</th>
                        <th>Obras Incluidas</th>
                        <th>Fechas de Exhibicion</th>
                        <th>Sala Asignada</th>
                        <th>Observaciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="coleccion" items="${colecciones}">
                        <tr>
                            <td>${coleccion.id}</td>
                            <td>${coleccion.nombre}</td>
                            <td>${coleccion.descripcion}</td>
                            <td>${coleccion.responsable}</td>
                            <td>${coleccion.estilo}</td>
                            <td>
                                <c:forEach var="obra" items="${coleccion.obrasIncluidas}">
                                    ${obra},
                                </c:forEach>
                            </td>
                            <td>${coleccion.fechasExhibicion}</td>
                            <td>${coleccion.salaAsignada}</td>
                            <td>${coleccion.observaciones}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <section class="form-section">
                <h2>Agregar una Nueva Coleccion</h2>
                <form action="colecciones" method="post" class="styled-form">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required><br>

                    <label for="descripcion">Descripcion:</label>
                    <textarea id="descripcion" name="descripcion" required></textarea><br>

                    <label for="responsable">Responsable:</label>
                    <input type="text" id="responsable" name="responsable"><br>

                    <label for="estilo">Estilo:</label>
                    <input type="text" id="estilo" name="estilo"><br>

                    <label for="fechasExhibicion">Fechas de Exhibicion:</label>
                    <input type="text" id="fechasExhibicion" name="fechasExhibicion"><br>

                    <label for="salaAsignada">Sala Asignada:</label>
                    <input type="text" id="salaAsignada" name="salaAsignada"><br>

                    <label for="observaciones">Observaciones:</label>
                    <textarea id="observaciones" name="observaciones"></textarea><br>

                    <label for="imagen">Imagen:</label>
                    <input type="text" id="imagen" name="imagen"><br>

                    <button type="submit" class="btn-submit">Agregar Coleccion</button>
                </form>
            </section>
        </div>
    </body>

    </html>