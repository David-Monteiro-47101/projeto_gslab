<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!-- CSS do Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
        }
        .login-container {
            width: 300px;
            padding: 16px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        }
        .login-container h2 {
            text-align: center;
            margin-bottom: 24px;
        }
        .login-container input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 4px;
            border: 1px solid #ddd;
        }
        .login-container button {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .login-container button:hover {
            background-color: #0056b3;
        }
        #back-link {
            position: absolute;
            top: 30px;
            left: 50px;
        }
    </style>
</head>
<body>
<a id="back-link" href="javascript:history.back()">Voltar</a>
<div class="login-container">
	<% 
	String erro = "";
	if(request.getAttribute("erro") != null){
		erro = (String) request.getAttribute("erro");
	}
	%>
    <h2>Login</h2>
    <% if(erro!=null){%>
		<label for="error" style="color: red;"><%= erro %></label>
	<% }%>
    <form action="Login" method="post">
        <input type="text" name="email" placeholder="Email" id="email" required>
        <input type="password" name="password" placeholder="Password" id="password"  required>
        <div class="button">
            <input type="submit" value="Login">
        </div>
    </form>
</div>
</body>
</html>