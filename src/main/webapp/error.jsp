<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f4;
            padding: 50px;
        }

        .error {
            color: #e74c3c;
            font-size: 24px;
        }

        .message {
            margin-top: 20px;
            font-size: 18px;
        }

        a {
            color: #3498db;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <div class="error">¡Ha ocurrido un error!</div>
    <div class="message">${error}</div>
    <a href="profesionales">Volver a Profesionales</a>
</body>

</html>