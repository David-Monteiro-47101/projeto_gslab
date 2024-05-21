<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Eliminar Sala</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        html, body {
            height: 100%;
        }

        body {
            display: flex;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="content text-center">
            <h1>Selecione a sala que quer eliminar</h1>

            <%
            ArrayList<String> salas = (ArrayList<String>) request.getAttribute("salas");
            %>

            <div class="select-container">
                <form action="EliminarSala" method="post" accept-charset="UTF-8">
                    <div class="form-group d-flex justify-content-center">
                        <label for="Sala" class="mr-2">Sala:</label>
                        <select id="Sala" name="Sala" class="form-control" style="width: 300px;">
                            <option value="" selected disabled hidden></option>

                            <%
                            if(salas != null){
                                for (int i = 0; i < salas.size(); i++) {
                                    String sala = salas.get(i);
                            %>
                                    <option value="<%=sala%>"><%=sala%></option>
                            <%	
                                }
                            }
                            %>
                        </select>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Eliminar Sala">
                </form>
            </div>
            
        </div>
    </div>
</body>
</html>