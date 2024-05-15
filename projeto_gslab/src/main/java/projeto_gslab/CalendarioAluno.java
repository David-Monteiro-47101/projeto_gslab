package projeto_gslab;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CalendarioAluno")
public class CalendarioAluno extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sala = request.getParameter("sala");
        request.setAttribute("sala", sala);
        System.out.println("Calendario sala: " + sala);

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

        // Forward the request back to the JSP with the data as an attribute
        request.setAttribute("sala", sala);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CalendarioAluno.jsp");
        dispatcher.forward(request, response);
    }
}