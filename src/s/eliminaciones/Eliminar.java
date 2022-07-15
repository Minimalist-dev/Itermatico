package s.eliminaciones;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import l.Sesion;
import s.Conexion;
import l.GetAndSet;

public class Eliminar {
    public static void 
    fila() {
        String sql = "DELETE FROM historial WHERE id = ? AND usuario = ?";

        try {
            PreparedStatement prepared = Conexion.getConexion().prepareStatement(sql);
            prepared.setObject(1, Sesion.tableView.getSelectionModel().getSelectedItem().getFila());
            prepared.setInt(2, GetAndSet.getId());
            prepared.executeUpdate();

            Sesion.observableListStaticType.remove(Sesion.tableView.getSelectionModel().getSelectedIndex());
            Sesion.filaSeleccionada.setText("");
        } catch (SQLException sqle) {
            Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, null, sqle);
        } finally { 
            try {
                Conexion.getConexion().close();
            } catch (SQLException sqle) {
                Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
    }
    public static void 
    historial() {
        String sql = "DELETE FROM historial WHERE usuario = ?";

        try {
            PreparedStatement prepared = Conexion.getConexion().prepareStatement(sql);
            prepared.setInt(1, GetAndSet.getId());
            prepared.executeUpdate();
        } catch (SQLException sqle) {
            Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, null, sqle);
        } finally { 
            try {
                Conexion.getConexion().close();
            } catch (SQLException sqle) {
                Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
    }
    public static void 
    perfil() {   
        String sql = "INSERT INTO historial"
        + "(usuario, historiador, inicioDeSesion, inicioDeSesionFecha, cierreDeSesion, cierreDeSesionFecha, "
        + "actualizacion, actualizacionFecha, historialEliminado, historialEliminadoFecha, perfilEliminado, "
        + "perfilEliminadoFecha) VALUES (?, ?, '', NOW(), '', NOW(), '', NOW(), '', NOW(), ?, NOW())";

        try {
            PreparedStatement insertar = Conexion.getConexion().prepareStatement(sql);
            insertar.setInt(1, GetAndSet.getId());
            insertar.setString(2, GetAndSet.getIdentificador());
            insertar.setString( 3, 
                "Nombre: "      + GetAndSet.getNombre()     + "\n" +
                "Apellido: "    + GetAndSet.getApellido()   + "\n" +
                "Correo: "      + GetAndSet.getCorreo()     + "\n" +
                "Contraseña: "  + GetAndSet.getPassword()   + "\n" +
                "Fecha: "       + GetAndSet.getFecha()      
            );
            
            insertar.executeUpdate();

            String sqlEliminar = "DELETE FROM usuarios WHERE correo = ?";

            try {
                PreparedStatement prepared = Conexion.getConexion().prepareStatement(sqlEliminar);
                prepared.setString(1, GetAndSet.getCorreo());

                prepared.executeUpdate();
            } catch (SQLException sqle) {
                Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, null, sqle);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            try {
                Conexion.getConexion().close();
            } catch (SQLException ex) {
                Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
}
