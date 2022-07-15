package s.consultas;

import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import l.Constructor;
import l.Sesion;
import s.Conexion;
import l.GetAndSet;
import s.Identificador;

public class Consultar {
    public static void 
    perfil() {
        String sql ="SELECT * FROM usuarios WHERE correo = ? ";
        
        try {
            PreparedStatement prepared = Conexion.getConexion().prepareStatement(sql);
            prepared.setString(1, GetAndSet.getCorreo());
            
            ResultSet res = prepared.executeQuery();
            
            while (res.next()) {
                GetAndSet.setId(res.getInt(1));
                GetAndSet.setIdentificador(res.getString(2));
                GetAndSet.setNombre(res.getString(3));
                GetAndSet.setApellido(res.getString(4));
                GetAndSet.setFecha(res.getDate(7));
                GetAndSet.setNombreDeImagen(res.getString(9));
            }
        } catch(SQLException e) {
            System.out.println(e);
        } finally { 
            try {
                Conexion.getConexion().close();
            } catch(SQLException e) {
                Logger.getLogger(Consultar.class.getName()).log(Level.SEVERE, null, e);
            }
        } 
    }
    public static void 
    noticias() {
        String sql = "SELECT * FROM noticias WHERE id >= ? ORDER BY id DESC LIMIT 50";
        
        try {
 
            PreparedStatement prepared = Conexion.getConexion().prepareStatement(sql);
            prepared.setInt(1, 1);
            
            ResultSet res = prepared.executeQuery();

            while(res.next()) {
                Sesion.observarNoticias.addAll(new Constructor(
                    res.getInt(1), res.getString(2)
                ));  
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally { 
            try {
                Conexion.getConexion().close();
            } catch(SQLException e) {
                Logger.getLogger(Consultar.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    public static void 
    historial() {
        String sql = "SELECT * FROM historial WHERE usuario = ? ORDER BY id DESC";
        
        try {
            PreparedStatement prepared = Conexion.getConexion().prepareStatement(sql);
            prepared.setInt(1, GetAndSet.getId());
            
            ResultSet res = prepared.executeQuery();
            
            while (res.next()) {
                if(!res.getString(4).equals("")) {
                    Sesion.observableListStaticType.addAll(new Constructor(
                        res.getInt(1),
                        "\n\tInicio de sesion el: " + res.getString(5) + "\n\tDesde: " + Identificador.ipv4()+"\n" + " "
                    ));  
                } else if(!res.getString(6).equals("")) {
                    Sesion.observableListStaticType.addAll(new Constructor(
                        res.getInt(1),
                        "\n\tCierre de sesion el: " + res.getString(7) + "\n\tDesde: " + Identificador.ipv4()+"\n" + " "
                    )); 
                } else if(!res.getString(8).equals("")) {
                    Sesion.observableListStaticType.addAll(new Constructor(
                        res.getInt(1),
                        "\n\tActualización de datos el: " + res.getString(9) + "\n\tDesde: " + Identificador.ipv4()+"\n" + " "
                    )); 
                } else {}
            }
        } catch(SQLException e) {
            System.out.println(e);
        } finally { 
            try {
                Conexion.getConexion().close();
            } catch (SQLException sqle) {
                Logger.getLogger(Consultar.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
    }
}
