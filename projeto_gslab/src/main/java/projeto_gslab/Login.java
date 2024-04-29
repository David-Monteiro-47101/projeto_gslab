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
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		//String email = "aa@gmail.com";
		//System.out.println("Email : " + email);
		
		String password = request.getParameter("password");
		//String password = "aa123";
		//System.out.println("password : " + password);
		
		DataBaseManager dataBaseManager = new DataBaseManager();
		
		
		String query = "SELECT u.email, u.nome, ur.papel " +
	               	   "FROM projeto.utilizador u " +
	               	   "LEFT JOIN projeto.utilizador_tem_role ur ON u.email = ur.email " +
	               	   "WHERE u.email = '" + email + "' AND u.password = '" + password + "'";
 
		
		ResultSet rs = dataBaseManager.executeQuery(query);
		
		String email1 = null;
		ArrayList<String> papeis = new ArrayList<>();
		String nome = null;
		
		try {
            while(rs!=null && rs.next()) {
            	email1 = rs.getString("email");
            	papeis.add(rs.getString("papel"));
            	nome = rs.getString("nome");
            }
            request.setAttribute("nome", nome);
            request.setAttribute("email", email);
            request.setAttribute("papeis", papeis);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
		
		
		if(email1 == null) {
		    System.out.println("Email ou password incorretos!");
		    request.setAttribute("erro", "Email ou password incorretos!");
		    getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		} 
		
		HttpSession session = request.getSession();
		session.setAttribute("email", email);

		
		//System.out.println("Login.java papel : " + papeis);
		
		dataBaseManager.disconnect();

		response.setContentType("text/html; charset=UTF-8");
		getServletContext().getRequestDispatcher("/Roles.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
