package projeto_gslab;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.jdbcx.JdbcConnectionPool;

public class DataBaseConfig {
    private JdbcConnectionPool cp = null;

    
    public DataBaseConfig(String dbUrl, String dbUsername, String dbPass) {
        super();
        if(cp == null)
            cp = JdbcConnectionPool.create(dbUrl, dbUsername, dbPass);
        
     }
  

    public ResultSet executeQuery(String query) {
        try {
        	Connection conn = cp.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
            return null;
        }
    }

    public int executeUpdate(String query) {
        try {
        	Connection conn = cp.getConnection();
             Statement stmt = conn.createStatement();
            int linhasAfetadas = stmt.executeUpdate(query);
            System.out.println(linhasAfetadas + " linhas afetadas.");
            conn.close();
            return linhasAfetadas;
        } catch (SQLException e) {
            System.err.println("Erro ao executar a atualização: " + e.getMessage());
            return -1;
        }
    }
}
