<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="d-flex justify-content-center align-items-center vh-100">
    <div class="text-center w-50">
        <!-- Mensagem de boas-vindas -->
        <h1 id="welcome-message" class="m-3"></h1>
        
        <!-- Botões -->
        <div class="row justify-content-around">
            <div class="col-4 mb-2">
                <button onclick="window.location.href='CriarSala.html'" class="btn btn-primary w-100">Criar Sala</button>
            </div>
            <div class="col-4 mb-2">
                <button onclick="window.location.href='ProcurarSala?action=edit'" class="btn btn-primary w-100">Editar Sala</button>
            </div>
            <div class="col-4 mb-2">
                <button onclick="window.location.href='ProcurarSala?action=delete'" class="btn btn-primary w-100">Eliminar Sala</button>
            </div>
        </div>
        <div class="row justify-content-around">
            <div class="col-4">
                <button onclick="window.location.href='CriarUtilizador.html'" class="btn btn-primary w-100">Criar Utilizador</button>
            </div>
            <div class="col-4">
                <button onclick="window.location.href='ProcurarUtilizador?action=edit'" class="btn btn-primary w-100">Editar Utilizador</button>
            </div>
            <div class="col-4">
                <button onclick="window.location.href='ProcurarUtilizador?action=delete'" class="btn btn-primary w-100">Eliminar Utilizador</button>
            </div>
        </div>
        
        <!-- Script para exibir a mensagem de alerta -->
        <script>
            var alertMessage = "<%= request.getAttribute("alert") %>";
            if (alertMessage) {
                alert(alertMessage);
            }
        </script>
    </div>
</div>
<script>
    document.getElementById('welcome-message').textContent = 'Admin Menu';// + admin;
</script>
</body>
</html>