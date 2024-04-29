<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Exemplo de Servlet e JSP</title>
</head>
<body>
    <h1>Exemplo de Servlet e JSP</h1>
    <ul>
        <% 
            String mensagem = (String) request.getAttribute("mensagem");
        %>
    <li><b>A mensagem recebida do servlet é:</b> <%= mensagem %></li>
</body>
</html>
