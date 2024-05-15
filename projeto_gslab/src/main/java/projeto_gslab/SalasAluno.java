package projeto_gslab;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SalasAluno")
public class SalasAluno extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    private static DataBaseConfig cp = null;

    public void init() throws ServletException {
    	String dbUrl = getServletContext().getInitParameter("db.url");
        String dbUsername = getServletContext().getInitParameter("db.user");
        String dbPass = getServletContext().getInitParameter("db.password");    	
    	
    	Object pool = getServletContext().getAttribute("connPoolId");
    	if ( pool == null) {
            cp = new DataBaseConfig(dbUrl, dbUsername, dbPass);
            getServletContext().setAttribute("connPoolId", cp);
    	} else if(pool instanceof DataBaseConfig) {
    		cp = (DataBaseConfig)pool;	
    	}
    }
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        //DataBaseManager dataBaseManager = new DataBaseManager();
        
        String query = "SELECT * FROM projeto.sala"; 
        ResultSet rs = cp.executeQuery(query);
        
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
        //dataBaseManager.disconnect();
        
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/Salas.jsp").forward(request, response);
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
