package admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import projeto_gslab.DataBaseConfig;
import projeto_gslab.DataBaseManager;

@WebServlet("/EditarSala")
public class EditarSala extends HttpServlet {
	
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
		
		String sala = request.getParameter("Sala");
		System.out.println("Sala: " + sala);
		
		String query = "SELECT localizacao, capacidade FROM projeto.sala WHERE nome = '" + sala + "';"; 
        ResultSet rs = cp.executeQuery(query);
		
		String localizacao = null;
		Integer capacidade = null;
		
        try {
            while (rs.next()) {
            	localizacao = rs.getString("localizacao");
            	capacidade = rs.getInt("capacidade");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
        }
        

        HttpSession sessionEditarSala = request.getSession();
        sessionEditarSala.setAttribute("name", sala);

        
        request.setAttribute("nome", sala);
		request.setAttribute("localizacao", localizacao);
		request.setAttribute("capacidade", capacidade);
		
		System.out.println("localizacao: " + localizacao);
		System.out.println("capacidade: " + capacidade);
		
		//dataBaseManager.disconnect();
		
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/EditarSala.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
