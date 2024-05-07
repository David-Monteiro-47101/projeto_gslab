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

@WebServlet("/SalvarDetalhesSala")
public class SalvarDetalhesSala extends HttpServlet {
	
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
        
    	String newName = request.getParameter("newName");
    	String location = request.getParameter("location");
    	String capacity = request.getParameter("capacity");
    	
    	HttpSession sessionEditarSala = request.getSession();
    	String name = (String) sessionEditarSala.getAttribute("name");
    	
    	System.out.println("newName: " + newName);
    	System.out.println("name: " + name);
    	System.out.println("location: " + location);
    	System.out.println("capacity: " + capacity);
    	
    	if (isUsernameInUse(newName) && !name.equals(newName)) {
            System.out.println("Uma sala com esse nome já existe: " + name);
            request.setAttribute("alert", "Uma sala com esse nome já existe.");
            response.setContentType("text/html; charset=UTF-8");
            getServletContext().getRequestDispatcher("/Admin.jsp").forward(request, response);
    	}
        
        // Inserir informações do usuário
    	String query = "UPDATE projeto.sala " +
                "SET nome = '" + newName + "', capacidade = " + capacity + ", localizacao = '" + location + "' " +
                "WHERE nome = '" + name + "'";
    	
    	int rowsAffected = cp.executeUpdate(query);
    	
        if (rowsAffected == 0) {
            System.out.println("Erro: " + name);
            request.setAttribute("alert", "Erro:");
        } else {
            System.out.println("Sala editada com sucesso: " + name);
            request.setAttribute("alert", "Sala editada com sucesso.");
        }
        
        //dataBaseManager.disconnect();
        
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/Admin.jsp").forward(request, response);
    	
    	
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    private boolean isUsernameInUse(String newUsername) {
    	DataBaseManager dataBaseManager = new DataBaseManager();
    	
        String query = "SELECT nome FROM projeto.sala WHERE nome = '" + newUsername + "'";
        
        ResultSet rs = dataBaseManager.executeQuery(query);

        try {
            return rs.next(); // Retorna true se houver resultados, indicando que o username está em uso
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.toString());
        } finally {
        	dataBaseManager.disconnect();
        }

        return false;
    }

}
