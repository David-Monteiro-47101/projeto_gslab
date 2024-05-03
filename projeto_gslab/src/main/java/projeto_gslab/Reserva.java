package projeto_gslab;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Reserva")
public class Reserva extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String sala = request.getParameter("sala");
    	System.out.println("Reserva sala: " + sala);
    	
    	
    	String data = request.getParameter("data");
    	System.out.println("Reserva data: " + data);
    	
        String selectedCellsString = request.getParameter("selectedCells");
        List<String> selectedCells = Arrays.asList(selectedCellsString.split(";"));
        // Imprime a lista de células selecionadas no console do servidor
        System.out.println("Celulas selecionadas: " + selectedCells);
  

        
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/Reserva.html").forward(request, response);
    	
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}