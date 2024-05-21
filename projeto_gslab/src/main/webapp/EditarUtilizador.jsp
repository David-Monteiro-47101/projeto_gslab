<%@ page import="java.util.ArrayList" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Editar Utilizador</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            width: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
    <%-- Recuperando os atributos da solicita��o --%>
    <%
        String nome = (String) request.getAttribute("nome"); 
        String email = (String) request.getAttribute("email"); 
        String password = (String) request.getAttribute("password"); 
        //String role = (String) request.getAttribute("role");
        ArrayList<String> roles = (ArrayList<String>) request.getAttribute("roles");

    %>
    <div class="form-container">
        <h2 class="text-center mb-4">Editar Detalhes do Utilizador</h2>
        <form id="formEditarUtilizador" action="SalvarDetalhesUtilizador" method="post" accept-charset="UTF-8">
            <div class="form-group">
                <label for="newName">Novo nome:</label>
                <input type="text" class="form-control" id="newName" name="newName" value="<%= nome %>">
            </div>
            <div class="form-group">
                <label for="newEmail">Novo email:</label>
                <input type="email" class="form-control" id="newEmail" name="newEmail" value="<%= email %>">
            </div>
            <div class="form-group">
                <label for="password">Nova password:</label>
                <input type="password" class="form-control" id="password" name="password" value="<%= password %>">
            </div>
            <div class="form-group">
                <label>Role</label>


                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="admin" id="admin" name="role" <%= (roles != null && roles.contains("admin")) ? "checked" : "" %>>
                    <label class="form-check-label" for="admin">Admin</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="coordenador" id="coordenador" name="role" <%= roles != null && roles.contains("coordenador") ? "checked" : "" %>>
                    <label class="form-check-label" for="coordenador">Coordenador</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="professor" id="professor" name="role" <%= roles != null && roles.contains("professor") ? "checked" : "" %>>
                    <label class="form-check-label" for="professor">Professor</label>
                </div>
            </div>
            <!-- Bot�o para enviar o formul�rio de edi��o do utilizador -->
            <button type="submit" class="btn btn-primary btn-block">Salvar</button>
        </form>
    </div>
</body>
</html>