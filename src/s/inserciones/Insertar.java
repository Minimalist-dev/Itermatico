package s.inserciones;

import l.Expresion;
import static front.Itermatico.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import s.Conexion;
import l.GetAndSet;
import s.Identificador;

public class Insertar {
    public static void 
    cierreDeSesion() {
        try {
            String sql = "INSERT INTO historial"
            + "(usuario, historiador, inicioDeSesion, inicioDeSesionFecha, cierreDeSesion, cierreDeSesionFecha, "
            + "actualizacion, actualizacionFecha, historialEliminado, historialEliminadoFecha, perfilEliminado, "
            + "perfilEliminadoFecha) VALUES (?, ?, '', NOW(), ?, NOW(), '', NOW(), '', NOW(), '', NOW())";
            
            PreparedStatement prepared = Conexion.getConexion().prepareStatement(sql);
            prepared.setInt(1, GetAndSet.getId());
            prepared.setString(2, Identificador.ipv4());
            prepared.setString( 3, "Cierre de sesion: ");
            
            prepared.executeUpdate();
        } catch(SQLException e) {
            Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, e);
        } finally { 
            try {
                Conexion.getConexion().close();
            } catch(SQLException e) {
                Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, e);
            }
        } 
    }
    public static void 
    sugerencias() {
        PreparedStatement prepared = null;
        
        String sql = "INSERT INTO sugerencias(nombre, asunto, correo, mensaje, fecha) VALUES (?, ?, ?, ?, NOW())";
        
        GetAndSet.setAlias(sugerenciaNombre.getText());
        GetAndSet.setAsunto(sugerenciaAsunto.getText());
        GetAndSet.setEmail(sugerenciaCorreo.getText());
        GetAndSet.setMensaje(sugerenciaMensaje.getText());
        
        if(!Expresion.nombre(GetAndSet.getAlias())) {
            sugerenciaError.setText("El nombre no es valido.");
        } else if(!Expresion.correo(GetAndSet.getEmail())) {
            sugerenciaError.setText("El correo no es valido.");
        } else if(GetAndSet.getMensaje().isEmpty()) {
            sugerenciaError.setText("El mensaje no pueden estar vacias.");
        } else {
            try {
                sugerenciaError.setId("error_null");
                sugerenciaError.setText("Su Sugerencias a sido enviada.");

                prepared = Conexion.getConexion().prepareStatement(sql);
                prepared.setString(1, GetAndSet.getAlias());
                prepared.setString(2, GetAndSet.getAsunto());
                prepared.setString(3, GetAndSet.getEmail());
                prepared.setString(4, GetAndSet.getMensaje());
            
                prepared.executeUpdate();
            } catch (SQLException e) { 
                sugerenciaError.setId("error");
                sugerenciaError.setText("No hay conexion"); 
            } finally { 
                try {
                    Conexion.getConexion().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }     
        }
    }
}
