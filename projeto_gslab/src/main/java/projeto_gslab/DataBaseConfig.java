package projeto_gslab;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.h2.jdbcx.JdbcConnectionPool;

import DAO.Agendamento;
import DAO.Sala;

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
    
    
    public ArrayList<Agendamento> getAgendamentos(String query) {
        ArrayList<Agendamento> agendamentos = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = cp.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Agendamento agendamento = new Agendamento(
                    rs.getInt("id_agendamento"),
                    rs.getString("disciplina"),
                    rs.getString("turma"),
                    rs.getDate("dia"),
                    rs.getString("professor"),
                    rs.getString("observacoes"),
                    rs.getInt("slot"),
                    rs.getString("sala_nome"),
                    rs.getString("utilizador_email")
                );
                agendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Statement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }

        return agendamentos;
    }
    
    public ArrayList<Sala> getSalas(String query) {
        ArrayList<Sala> salas = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = cp.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Sala sala = new Sala(
                    rs.getString("nome"),
                    rs.getInt("capacidade"),
                    rs.getString("localizacao"),
                    rs.getString("email_utilizador")
                );
                salas.add(sala);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Statement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }

        return salas;
    }
}
