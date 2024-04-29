package testes;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MeuServlet")
public class MeuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Definindo uma string para ser enviada para o JSP
        String mensagem = "Olá, mundo!";
        
        // Definindo o atributo "mensagem" no objeto request
        request.setAttribute("mensagem", mensagem);
        
        // Encaminhando a requisição para o arquivo JSP
        request.getRequestDispatcher("/pagina.jsp").forward(request, response);
        //response.setContentType("text/html; charset=UTF-8");
        //getServletContext().getRequestDispatcher("/pagina.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request, response);
    }
}
