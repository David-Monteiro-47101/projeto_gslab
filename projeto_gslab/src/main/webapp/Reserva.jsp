<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reserva</title>
    <!-- Adicionando Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<%
    String sala = (String) request.getAttribute("sala");
    String selectedSlots = (String) request.getAttribute("selectedSlots");

    // Split the selectedSlots string into an array of slots
    String[] slots = selectedSlots.split(";");
%>

<div class="container mt-5">
    <h1 class="mb-4">Sala: <%= sala %></h1>
    <h2>Slots selecionados:</h2>
    <ul class="list-group">
        <%
            // Process each slot
            for (String slot : slots) {
                // Split the slot string into day and position
                String[] parts = slot.split(",");
                String day = parts[0];
                String position = parts[1];
        %>
            <li class="list-group-item">Dia: <%= day %>, Slot: <%= position %></li>
        <%
            }
        %>
    </ul>
    <!-- Adicionando o formulário para reserva -->
    <form action="Reserva3" method="post">
        <!-- Campos ocultos para enviar dados -->
        <input type="hidden" name="sala" value="<%= sala %>">
        <input type="hidden" name="selectedSlots" value="<%= selectedSlots %>">
        
        <!-- Campos de texto para informações adicionais -->
        <div class="form-group">
            <label for="turma">Turma:</label>
            <input type="text" class="form-control" id="turma" name="turma">
        </div>
        <div class="form-group">
            <label for="disciplina">Disciplina:</label>
            <input type="text" class="form-control" id="disciplina" name="disciplina">
        </div>
        <div class="form-group">
            <label for="professor">Professor:</label>
            <input type="text" class="form-control" id="professor" name="professor">
        </div>
        <div class="form-group">
            <label for="observacoes">Observações:</label>
            <textarea class="form-control" id="observacoes" name="observacoes"></textarea>
        </div>
        
        <!-- Botões para reservar -->
        <div class="mt-3">
            <button type="submit" name="action" value="reserve_esta_slot" class="btn btn-primary mr-2">Reservar</button>
        </div>
    </form>
</div>

</body>
</html>

