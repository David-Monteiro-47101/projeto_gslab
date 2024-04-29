package testes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;

import java.sql.*;

public class Teste {
    public static void main(String[] args) {
        // URL de conexão JDBC para se conectar ao banco de dados H2 em modo servidor TCP/IP
        String url = "jdbc:h2:tcp://localhost/~/test";

        // Credenciais de acesso a database (nome de usuário e senha)
        String user = "sa";
        String password = "47101";

        Connection conn = null; // Inicializa a conexão como nula

        try {
            // Estabelecer conexão com a database
            conn = DriverManager.getConnection(url, user, password);
            
            // Verifica se a conexão foi bem-sucedida
            if (conn != null) {
                System.out.println("Conexão bem-sucedida à base de dados H2!");
            } else {
                System.err.println("Erro: Conexão à base de dados H2 retornou nula.");
            }

            // Não esqueça de fechar a conexão quando terminar de usá-la
            conn.close();
        } catch (SQLException e) {
            // Se ocorrer um erro durante a conexão, imprima uma mensagem de erro
            System.err.println("Erro ao conectar à base de dados H2: " + e.getMessage());
        }
    }
}


