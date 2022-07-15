package l;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class Foto {
    public static Button guardar = new Button("Guardar Foto");
    
    public static void 
    seleccionar() {
        FileChooser fileChooserObject = new FileChooser();
        configurar(fileChooserObject);
        File fileObject = fileChooserObject.showOpenDialog(GetAndSet.getStage());
        
        if (fileObject != null) {
            guardar(fileObject);
            guardar.setDisable(false);
            Sesion.seleccionar.setDisable(true);

            try {
                FileInputStream fileInputStreamObject = new FileInputStream(fileObject.getAbsoluteFile());
                GetAndSet.setImagen(fileInputStreamObject);
                
                int longitud = longitud = (int) fileObject.getAbsoluteFile().length();
                GetAndSet.setLongitudDeImagen(longitud);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Foto.class.getName()).log(Level.SEVERE, null, ex);
            }             
        }     
    }
    private static void 
    configurar(FileChooser fileChooserObject){                           
        fileChooserObject.setTitle("Open Resource File");
        fileChooserObject.setInitialDirectory(new File(System.getProperty("user.home"))); 
        fileChooserObject.getExtensionFilters().addAll(new ExtensionFilter("Seleccionar Imagen", "*.png", "*.jpg", "*.gif")); 
    }
    private static void 
    guardar(File fileObject){
        Stage secondStage = new Stage();

        Label name = new Label(fileObject.getAbsolutePath());
        Image image = new Image(fileObject.toURI().toString());
//        ImageView imageView = new ImageView();
        guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                FileChooser fileChooserObject = new FileChooser();
                fileChooserObject.setTitle("Save Image");
                fileChooserObject.setInitialDirectory(new File(System.getProperty("user.home"))); 
                
                File fileObject = fileChooserObject.showSaveDialog(secondStage);
                
                if (fileObject != null) {
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(Sesion.editarFoto.getImage(), null), "jpg", fileObject);
                        GetAndSet.setNombreDeFoto(fileObject.getName());
//                        GetAndSet.setNombreDeFotoObject(name.getText());
                    } catch (IOException ex) {
                        Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
                    }                              
                }
                
                guardar.setDisable(true);
                Sesion.seleccionar.setDisable(false);
            }
        });

        Sesion.editarFoto.setFitHeight(400);
        Sesion.editarFoto.setPreserveRatio(true);
        Sesion.editarFoto.setImage(image);
        Sesion.editarFoto.setSmooth(true);
        Sesion.editarFoto.setCache(true);    
    }
}
