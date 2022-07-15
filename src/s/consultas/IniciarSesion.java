package s.consultas;

import l.Expresion;
import static front.Itermatico.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import l.Sesion;
import s.Conexion;
import l.GetAndSet;
import s.Identificador;

public class IniciarSesion {
    public static boolean 
    iniciarSesion() {
        GetAndSet.setCorreo(iniciarCorreo.getText());
        GetAndSet.setPassword(iniciarClave.getText());
        
        if(!Expresion.correo(GetAndSet.getCorreo())){
            iniciarError.setText("El correo no es valido.");
        } else if(!Expresion.password(GetAndSet.getPassword())) {
            iniciarError.setText("La contraseña no es valida.");
        } else {
            String sql = "SELECT correo, clave FROM usuarios WHERE correo = ? AND clave = ? LIMIT 1";
            
            try {
                PreparedStatement prepared = Conexion.getConexion().prepareStatement(sql);
                prepared.setString(1, GetAndSet.getCorreo());
                prepared.setString(2, GetAndSet.getPassword());
                ResultSet resultSet = prepared.executeQuery();
                
                String sqlInsertar = "INSERT INTO historial"
                + "(usuario, historiador, inicioDeSesion, inicioDeSesionFecha, cierreDeSesion, cierreDeSesionFecha, "
                + "actualizacion, actualizacionFecha, historialEliminado, historialEliminadoFecha, perfilEliminado, "
                + "perfilEliminadoFecha) VALUES (?, ?, ?, NOW(), '', NOW(), '', NOW(), '', NOW(), '', NOW())";
                
                if (resultSet.next()) {
                    if (GetAndSet.getCorreo().equals(resultSet.getString(1)) 
                    && GetAndSet.getPassword().equals(resultSet.getString(2))) {
                        iniciarError.setText("");
                        GetAndSet.setCorreo(GetAndSet.getCorreo());
                        Sesion sesion = new Sesion(); borderPane.setCenter(sesion.sesion());
                        
                        PreparedStatement preparedHistorial = Conexion.getConexion().prepareStatement(sqlInsertar);
                        preparedHistorial.setInt(1, GetAndSet.getId());
                        preparedHistorial.setString(2, Identificador.ipv4());
                        preparedHistorial.setString( 3, "Inicio de sesion: ");

                        preparedHistorial.executeUpdate();
                        
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    iniciarError.setText("Los datos son incorrectos");
                }
                
                return false;
            } catch (SQLException e) {
                iniciarError.setText("No hay conexion"); return false; 
            } finally { 
                try {
                    Conexion.getConexion().close();
                } catch (SQLException sqle) {
                    Logger.getLogger(IniciarSesion.class.getName()).log(Level.SEVERE, null, sqle);
                }
            }
        }
        
        return false;   
    }
}
