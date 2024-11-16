<!DOCTYPE html>
<html lang="es">

<head>
    <title>Colecciones - Galería de Arte</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/tablas.css">

    <link
        href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Roboto:wght@400;500&display=swap"
        rel="stylesheet">
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
        <h1 class="page-title">Lista de colecciones</h1>

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

        <h2 class="form-title">Agregar colecciones</h2>

        <form action="colecciones" method="post" class="styled-form">

            <div class="form-row">
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required>

                </div>

                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required>

                </div>

            </div>


            <div class="form-group">
                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" required></textarea>

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
                <div class="form-group">
                    <label for="fechasExhibicion">Fechas de Exhibición:</label>
                    <input type="text" id="fechasExhibicion" name="fechasExhibicion">


                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="salaAsignada">Sala Asignada:</label>
                    <input type="text" id="salaAsignada" name="salaAsignada">

                </div>
                <div class="form-group">

                    <label for="imagen">Imagen:</label>
                    <input type="text" id="imagen" name="imagen">

                </div>

            </div>

            <div class="form-group">
                <label for="observaciones">Observaciones:</label>
                <textarea id="observaciones" name="observaciones"></textarea>

            </div>

            <button type="submit" class="btn-submit">Agregar Colección</button>
        </form>
    </div>



    <footer>
        <div class="footer-bottom">
            <p>&copy; 2024 Galería de Arte. Oscar Tajumbina.</p>
        </div>
    </footer>

</body>

</html>