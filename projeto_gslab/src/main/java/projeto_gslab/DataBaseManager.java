package projeto_gslab;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManager {
    // URL de conexão JDBC para se conectar a database H2 em modo servidor TCP/IP
    private static final String url = "jdbc:h2:tcp://localhost/~/test";
    private static final String user = "sa";
    private static final String password = "47101";
    
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public ResultSet executeQuery(String query) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
            return null;
        } 
    } 
    
    public int executeUpdate(String query) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " linhas afetadas.");
            return rowsAffected;
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta de atualização: " + e.getMessage());
            return -1; // Retorna -1 para indicar um erro
        } 
    }

    
    public boolean disconnect() {
		try {
			if(rs!=null) {
				rs.close();
				rs=null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null && !conn.isClosed()) {
				if(!conn.getAutoCommit())
					conn.commit();
				conn.close();
				conn = null;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.toString());
			return false;
		}
	}
}