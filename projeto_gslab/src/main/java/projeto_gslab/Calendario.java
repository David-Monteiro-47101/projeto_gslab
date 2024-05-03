package projeto_gslab;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Calendario")
public class Calendario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String sala = request.getParameter("sala");
    	request.setAttribute("sala", sala);
    	System.out.println("Calendario sala: " + sala);
    	
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/Calendario.jsp").forward(request, response);
    	
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}