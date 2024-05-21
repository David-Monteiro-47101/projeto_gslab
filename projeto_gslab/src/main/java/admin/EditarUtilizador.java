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
import jakarta.servlet.http.HttpSession;
import projeto_gslab.DataBaseConfig;

@WebServlet("/EditarUtilizador")
public class EditarUtilizador extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static DataBaseConfig cp = null;

    public void init() {
        String dbUrl = getServletContext().getInitParameter("db.url");
        String dbUsername = getServletContext().getInitParameter("db.user");
        String dbPass = getServletContext().getInitParameter("db.password");

        Object pool = getServletContext().getAttribute("connPoolId");
        if ( pool == null) {
            cp = new DataBaseConfig(dbUrl, dbUsername, dbPass);
            getServletContext().setAttribute("connPoolId", cp);
        }else if(pool instanceof DataBaseConfig) {
            cp = (DataBaseConfig)pool;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("Utilizador");

        String query = "SELECT u.nome, u.password, r.papel FROM projeto.utilizador u INNER JOIN projeto.utilizador_tem_role t ON u.email = t.email INNER JOIN projeto.role r ON t.papel = r.papel WHERE u.email = '" + email + "';";
        ResultSet rs = cp.executeQuery(query);

        String nome = "";
        String password = "";
        ArrayList<String> roles = new ArrayList<>();

        try {
            while (rs.next()) {
                nome = rs.getString("nome");
                password = rs.getString("password");
                roles.add(rs.getString("papel"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
        }


        HttpSession sessionEditarUtilizador = request.getSession();
        sessionEditarUtilizador.setAttribute("email", email);


        request.setAttribute("nome", nome);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("roles", roles);
        
        System.out.println("nome: " + nome);
        System.out.println("email: " + email);
        System.out.println("password: " + password);
        System.out.println("roles: " + roles);

        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/EditarUtilizador.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}