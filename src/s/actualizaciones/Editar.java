package s.actualizaciones;

import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import l.Sesion;
import s.Conexion;
import l.GetAndSet;
import s.Identificador;

public class Editar {
    public static void 
    perfil() {
        int usuario = 1;
        String datosMatriz[] = { "", "", "", "" };  
        
        String sql = "INSERT INTO historial"
        + "(usuario, historiador, inicioDeSesion, inicioDeSesionFecha, cierreDeSesion, cierreDeSesionFecha, "
        + "actualizacion, actualizacionFecha, historialEliminado, historialEliminadoFecha, perfilEliminado, "
        + "perfilEliminadoFecha) VALUES (?, ?, '', NOW(), '', NOW(), ?, NOW(), '', NOW(), '', NOW())";

        try {
            PreparedStatement prepared = Conexion.getConexion().prepareStatement(sql);
            prepared.setInt(1, GetAndSet.getId());
            prepared.setString(2, Identificador.ipv4());
            
            switch (usuario) {
                case 1:  
                    if(!Sesion.editarNombre.getText().equals(GetAndSet.getNombre())) {
                        datosMatriz[0] = Sesion.editarNombre.getText();
                    } else { datosMatriz[0] = ""; }
                //break;
                case 2:  
                    if(!Sesion.editarApellido.getText().equals(GetAndSet.getApellido())) { 
                        datosMatriz[1] = Sesion.editarApellido.getText();
                    } else { datosMatriz[1] = ""; }
                //break;
                case 3:  
                    if(!Sesion.editarClave.getText().equals(GetAndSet.getPassword())) {
                        datosMatriz[2] = Sesion.editarClave.getText();
                    } else { datosMatriz[2] = ""; }
                break;
                default: datosMatriz[3] = "Invalid update";
                break;
            }
            
            prepared.setString( 3,
                "Nombre: "      + datosMatriz[0] + "\n" +
                "Apellido: "    + datosMatriz[1] + "\n" +
                "Contraseña: "  + datosMatriz[2]
            );
            
            prepared.executeUpdate();
        
            String sqlActualizar = "UPDATE usuarios "
            + "SET nombre = ?, apellido = ?, clave = ?, foto = ?, nombreDeFoto = ? WHERE correo = ?";

            try {
                PreparedStatement preparedActualizar = Conexion.getConexion().prepareStatement(sqlActualizar);
                preparedActualizar.setString(1, Sesion.editarNombre.getText());
                preparedActualizar.setString(2, Sesion.editarApellido.getText());
                preparedActualizar.setString(3, Sesion.editarClave.getText());
                preparedActualizar.setBinaryStream(4 , GetAndSet.getImagen(), GetAndSet.getLongitudDeImagen());
                preparedActualizar.setString(5, GetAndSet.getNombreDeImagen());
                preparedActualizar.setString(6, Sesion.editarCorreo.getText());
                preparedActualizar.executeUpdate();
            } catch (SQLException sqleObject) {
                Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, sqleObject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Editar.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            try {
                Conexion.getConexion().close();
            } catch (SQLException ex) {
                Logger.getLogger(Editar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
}
