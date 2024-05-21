package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projeto_gslab.DataBaseConfig;

import java.io.IOException;

@WebServlet("/EliminarUtilizador")
public class EliminarUtilizador extends HttpServlet {
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

		String email = request.getParameter("Utilizador");
		System.out.println("email: " + email);

		String query1 = "DELETE FROM projeto.utilizador_tem_role WHERE email = '" + email + "';";
		int rowsAffected1 = cp.executeUpdate(query1);

		String query2 = "DELETE FROM projeto.utilizador WHERE email = '" + email + "';";
		
		int rowsAffected2 = cp.executeUpdate(query2);
		
        request.setAttribute("alert", "Utilizador eliminado com sucesso.");

		response.getWriter().append("Served at: ").append(request.getContextPath());
		getServletContext().getRequestDispatcher("/Admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
