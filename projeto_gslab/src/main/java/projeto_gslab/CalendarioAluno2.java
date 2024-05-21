package projeto_gslab;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DAO.Agendamento;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CalendarioAluno2")
public class CalendarioAluno2 extends HttpServlet {
	
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
        String sala = request.getParameter("sala");
        request.setAttribute("sala", sala);
        System.out.println("Calendario sala: " + sala);

        // Set the data attribute to the current date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        request.setAttribute("data", currentDate);

        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/CalendarioAluno.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
        String sala = request.getParameter("sala");
        
        System.out.println("Calendario sala: " + sala);
        System.out.println("Calendario data: " + data);

        // Now you can use the date and room name for your logic
        // ...
        
        String query = "SELECT * "
                + "FROM projeto.agendamento "
                + "WHERE sala_nome = '" + sala +"' "
                + "AND dia >= DATEADD('DAY', 1 - DAYOFWEEK('" + data +"'), '" + data +"') "
                + "AND dia < DATEADD('DAY', 8 - DAYOFWEEK('" + data +"'), '" + data +"');"; 
        
        ArrayList<Agendamento> agendamentos = cp.getAgendamentos(query);
        
        
        for (Agendamento agendamento : agendamentos) 
            System.out.println(agendamento);

        // Forward the request back to the JSP with the data as an attribute
        request.setAttribute("sala", sala);
        request.setAttribute("data", data); // Pass the selected date to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CalendarioAluno.jsp");
        dispatcher.forward(request, response);
    }
}