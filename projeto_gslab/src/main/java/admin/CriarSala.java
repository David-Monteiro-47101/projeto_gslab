package admin;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import projeto_gslab.DataBaseConfig;

@WebServlet("/CriarSala")
public class CriarSala extends HttpServlet {
	
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
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        
        String nome = request.getParameter("nome");
        String localizacao = request.getParameter("localizacao");
        
        String capacidadeString = request.getParameter("capacidade");
        Integer capacidade = null; // Usamos Integer em vez de int para permitir null

        if (capacidadeString != null && !capacidadeString.isEmpty()) {
            capacidade = Integer.parseInt(capacidadeString);
        }

        //System.out.println("CriarSala nome: " + nome);
        //System.out.println("CriarSala localizacao: " + localizacao);
        //System.out.println("CriarSala capacidade: " + capacidade);
        //System.out.println("CriarSala email: " + email);
        
        //DataBaseManager dataBaseManager = new DataBaseManager();
        
        String query = "INSERT INTO projeto.sala(nome, capacidade, localizacao, email_utilizador) "
                + "SELECT * FROM (SELECT '" + nome + "', " + capacidade + ", '" + localizacao + "', '" + email + "') AS tmp "
                + "WHERE NOT EXISTS (SELECT nome FROM projeto.sala WHERE nome = '" + nome + "') LIMIT 1;";

        int rowsAffected = cp.executeUpdate(query);
        
        if (rowsAffected == 0) {
            System.out.println("Uma sala com esse nome já existe: " + nome);
            request.setAttribute("alert", "Uma sala com esse nome já existe.");
        } else {
            System.out.println("Sala adicionada com sucesso: " + nome);
            request.setAttribute("alert", "Sala adicionada com sucesso.");
        }
        
        //dataBaseManager.disconnect();
        
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/Admin.jsp").forward(request, response);
    }

    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
