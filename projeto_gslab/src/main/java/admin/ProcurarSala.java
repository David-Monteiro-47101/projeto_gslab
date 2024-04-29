package admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projeto_gslab.DataBaseManager;

@WebServlet("/ProcurarSala")
public class ProcurarSala extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        
        String query = "SELECT * FROM projeto.sala"; 
        ResultSet rs = dataBaseManager.executeQuery(query);
        
        ArrayList<String> salas = new ArrayList<>();
		
        try {
            while (rs.next()) {
            	salas.add(rs.getString("nome")); 
            }
            request.setAttribute("salas", salas);
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
        }
        
        //System.out.println("servlet salas: " + salas);
        
        dataBaseManager.disconnect();
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/ProcurarSala.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

