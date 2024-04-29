<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Editar Sala</title>
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
            <%-- Recuperando os atributos da solicitação --%>
            
            <%
          	 String nome = (String) request.getAttribute("nome"); 
             String localizacao = (String) request.getAttribute("localizacao"); 
             Integer capacidade = (Integer) request.getAttribute("capacidade"); 
             %>
             
    <div class="form-container">
        <h2 class="text-center mb-4">Editar Detalhes da Sala</h2>
        <form id="formEditarSala" action="SalvarDetalhesSala" method="post" accept-charset="UTF-8">
            <div class="form-group">
                <label for="newName">Novo nome da sala:</label>
                <input type="text" class="form-control" id="newName" name="newName" value="<%= nome %>">
            </div>
            <div class="form-group">
                <label for="location">Nova localização da sala:</label>
                <input type="text" class="form-control" id="location" name="location" value="<%= localizacao %>">
            </div>
            <div class="form-group">
                <label for="capacity">Nova capacidade da sala:</label>
                <input type="number" class="form-control" id="capacity" name="capacity" value="<%= capacidade %>">
            </div>
            <!-- Botão para enviar o formulário de edição da sala -->
            <button type="submit" class="btn btn-primary btn-block">Salvar</button>
        </form>
    </div>
</body>
</html>

</html>