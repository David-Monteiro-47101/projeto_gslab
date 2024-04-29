package testes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import projeto_gslab.DataBaseManager;

public class Exemplo {
	
	public static void main(String[] args) {
		
		DataBaseManager dataBaseManager = new DataBaseManager();
		
        String query = "SELECT * FROM projeto.sala"; // Substitua YOUR_TABLE pelo nome da sua tabela
        ResultSet rs = dataBaseManager.executeQuery(query);
        
        ArrayList<String> salas = new ArrayList<>();
		
        try {
            while (rs.next()) {
                // Processa o resultado aqui
            	salas.add(rs.getString("nome"));
                System.out.println("nome: " + rs.getString("nome")); // Substitua YOUR_COLUMN pelo nome da sua coluna
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
        }
        
        System.out.println("salas: " + salas);
        dataBaseManager.disconnect();
    }

}
