package testes;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ExemploServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        // Este método é chamado quando o servlet é inicializado
        System.out.println("Servlet Exemplo inicializado com sucesso!");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configura o tipo de conteúdo da resposta
        response.setContentType("text/html");

        // Define algumas informações para serem enviadas para o JSP
        String mensagem = "Olá, mundo!";
        int numero = 42;

        // Define os atributos da requisição para serem acessados pelo JSP
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("numero", numero);

        // Encaminha a requisição para o JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/exemplo.jsp");
        dispatcher.forward(request, response);
    }
}



