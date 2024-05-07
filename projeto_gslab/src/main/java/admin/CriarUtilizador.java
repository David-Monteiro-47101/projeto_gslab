package admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projeto_gslab.DataBaseConfig;
import projeto_gslab.DataBaseManager;

@WebServlet("/CriarUtilizador")
public class CriarUtilizador extends HttpServlet {
	
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
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String[] roles = request.getParameterValues("role[]");

        //System.out.println("CriarUtilizador nome: " + nome);
        //System.out.println("CriarUtilizador email: " + email);
        //System.out.println("CriarUtilizador password: " + password);   

        //DataBaseManager dataBaseManager = new DataBaseManager();
        
        // Inserir informações do usuário
        String query = "INSERT INTO projeto.utilizador(email, nome, password) "
                    + "SELECT * FROM (SELECT '" + email + "', '" + nome + "', '" + password + "') AS tmp "
                    + "WHERE NOT EXISTS (SELECT email FROM projeto.utilizador WHERE email = '" + email + "') LIMIT 1;";

        int rowsAffected = cp.executeUpdate(query);
        
        if (rowsAffected == 0) {
            System.out.println("Um utilizador com esse email já existe: " + email);
            request.setAttribute("alert", "Um utilizador com esse email já existe.");
        } else {
            System.out.println("Utilizador adicionado com sucesso: " + email);
            request.setAttribute("alert", "Utilizador adicionado com sucesso.");
            
            // Inserir papéis do usuário apenas se o usuário for criado com sucesso
            if (roles != null && roles.length > 0) {
                StringBuilder query2 = new StringBuilder("INSERT INTO projeto.utilizador_tem_role(email, papel) VALUES ");
                for (String role : roles) { 
                	System.out.println("Role: " + role);
                    query2.append("('").append(email).append("', '").append(role).append("'),");
                }
                query2.deleteCharAt(query2.length() - 1); // Remover a vírgula extra no final

                cp.executeUpdate(query2.toString());
            }
        }

        //dataBaseManager.disconnect();
        
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/Admin.jsp").forward(request, response);
    }


    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
