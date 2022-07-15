package l;

import front.Itermatico;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import s.actualizaciones.Editar;
import s.consultas.Consultar;
import s.eliminaciones.Eliminar;
import s.inserciones.Insertar;

public class Sesion extends Itermatico {
    //Editar Perfil ------------------------------------------------------------
    public static ImageView editarFoto          = new ImageView();
    public static Button seleccionar;
    public static TextField editarNombre        = new TextField();
    public static TextField editarApellido      = new TextField();
    public static TextField editarCorreo        = new TextField();
    public static PasswordField editarClave     = new PasswordField();
    public static PasswordField confirmarClave  = new PasswordField();
    //Noticias -----------------------------------------------------------------
    public static TableView<Constructor> tablaNoticias          = new TableView<>();
    public static ObservableList<Constructor> observarNoticias  = FXCollections.observableArrayList();
    //Historial ----------------------------------------------------------------
    public static TableView<Constructor> tableView                      = new TableView<>();
    public static ObservableList<Constructor> observableListStaticType  = FXCollections.observableArrayList();
    public static TextField filaSeleccionada                            = new TextField();
    
    
    public StackPane 
    sesion() {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        tabPane.setSide(Side.TOP);
        //SQL ------------------------------------------------------------------
            Consultar.perfil();
        //SQL ------------------------------------------------------------------
        Tab perfil = new Tab();
        perfil.setText("Perfil");
        perfil.setContent(perfil());
        
        Tab editar = new Tab();
        editar.setText("Editar");
        editar.setContent(editar());
        
        Tab noticias = new Tab();
        noticias.setText("Noticias");
        noticias.setContent(noticias());
        
        Tab historial = new Tab();
        historial.setText("Historial");
        historial.setContent(historial());
       
        tabPane.getTabs().addAll(
            perfil, 
            editar, 
            noticias,
            historial
        );
        center.getChildren().add(
            tabPane  
        );
        center.getStyleClass().add("center");
        
        return center;
    }
    public Pane 
    perfil() {
        GridPane grid = new GridPane();
        grid.setId("perfil");
        
        Image fotoURL = new Image(getClass().getResourceAsStream("/i/foto/"+GetAndSet.getNombreDeImagen()));
       
        ImageView foto = new ImageView();
        foto.setImage(fotoURL);
        foto.setFitWidth(150);
        foto.setFitHeight(150);
        Rectangle clip = new Rectangle(150, 150);
        clip.setArcWidth(150);
        clip.setArcHeight(150);
        foto.setClip(clip);
        GridPane.setConstraints(foto, 0, 0, 1, 6, HPos.LEFT, VPos.BASELINE);

        Text nombre = new Text();
        nombre.setText(GetAndSet.getNombre());
        nombre.getStyleClass().add("nombre");
        GridPane.setConstraints(nombre, 1, 0, 2, 1);

        Text apellido = new Text();
        apellido.setText(GetAndSet.getApellido());
        apellido.getStyleClass().add("nombre");
        GridPane.setConstraints(apellido, 1, 1, 2, 1);

        Text correo = new Text();
        correo.setText(GetAndSet.getCorreo());
        correo.getStyleClass().add("texto");
        GridPane.setConstraints(correo , 1, 2, 2, 1, HPos.LEFT, VPos.TOP);

        Button cerrarSesion = new Button("Cerrar sesion");
        GridPane.setConstraints(cerrarSesion, 1, 3, 1, 1, HPos.LEFT, VPos.BASELINE);
        cerrarSesion.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                Alert.AlertType innerAlerta = Alert.AlertType.CONFIRMATION;
           
                Alert alerta = new Alert(innerAlerta, "");
                alerta.initStyle(StageStyle.TRANSPARENT);
                alerta.initModality(Modality.APPLICATION_MODAL);

                final DialogPane dialogPane = alerta.getDialogPane();
                dialogPane.setHeaderText(null);
                dialogPane.setGraphic(null);
                dialogPane.getStylesheets().add("/i/css/root.css");
                dialogPane.getStylesheets().add("/i/css/sesion.css");
              
                Text text = new Text("¿Seguro que quiere cerrar la sesion?");
                text.getStyleClass().add("texto");

                dialogPane.setContent(text);
                
                dialogPane.lookupButton(ButtonType.OK).getStyleClass().add("verde");
                dialogPane.lookupButton(ButtonType.CANCEL).getStyleClass().add("rojo");

                Optional<ButtonType> optional = alerta.showAndWait();

                if(optional.get() == ButtonType.OK) {
                    Itermatico.borderPane.setCenter(GetAndSet.getCenter());
                    iniciarCorreo.setText("");
                    iniciarClave.setText("");

                    registrarseNombre.setText("");
                    registrarseApellido.setText("");
                    registrarseCorreo.setText("");
                    registrarseClave.setText("");
                    Insertar.cierreDeSesion();
                } 
            }
        });
        
        grid.getChildren().addAll(
            nombre, apellido, correo, foto, cerrarSesion
        );

        return grid;
    }
    public Pane 
    editar() {
        GridPane grid = new GridPane();
        grid.setId("editar");

        Image fotoURL = new Image(getClass().getResourceAsStream("/i/foto/"+GetAndSet.getNombreDeImagen()));
        
        editarFoto.setImage(fotoURL);
        editarFoto.setFitWidth(170);
        editarFoto.setFitHeight(170);
        Rectangle clip = new Rectangle(170, 170);
        clip.setArcWidth(25);
        clip.setArcHeight(25);
        editarFoto.setClip(clip);
        GridPane.setConstraints(editarFoto, 0, 0, 1, 7, HPos.CENTER, VPos.CENTER);
        
        seleccionar = new Button("Seleccionar");
        seleccionar.setMaxWidth(200);
        seleccionar.setDisable(true);
        GridPane.setConstraints(seleccionar, 0, 7, 1, 1, HPos.LEFT, VPos.CENTER);
        seleccionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                if(seleccionar.equals(event.getSource())) {
                    Foto.seleccionar();
                }
            }
        });

        Foto.guardar.setMaxWidth(200);
        Foto.guardar.setDisable(true);
        GridPane.setConstraints(Foto.guardar, 0, 8, 1, 1, HPos.LEFT, VPos.CENTER);
        
        Button actualizar = new Button("Actualizar");
        actualizar.setPrefWidth(200);
        GridPane.setConstraints(actualizar, 0, 9, 1, 1, HPos.LEFT, VPos.CENTER);
        actualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                Alert.AlertType innerAlerta = Alert.AlertType.CONFIRMATION;
                
                Alert alerta = new Alert(innerAlerta, "");
                alerta.initStyle(StageStyle.TRANSPARENT);
                alerta.initModality(Modality.APPLICATION_MODAL);
           
                final DialogPane dialogPane = alerta .getDialogPane();
                dialogPane.setHeaderText(null);
                dialogPane.setGraphic(null);
                dialogPane.getStylesheets().add("/i/css/root.css");
                dialogPane.getStylesheets().add("/i/css/sesion.css");


                Text text = new Text("¿Seguro que quiere actualiar tus datos?");
                text.getStyleClass().add("texto");

                dialogPane.setContent(text);
                
                dialogPane.lookupButton(ButtonType.OK).getStyleClass().add("verde");
                dialogPane.lookupButton(ButtonType.CANCEL).getStyleClass().add("rojo");

                Optional<ButtonType> optional = alerta.showAndWait();

                if(optional.get() == ButtonType.OK) {
                    Editar.perfil();
                }
            }
        });
        
        Label nombre = new Label("Nombre");
        GridPane.setConstraints(nombre, 1, 0, 1, 1, HPos.LEFT, VPos.CENTER);
        
        //TextField editarNombre = new TextField();
        editarNombre.setText(GetAndSet.getNombre());
        editarNombre.setPrefSize(200, 10);
        GridPane.setConstraints(editarNombre, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER);
        
        Label apellido = new Label("Apellidos");
        GridPane.setConstraints(apellido, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER);
        
        //TextField editarApellido = new TextField();
        editarApellido.setText(GetAndSet.getApellido());
        editarApellido.setPrefSize(200, 10);
        GridPane.setConstraints(editarApellido, 1, 3, 1, 1, HPos.LEFT, VPos.CENTER);
        
        Label correo = new Label("Correo");
        GridPane.setConstraints(correo, 1, 4, 1, 1, HPos.LEFT, VPos.CENTER);
        
        //TextField editarCorreo = new TextField();
        editarCorreo.setText(GetAndSet.getCorreo());
        editarCorreo.setPrefSize(200, 10);
        editarCorreo.setDisable(true);
        GridPane.setConstraints(editarCorreo, 1, 5, 1, 1, HPos.LEFT, VPos.CENTER);
        
        Label clave = new Label("Contraseña");
        GridPane.setConstraints(clave, 1, 6, 1, 1, HPos.LEFT, VPos.BASELINE); 
        
        //PasswordField editarClave = new PasswordField();
        editarClave.setText(GetAndSet.getPassword());
        editarClave.setPrefSize(200, 10);
        GridPane.setConstraints(editarClave, 1, 7, 1, 1, HPos.LEFT, VPos.CENTER);
        
        PasswordField confirmarClave = new PasswordField();
        confirmarClave.setPromptText("Confirmal Contraseña");
        confirmarClave.setPrefSize(200, 10);
        GridPane.setConstraints(confirmarClave, 1, 8, 1, 1, HPos.LEFT, VPos.CENTER);
        
        Button eliminar = new Button("Eliminar");
        eliminar.setPrefWidth(200);
        eliminar.getStyleClass().add("rojo");
        GridPane.setConstraints(eliminar, 1, 9, 1, 1, HPos.LEFT, VPos.BASELINE); 
        eliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                Alert.AlertType innerAlerta = Alert.AlertType.CONFIRMATION;
                
                Alert alerta = new Alert(innerAlerta, "");
                alerta.initStyle(StageStyle.TRANSPARENT);
                alerta.initModality(Modality.APPLICATION_MODAL);
           
                final DialogPane dialogPane = alerta .getDialogPane();
                dialogPane.setHeaderText(null);
                dialogPane.setGraphic(null);
                dialogPane.getStylesheets().add("/i/css/root.css");
                dialogPane.getStylesheets().add("/i/css/sesion.css");

                Text text = new Text("¿Seguro que quieres eliminar tu cuenta?");
                text.getStyleClass().add("texto");

                dialogPane.setContent(text);
                
                dialogPane.lookupButton(ButtonType.OK).getStyleClass().add("verde");
                dialogPane.lookupButton(ButtonType.CANCEL).getStyleClass().add("rojo");

                Optional<ButtonType> optional = alerta.showAndWait();

                if(optional.get() == ButtonType.OK) {
                    Eliminar.perfil();
                    
                    Itermatico.borderPane.setCenter(GetAndSet.getCenter());
                    iniciarCorreo.setText("");
                    iniciarClave.setText("");

                    registrarseNombre.setText("");
                    registrarseApellido.setText("");
                    registrarseCorreo.setText("");
                    registrarseClave.setText("");
                }
            }
        });
        
        grid.getChildren().addAll(
            editarFoto, seleccionar, Foto.guardar, actualizar, eliminar, 
            nombre, editarNombre, 
            apellido, editarApellido, 
            correo, editarCorreo, 
            clave, editarClave, confirmarClave
        );
        
        return grid;
    }
    public Pane 
    noticias() {
        GridPane grid = new GridPane();
        grid.setId("noticias");

        final TableColumn fila = new TableColumn("#");
        fila.setVisible(false);
        fila.setMinWidth(50);
        fila.setPrefWidth(100);
        fila.setMaxWidth(150);
        fila.setSortable(false);
        
        fila.setCellValueFactory(new PropertyValueFactory<>("fila"));
       
        final TableColumn noticias = new TableColumn("Noticias");
        noticias.setSortable(false);
        noticias.setCellValueFactory(new PropertyValueFactory<>("historial"));

        Text placeHolder = new Text("No hay noticias recientes.");
        tablaNoticias.setPlaceholder(placeHolder);
        //tablaNoticias.setTableMenuButtonVisible(true);
        tablaNoticias.getColumns().clear();
        tablaNoticias.getColumns().addAll(fila, noticias);
        tablaNoticias.setMinSize(500, 300);
        tablaNoticias.setMaxSize(900, 300);
        tablaNoticias.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        tablaNoticias.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        GridPane.setConstraints(tablaNoticias, 0, 0, 2, 9, HPos.RIGHT, VPos.TOP);
        
        tablaNoticias.setItems(observarNoticias);
        // SQL -----------------------------------------------------------------
            observarNoticias.clear();
            Consultar.noticias();
        // SQL -----------------------------------------------------------------  }
        grid.getChildren().add(
            tablaNoticias
        );

        return grid;
    }
    public Pane 
    historial() {
        GridPane grid = new GridPane();
        grid.setId("historial");

        final TableColumn fila = new TableColumn("#");
        fila.setVisible(false);
        fila.setMinWidth(50);
        fila.setPrefWidth(100);
        fila.setMaxWidth(150);
        fila.setSortable(false);
        
        fila.setCellValueFactory(new PropertyValueFactory<>("fila"));
       
        final TableColumn historial = new TableColumn("Historial");
        historial.setSortable(false);
        historial.setCellValueFactory(new PropertyValueFactory<>("historial"));

        //tableView.setTableMenuButtonVisible(true);
        tableView.getColumns().clear();
        tableView.getColumns().addAll(fila, historial);
        tableView.setPrefSize(500, 300);
        tableView.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        GridPane.setConstraints(tableView, 0, 0, 2, 9, HPos.RIGHT, VPos.TOP);
        
        Text placeHolder = new Text("No hay historial guardado.");
        tableView.setPlaceholder(placeHolder);
        tableView.setItems(observableListStaticType);
        tableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Constructor>() {
           public void onChanged(ListChangeListener.Change<? extends Constructor> listener) {
                filaSeleccionada.setText(
                    "#" + tableView.getSelectionModel().getSelectedItems().get(0).getFila()+ " Selecionado"
                );
            }
        });
        // SQL -----------------------------------------------------------------
            observableListStaticType.clear();
            Consultar.historial();
        // SQL -----------------------------------------------------------------  }
        //TextField filaSeleccionada = new TextField();
        filaSeleccionada.setPromptText("Solo para visualización");
        filaSeleccionada.setPrefWidth(200);
        filaSeleccionada.setDisable(true);
        GridPane.setConstraints(filaSeleccionada, 1, 9, 1, 1, HPos.CENTER, VPos.CENTER);
        
        Button eliminar = new Button("Eliminar");
        eliminar.getStyleClass().add("rojo");
        eliminar.setPrefWidth(200);
        GridPane.setConstraints(eliminar, 1, 10, 1, 1, HPos.CENTER, VPos.CENTER);
        eliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {              
                Alert.AlertType innerAlerta = Alert.AlertType.CONFIRMATION;
                
                Alert alerta = new Alert(innerAlerta, "");
                alerta.initStyle(StageStyle.TRANSPARENT);
                alerta.initModality(Modality.APPLICATION_MODAL);
           
                final DialogPane dialogPane = alerta .getDialogPane();
                dialogPane.setHeaderText(null);
                dialogPane.setGraphic(null);
                dialogPane.getStylesheets().add("/i/css/root.css");
                dialogPane.getStylesheets().add("/i/css/sesion.css");

                Text text = new Text("¿Seguro que quiere eliminar el historial?");
                text.getStyleClass().add("texto");

                dialogPane.setContent(text);
                
                dialogPane.lookupButton(ButtonType.OK).getStyleClass().add("verde");
                dialogPane.lookupButton(ButtonType.CANCEL).getStyleClass().add("rojo");

                Optional<ButtonType> optional = alerta.showAndWait();

                if(optional.get() == ButtonType.OK) {
                    Eliminar.fila();
                    tableView.getSelectionModel().clearSelection();
                }
            }
        });
        
        Button eliminarTodo = new Button("Eliminar todo");
        eliminarTodo.getStyleClass().add("rojo");
        eliminarTodo.setPrefWidth(400);
        eliminarTodo.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        GridPane.setConstraints(eliminarTodo, 0, 9, 1, 2, HPos.CENTER, VPos.BOTTOM);
        eliminarTodo.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                Alert.AlertType innerAlerta = Alert.AlertType.CONFIRMATION;
                
                Alert alerta = new Alert(innerAlerta, "");
                alerta.initStyle(StageStyle.TRANSPARENT);
                alerta.initModality(Modality.APPLICATION_MODAL);
           
                final DialogPane dialogPane = alerta .getDialogPane();
                dialogPane.setHeaderText(null);
                dialogPane.setGraphic(null);
                dialogPane.getStylesheets().add("/i/css/root.css");
                dialogPane.getStylesheets().add("/i/css/sesion.css");

                Text text = new Text("¿Seguro que quiere eliminar todo?");
                text.getStyleClass().add("texto");

                dialogPane.setContent(text);
                
                dialogPane.lookupButton(ButtonType.OK).getStyleClass().add("verde");
                dialogPane.lookupButton(ButtonType.CANCEL).getStyleClass().add("rojo");

                Optional<ButtonType> optional = alerta.showAndWait();

                if(optional.get() == ButtonType.OK) {
                    tableView.getColumns().clear();
                    Eliminar.historial();
                }
            }
        });
        
        grid.getChildren().addAll(
            tableView, filaSeleccionada, eliminar, eliminarTodo
        );

        return grid;
    }
}
