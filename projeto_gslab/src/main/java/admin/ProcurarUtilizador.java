package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projeto_gslab.DataBaseConfig;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/ProcurarUtilizador")
public class ProcurarUtilizador extends HttpServlet {
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
		String query = "SELECT * FROM projeto.utilizador"; 
        ResultSet rs = cp.executeQuery(query);
        
        ArrayList<String> utilizadores = new ArrayList<>();
		
        try {
            while (rs.next()) {
            	utilizadores.add(rs.getString("email")); 
            }
            request.setAttribute("utilizadores", utilizadores);
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
        }
        
        response.setContentType("text/html; charset=UTF-8");
        
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            getServletContext().getRequestDispatcher("/ProcurarUtilizadorEliminar.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/ProcurarUtilizador.jsp").forward(request, response);
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}





