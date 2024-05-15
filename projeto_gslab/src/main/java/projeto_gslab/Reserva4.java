package projeto_gslab;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Reserva4")
public class Reserva4 extends HttpServlet {
	
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
        // Obtenção dos parâmetros da requisição
        String sala = request.getParameter("sala");
        System.out.println("Sala: " + sala);
        String turma = request.getParameter("turma");
        System.out.println("Turma: " + turma);
        String disciplina = request.getParameter("disciplina");
        System.out.println("Disciplina: " + disciplina);
        String professor = request.getParameter("professor");
        System.out.println("Professor: " + professor);
        String observacoes = request.getParameter("observacoes");
        System.out.println("Observacoes: " + observacoes);
        String[] selectedSlots = request.getParameterValues("selectedSlots"); // selectedSlots é agora um array de strings
        System.out.println("Selected Slots: " + Arrays.toString(selectedSlots));
        
        // Obtenção do email do professor da sessão
        HttpSession session = request.getSession();
        String professor_reserva = (String) session.getAttribute("email");
        System.out.println("Professor Reserva: " + professor_reserva);
        
        // Iteração sobre os slots selecionados
        for (String slot : selectedSlots) {
            // Split on the semicolon first
            String[] slots = slot.split(";");
            for (String s : slots) {
                // Now split on the comma
                String[] parts = s.split(",");
                String dia = parts[0];
                String numeroSlot = parts[1];
                
                // Convert the date format from "dd/MM/yyyy" to "yyyy-MM-dd"
                SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    String reformattedStr = myFormat.format(fromUser.parse(dia));
                    dia = reformattedStr;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Dia: " + dia + ", Numero Slot: " + numeroSlot);
                
                // Construção da query de inserção para este slot
                String query = "INSERT INTO projeto.agendamento (`disciplina`, `turma`, `dia`, `professor`, `observacoes`, `slot`, `sala_nome`, `utilizador_email`)"
                        + " SELECT * FROM (SELECT '" + disciplina + "', '" + turma + "', '" + dia + "', '" + professor + "', '" + observacoes + "', '" + numeroSlot + "', '" + sala + "', '" + professor_reserva + "') AS tmp"
                        + " WHERE NOT EXISTS ("
                        + " SELECT 1 FROM projeto.agendamento"
                        + " WHERE `slot` = '" + numeroSlot + "' AND `dia` = '" + dia + "' AND `sala_nome` = '" + sala + "'"
                        + " ) LIMIT 1;";

                // Execução da query
                int rowsAffected = cp.executeUpdate(query);
                System.out.println("Rows affected: " + rowsAffected);
                
                if (rowsAffected == -1)
                	System.out.println("Erro com a base de dados");
                if (rowsAffected == 0) {
                    System.out.println("Esse slot já está ocupado: Dia " + dia + ", Slot " + numeroSlot);
                } else {
                    System.out.println("Slot adicionado com sucesso: Dia " + dia + ", Slot " + numeroSlot);
                }
            }
        }
        
        // Encaminhamento para a página de destino
        response.sendRedirect("SalasAluno");
    }

    	


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }

}