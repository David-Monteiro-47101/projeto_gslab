package admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import projeto_gslab.DataBaseConfig;


@WebServlet("/SalvarDetalhesUtilizador")
public class SalvarDetalhesUtilizador extends HttpServlet {
	
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

		String name = request.getParameter("newName");
		String newEmail = request.getParameter("newEmail");
		String password = request.getParameter("password");
		String[] role = request.getParameterValues("role");
    	
    	HttpSession sessionEditarUtilizador = request.getSession();
    	String email = (String) sessionEditarUtilizador.getAttribute("email");
    	
    	System.out.println("name: " + name);
    	System.out.println("email: " + email);
		System.out.println("newEmail: " + newEmail);
		System.out.println("password: " + password);
		System.out.println("role: " + Arrays.toString(role));
    	
    	if (isUsernameInUse(newEmail) && !email.equals(newEmail)) {
            System.out.println("Um Utilizador com esse email já existe: " + newEmail);
            request.setAttribute("alert", "Um Utilizador com esse email já existe.");
            response.setContentType("text/html; charset=UTF-8");
            getServletContext().getRequestDispatcher("/Admin.jsp").forward(request, response);
    	}

        String deleteQuery = "DELETE FROM projeto.utilizador_tem_role WHERE email = '" + email + "'";
        cp.executeUpdate(deleteQuery);

        // Inserir informações do usuário
        String query = "UPDATE projeto.utilizador " +
                "SET nome = '" + name + "', email = '" + newEmail + "', password = '" + password + "' " +
                "WHERE email = '" + email + "'";

        int rowsAffected = cp.executeUpdate(query);

        // Inserir informações das roles de um usuário
        for (String papel : role) {
            String insertQuery = "INSERT INTO projeto.utilizador_tem_role (email, papel) VALUES ('" + newEmail + "', '" + papel + "')";
            cp.executeUpdate(insertQuery);
        }
    	
        if (rowsAffected == 0) {
            System.out.println("Erro: " + name);
            request.setAttribute("alert", "Erro:");
        } else {
            System.out.println("Utilizador editado com sucesso: " + name);
            request.setAttribute("alert", "Utilizador editado com sucesso.");
        }
        
        //dataBaseManager.disconnect();
        
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/Admin.jsp").forward(request, response);
    	
    	
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    private boolean isUsernameInUse(String newEmail) {    	
        String query = "SELECT email FROM projeto.utilizador WHERE email = '" + newEmail + "'";
        
        ResultSet rs = cp.executeQuery(query);

        try {
            return rs.next(); // Retorna true se houver resultados, indicando que o username está em uso
        } catch (SQLException e) {
            System.out.println("Erro ao executar a ASDGFJWABDAVDPASDASYDIVADASconsulta: " + e.getMessage());
            e.printStackTrace();
            System.err.println(e.toString());


        return false;
    }

    }
}