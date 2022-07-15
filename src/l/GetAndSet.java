package l;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GetAndSet {
    public static Stage stage;
    public static Scene scene;
        
    public static HBox top;
    public static VBox left;
    public static StackPane center;
    public static VBox right;
    public static HBox bottom;
    
    public static int id;
    public static String identificador;
    public static String nombre;
    public static String apellido;
    public static String correo;
    public static String password;
    public static Date fecha;
    public static String nombreDeImagen;
    
    public static String alias;
    public static String asunto;
    public static String email;
    public static String mensaje;
    
    public static int idHistorial;
    public static File fila;
    public static FileInputStream imagen;
    public static int longitudDeImagen;
    public static String nombreDeFoto;

    public static String getNombreDeImagen() {
        return nombreDeImagen;
    }

    public static void setNombreDeImagen(String nombreDeImagen) {
        GetAndSet.nombreDeImagen = nombreDeImagen;
    }

    public static int getLongitudDeImagen() {
        return longitudDeImagen;
    }

    public static void setLongitudDeImagen(int longitudDeImagen) {
        GetAndSet.longitudDeImagen = longitudDeImagen;
    }

    public static File getFilaOjbject() {
        return fila;
    }

    public static void setFilaOjbject(File filaOjbject) {
        GetAndSet.fila = filaOjbject;
    }

    public static FileInputStream getImagen() {
        return imagen;
    }

    public static void setImagen(FileInputStream imagen) {
        GetAndSet.imagen = imagen;
    }

   

    public static String getNombreDeFoto() {
        return nombreDeFoto;
    }

    public static void setNombreDeFoto(String nombreDeFoto) {
        GetAndSet.nombreDeFoto = nombreDeFoto;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        GetAndSet.stage = stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        GetAndSet.scene = scene;
    }

    public static HBox getTop() {
        return top;
    }

    public static void setTop(HBox top) {
        GetAndSet.top = top;
    }

    public static VBox getLeft() {
        return left;
    }

    public static void setLeft(VBox left) {
        GetAndSet.left = left;
    }

    public static StackPane getCenter() {
        return center;
    }

    public static void setCenter(StackPane center) {
        GetAndSet.center = center;
    }

    public static VBox getRight() {
        return right;
    }

    public static void setRight(VBox right) {
        GetAndSet.right = right;
    }

    public static HBox getBottom() {
        return bottom;
    }

    public static void setBottom(HBox bottom) {
        GetAndSet.bottom = bottom;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        GetAndSet.id = id;
    }

    public static String getIdentificador() {
        return identificador;
    }

    public static void setIdentificador(String identificador) {
        GetAndSet.identificador = identificador;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        GetAndSet.nombre = nombre;
    }

    public static String getApellido() {
        return apellido;
    }

    public static void setApellido(String apellido) {
        GetAndSet.apellido = apellido;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        GetAndSet.correo = correo;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        GetAndSet.password = password;
    }

    public static Date getFecha() {
        return fecha;
    }

    public static void setFecha(Date fecha) {
        GetAndSet.fecha = fecha;
    }

    public static String getAlias() {
        return alias;
    }

    public static void setAlias(String alias) {
        GetAndSet.alias = alias;
    }

    public static String getAsunto() {
        return asunto;
    }

    public static void setAsunto(String asunto) {
        GetAndSet.asunto = asunto;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        GetAndSet.email = email;
    }

    public static String getMensaje() {
        return mensaje;
    }

    public static void setMensaje(String mensaje) {
        GetAndSet.mensaje = mensaje;
    }

    public static int getIdHistorial() {
        return idHistorial;
    }

    public static void setIdHistorial(int idHistorial) {
        GetAndSet.idHistorial = idHistorial;
    }
    
}