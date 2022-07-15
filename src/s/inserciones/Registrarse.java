package s.inserciones;

import l.Expresion;
import front.Itermatico;
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

public class Registrarse {
    public static void 
    registrarse() {
        PreparedStatement prepared              = null;
        PreparedStatement preparedRegistrarse   = null;
        PreparedStatement preparedSesion        = null;
        ResultSet res                           = null;
        ResultSet resHistorial                  = null;
        
        GetAndSet.setNombre(registrarseNombre.getText());
        GetAndSet.setApellido(registrarseApellido.getText());
        GetAndSet.setCorreo(registrarseCorreo.getText());
        GetAndSet.setPassword(registrarseClave.getText());

        if(!Expresion.nombre(GetAndSet.getNombre())) {
            registrarseError.setText("El nombre no es valido.");
        } else if(!Expresion.apellidos(GetAndSet.getApellido())) {
            registrarseError.setText("El apellido no es valido.");
        } else if(!Expresion.correo(GetAndSet.getCorreo())) {
            registrarseError.setText("El correo no es valido.");
        } else if(!Expresion.password(GetAndSet.getPassword())) {
            registrarseError.setText("La contraseña no es valida.");
        } else {
            String sqlCorreo = "SELECT * FROM usuarios WHERE correo = ? LIMIT 1";

            try {
                prepared = Conexion.getConexion().prepareStatement(sqlCorreo);
                prepared.setString(1, GetAndSet.getCorreo());
              
                res = prepared.executeQuery();
                
                if(res.next()) {
                    registrarseError.setText("EL CORREO YA ESTA RESGISTRADO.");
                } else {
                    String sqlRegistrarse = "INSERT INTO usuarios "
                    + "(ip, nombre, apellido, correo, clave, fecha, nombreDeFoto) "
                    + "VALUES (?, ?, ?, ?, ?, NOW(), ?)";

                    String sql = "SELECT * FROM usuarios WHERE correo = ? AND clave = ? LIMIT 1";

                    try {
                        preparedRegistrarse = Conexion.getConexion().prepareStatement(sqlRegistrarse);
                        preparedRegistrarse.setString(1, Identificador.ipv4());
                        preparedRegistrarse.setString(2, GetAndSet.getNombre());
                        preparedRegistrarse.setString(3, GetAndSet.getApellido());
                        preparedRegistrarse.setString(4, GetAndSet.getCorreo());
                        preparedRegistrarse.setString(5, GetAndSet.getPassword());
                        preparedRegistrarse.setString(6, "avatar.png");
                                
                        preparedRegistrarse.executeUpdate();
                        
                        
                        preparedSesion = Conexion.getConexion().prepareStatement(sql);
                        preparedSesion.setString(1, GetAndSet.getCorreo());
                        preparedSesion.setString(2, GetAndSet.getPassword());
                        
                        resHistorial = prepared.executeQuery();
                        
                        String sqlHistorial = "INSERT INTO historial"
                        + "(usuario, historiador, inicioDeSesion, inicioDeSesionFecha, cierreDeSesion, cierreDeSesionFecha, "
                        + "actualizacion, actualizacionFecha, historialEliminado, historialEliminadoFecha, perfilEliminado, "
                        + "perfilEliminadoFecha) VALUES (?, ?, ?, NOW(), '', NOW(), '', NOW(), '', NOW(), '', NOW())";

                        if(resHistorial.next()) { 
                            registrarseError.setText("");
                            GetAndSet.setCorreo(GetAndSet.getCorreo());
                            Sesion sesion = new Sesion(); borderPane.setCenter(sesion.sesion());
                            
                            PreparedStatement preparedHistorial = Conexion.getConexion().prepareStatement(sqlHistorial);
                            preparedHistorial.setInt(1, GetAndSet.getId());
                            preparedHistorial.setString(2, GetAndSet.getIdentificador());
                            preparedHistorial.setString( 3, "Inicio de sesion: ");

                            preparedHistorial.executeUpdate();
                        }
                    } catch(SQLException e) {
                        System.out.println(e);
                    } finally {
                        try {
                            if(Conexion.getStatement() != null) { Conexion.getStatement().close(); }
                        } catch (SQLException ex) {
                            Logger.getLogger(Itermatico.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } catch(SQLException e) { 
                registrarseError.setText("No hay conexion"); 
            } finally { 
                try {
                    Conexion.getConexion().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Registrarse.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
