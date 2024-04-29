<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Roles</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            width: 18rem;
            margin: 20px;
        }
        .card:hover {
            box-shadow: 0 0.5em 0.5em -0.4em #007BFF;
            transform: translateY(-0.25em);
        }
    </style>
</head>
<body>
<%
    String nome = (String) request.getAttribute("nome");
	String email = (String) request.getAttribute("email");
    ArrayList<String> papeis = (ArrayList<String>) request.getAttribute("papeis");
    
    //System.out.println("Roles.jsp nome : " + nome);
    //System.out.println("Roles.jsp email : " + email);
    //System.out.println("Roles.jsp papeis : " + papeis);
%>
        
<h1 id="welcome-message"></h1>
<h2>Entrar como:</h2>
<div class="d-flex justify-content-center">
    <% 
        if (papeis != null && !papeis.isEmpty()) {
            for (String papel : papeis) {
    %>
                <div class="card" onclick="enterAs('<%= papel %>')">
                    <div class="card-body text-center">
                        <% 
                            String iconClass = "";
                            switch (papel) {
                                case "admin":
                                    iconClass = "fas fa-user-shield fa-3x";
                                    break;
                                case "coordenador":
                                    iconClass = "fas fa-user-tie fa-3x";
                                    break;
                                case "professor":
                                    iconClass = "fas fa-chalkboard-teacher fa-3x";
                                    break;
                                // Adicione outros papéis e ícones conforme necessário
                            }
                        %>
                        <i class="<%= iconClass %>"></i>
                        <h5 class="card-title"><%= papel %></h5>
                    </div>
                </div>
    <%
            }
        }
    %>
</div>

<script>
    var nome = "<%= nome %>";
    var email = "<%= email %>";
    document.getElementById('welcome-message').textContent = 'Olá, ' + nome;

    function enterAs(role) {
        switch(role) {
            case 'admin':
                window.location.href = 'Admin.jsp'; // Redireciona para a página do Administrador
                break;
            case 'coordenador':
            	window.location.href = 'Salas'; // Redireciona para o servlet "Salas"
                break;
            case 'professor':
            	window.location.href = 'Salas'; // Redireciona para o servlet "Salas"
                break;
            // Adicione outras opções de função e redirecionamentos conforme necessário
        }
    }
</script>
</body>
</html>

