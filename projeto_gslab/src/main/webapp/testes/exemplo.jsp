<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Exemplo de Servlet e JSP</title>
</head>
<body>
    <h1>Exemplo de Servlet e JSP</h1>
    <%
        // Adicione logs para verificar os valores dos atributos
        System.out.println("Valor do atributo 'mensagem' no JSP: " + request.getAttribute("mensagem"));
        System.out.println("Valor do atributo 'numero' no JSP: " + request.getAttribute("numero"));
    %>
    <p>Mensagem: <%= request.getAttribute("mensagem") %></p>
    <p>Número: <%= request.getAttribute("numero") %></p>
</body>
</html>


