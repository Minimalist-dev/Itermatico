package s;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    private static final String URL         = "jdbc:mysql://localhost:3306/iterador";
    private static final String USUARIO     = "root";
    private static final String PASSWORD    = "";

    static public Connection
    getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD); 
    }
    static public Statement 
    getStatement() throws SQLException {
        return Conexion.getConexion().createStatement();
    }
    static public ResultSet 
    setResultSet(String sql) throws SQLException {
        return Conexion.getStatement().executeQuery(sql);
    }
}
