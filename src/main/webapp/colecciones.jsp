<!DOCTYPE html>
<html>
<head>
    <title>Colecciones</title>
    <style>
        /* General styles */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        header h1 {
            text-align: center;
            color: #444;
            margin-bottom: 20px;
        }

        /* Table styles */
        .styled-table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 0.9em;
            font-family: 'Arial', sans-serif;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
        }

        .styled-table thead tr {
            background-color: #009879;
            color: #ffffff;
            text-align: left;
        }

        .styled-table th,
        .styled-table td {
            padding: 12px 15px;
            border: 1px solid #dddddd;
        }

        .styled-table tbody tr {
            border-bottom: 1px solid #dddddd;
        }

        .styled-table tbody tr:nth-of-type(even) {
            background-color: #f3f3f3;
        }

        .styled-table tbody tr:last-of-type {
            border-bottom: 2px solid #009879;
        }

        /* Form styles */
        .form-section {
            margin-top: 30px;
        }

        .styled-form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .styled-form label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .styled-form input,
        .styled-form textarea {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #dddddd;
            border-radius: 4px;
            font-size: 1em;
        }

        .styled-form textarea {
            height: 100px;
        }

        .btn-submit {
            background-color: #009879;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .btn-submit:hover {
            background-color: #007961;
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>Lista de Colecciones</h1>
        </header>
        
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

        <section class="form-section">
            <h2>Agregar una Nueva Colección</h2>
            <form action="colecciones" method="post" class="styled-form">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required><br>

                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" required></textarea><br>

                <label for="responsable">Responsable:</label>
                <input type="text" id="responsable" name="responsable"><br>

                <label for="estilo">Estilo:</label>
                <input type="text" id="estilo" name="estilo"><br>

                <label for="fechasExhibicion">Fechas de Exhibición:</label>
                <input type="text" id="fechasExhibicion" name="fechasExhibicion"><br>

                <label for="salaAsignada">Sala Asignada:</label>
                <input type="text" id="salaAsignada" name="salaAsignada"><br>

                <label for="observaciones">Observaciones:</label>
                <textarea id="observaciones" name="observaciones"></textarea><br>

                <label for="imagen">Imagen:</label>
                <input type="text" id="imagen" name="imagen"><br>

                <button type="submit" class="btn-submit">Agregar Colección</button>
            </form>
        </section>
    </div>
</body>
</html>
