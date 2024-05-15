<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Calendário</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .table-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center; /* Center align the room name */
            height: 100vh;
            margin: auto; /* Center the container on the page */
        }
        .selector {
            align-self: center; /* Center align the selector */
            margin-bottom: 10px; /* Add a margin below the selector */
        }
        .scrollable-table {
            overflow-y: auto;
            height: 600px; /* Increase the height of the table */
        }
        .selected {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
<%
     String sala = (String)request.getAttribute("sala");
%>
<div class="table-container">
    <h2 class="room-name"><%= sala %></h2> <!-- Adiciona o nome da sala -->
    <form action="CalendarioAluno" method="post">
        <input class="selector" type="date" id="dateSelector" name="data" onchange="updateHeaders()">
        <input type="hidden" name="sala" id="salaInput" value="<%= sala %>"> <!-- Adiciona o nome da sala -->
    </form>
    <div class="scrollable-table">
        <table class="table table-bordered">
            <thead id="tableHead">
            <tr>
                <th>Horário</th>
                <th id="day1">Segunda</th>
                <th id="day2">Terça</th>
                <th id="day3">Quarta</th>
                <th id="day4">Quinta</th>
                <th id="day5">Sexta</th>
                <th id="day6">Sábado</th>
            </tr>
            </thead>
            <tbody>
            <script>
                var selectedRoom = "<%= sala %>"; // Nome da sala

                var startHour = 8;
                var endHour = 23;
                var interval = 0.5;
                for (var i = startHour; i < endHour; i += interval) {
                    var nextInterval = i + interval;
                    document.write('<tr>');
                    document.write('<td>' + formatTime(i) + ' - ' + formatTime(nextInterval) + '</td>');
                    for (var j = 0; j < 6; j++) {
                        document.write('<td data-day="' + j + '" data-position="' + i + '"></td>'); // Removed onclick attribute
                    }
                    document.write('</tr>');
                }

                function formatTime(time) {
                    var hours = Math.floor(time);
                    var minutes = (time % 1 !== 0 ? '30' : '00');
                    return hours + ':' + minutes;
                }

                function updateHeaders() {
                    var dateSelector = document.getElementById('dateSelector');
                    var selectedDate = new Date(dateSelector.value);
                    var shouldSubmitForm = dateSelector.value !== '';

                    // If the date selector doesn't have a value, set it to the current date
                    if (!shouldSubmitForm) {
                        selectedDate = new Date();
                        dateSelector.value = selectedDate.toISOString().split('T')[0];
                    }

                    var startOfWeek = new Date(selectedDate);
                    startOfWeek.setDate(startOfWeek.getDate() - ((startOfWeek.getDay() === 0 ? 6 : startOfWeek.getDay() - 1)));
                    for (var i = 0; i < 6; i++) {
                        var date = new Date(startOfWeek);
                        date.setDate(date.getDate() + i);
                        document.getElementById('day' + (i+1)).textContent = ['Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'][i] + ' (' + date.toLocaleDateString('pt-PT') + ')';
                        // Set the data-date attribute of each cell in this column to the date
                        document.querySelectorAll('[data-day="' + i + '"]').forEach(function(cell) {
                            cell.setAttribute('data-date', date.toLocaleDateString('pt-PT'));
                        });
                    }

                    // Only submit the form if the date selector has a value
                    if (shouldSubmitForm) {
                        document.forms[0].submit();
                    }
                }

                // Call updateHeaders when the page loads
                updateHeaders();
            </script>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>