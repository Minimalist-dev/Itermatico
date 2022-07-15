package s.inserciones;

import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart;
import static l.botones.Estadistica.pieChart;
import s.Conexion;
import s.consultas.Consultar;

public class Estadisticas {
    public static void 
    celsius() {
        String sql = "SELECT * FROM estadisticas_celsius WHERE id >= ?";
        
        try {
 
            PreparedStatement prepared = Conexion.getConexion().prepareStatement(sql);
            prepared.setInt(1, 1);
            
            ResultSet res = prepared.executeQuery();

            pieChart.getData().clear();
            
            while(res.next()) {
                PieChart.Data data = new PieChart.Data(res.getInt(2) + " grados celsius", res.getInt(3));
                pieChart.getData().add(data); 
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally { 
            try {
                Conexion.getConexion().close();
            } catch (SQLException sqleObject) {
                Logger.getLogger(Consultar.class.getName()).log(Level.SEVERE, null, sqleObject);
            }
        }
    }
    public static void 
    celsius(int celsius) {
        String sql = "SELECT * FROM estadisticas_celsius WHERE celsius = ?";

        try {
            PreparedStatement prepared = Conexion.getConexion().prepareStatement(sql);
            prepared.setInt(1, celsius);

            ResultSet res = prepared.executeQuery();

            if(res.next()) {
                String sqlActualizar = "UPDATE estadisticas_celsius SET cantidad = cantidad + 1 WHERE celsius = ?";


                try {
                    PreparedStatement prep = Conexion.getConexion().prepareStatement(sqlActualizar);
                    prep.setInt(1, celsius);

                    prep.executeUpdate();
                } catch(SQLException e) {
                    System.out.println(e);
                } finally {
                    try {
                        if (Conexion.getStatement() != null) { Conexion.getStatement().close(); }
                    } catch(SQLException ex) {
                        Logger.getLogger(Estadisticas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                String sqlInsertar = "INSERT INTO estadisticas_celsius(celsius, cantidad) VALUES(?, ?)";

                try {
                    PreparedStatement prep = Conexion.getConexion().prepareStatement(sqlInsertar);
                    prep.setInt(1, celsius);
                    prep.setInt(2, 1);

                    prep.executeUpdate();
                } catch(SQLException e) {
                    System.out.println(e);
                } finally {
                    try {
                        if (Conexion.getStatement() != null) { Conexion.getStatement().close(); }
                    } catch (SQLException ex) {
                        Logger.getLogger(Estadisticas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch(SQLException e) { 
            out.println("No hay conexion");
        } finally { 
            try {
                Conexion.getConexion().close();
            } catch (SQLException ex) {
                Logger.getLogger(Registrarse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
