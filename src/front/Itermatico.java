package front;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import static java.lang.System.out;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import l.botones.Estadistica;
import l.botones.Finanza;
import l.botones.General;
import l.botones.Personal;
import l.GetAndSet;
import s.consultas.IniciarSesion;
import s.inserciones.Insertar;
import s.inserciones.Registrarse;

/**
 * @author neury
 * Created on : Oct 7, 2019, 9:10:58 AM
 * Author     : neury
 */
public class Itermatico extends Application {
    public static BorderPane borderPane = new BorderPane();
    public StackPane center             = new StackPane();
    public static HBox top              = new HBox();
    public static VBox left             = new VBox();
    public static VBox right            = new VBox();
    public HBox bottom                  = new HBox();
    //Iniciar Sesion -----------------------------------------------------------
    private Button iniciarForm                  = new Button("Iniciar Sesion");
    private Pane iniciarPane                    = new Pane();
    private Text iniciarTitulo                  = new Text();
    private Text iniciarFrase                   = new Text();
    public static TextField iniciarCorreo       = new TextField();
    public static PasswordField iniciarClave    = new PasswordField();
    private Button iniciar                      = new Button();
    private Button iniciarCancelar              = new Button();
    public static Text iniciarError             = new Text();
    //Registrarse  -------------------------------------------------------------
    private Button registrarseForm                  = new Button("Registrarse");
    private Pane registrarsePaneObject              = new Pane();
    private GridPane registrarseGrid                = new GridPane();
    private Text registrarseTitulo                  = new Text();
    private Text registrarseFrase                   = new Text();
    public static TextField registrarseNombre       = new TextField();
    public static TextField registrarseApellido     = new TextField();
    public static TextField registrarseCorreo       = new TextField();
    public static PasswordField registrarseClave    = new PasswordField();
    private Button registrarse                      = new Button();
    private Button registrarseCancelar              = new Button();
    public static Text registrarseError             = new Text();
    //Sugerencias --------------------------------------------------------------
    private Button sugerenciaForm               = new Button();
    private Pane sugerenciaPane                 = new Pane();
    private GridPane sugerenciaGrid             = new GridPane();
    private Text sugerenciasTituloObject        = new Text();
    private Text sugerenciasFraseObject         = new Text();
    public static TextField sugerenciaNombre    = new TextField();
    public static TextField sugerenciaAsunto    = new TextField();
    public static TextField sugerenciaCorreo    = new TextField();
    public static TextArea sugerenciaMensaje    = new TextArea();
    private Button sugerencia                   = new Button();
    private Button sugerenciaCancelar           = new Button();
    public static Text sugerenciaError          = new Text();
    // ToggleBottom ------------------------------------------------------------
    private final ToggleGroup toggleGroup   = new ToggleGroup();
    private ToggleButton personal           = new ToggleButton("Personal");
    private ToggleButton finanza            = new ToggleButton("Finanza");
    public static ToggleButton general      = new ToggleButton("General");
    private ToggleButton estadistica        = new ToggleButton("Estadistica");
    //Globales------------------------------------------------------------------
    public static FlowPane temas = new FlowPane();
    
    public void 
    init(Stage stage){ 
        HBox topObj     = addHBoxTop();
        VBox leftObj    = addVBoxLeft();
        center          = addStackPane();
        VBox rightObj   = addVBoxRight();
        HBox bottomObj  = addHBoxBottom();
        
        topObj.toFront();
        leftObj.toFront();
        center.toFront();
        rightObj.toFront();
        bottomObj.toFront();
        
        borderPane.setTop(topObj);
        borderPane.setLeft(leftObj);
        borderPane.setCenter(center);
        borderPane.setRight(rightObj);
        borderPane.setBottom(bottomObj);
        
        borderPane.setPrefSize(1000, 500);
        Scene scene = new Scene(borderPane);

        stage.setScene(scene);

        scene.getStylesheets().add("/i/css/root.css");
        scene.getStylesheets().add("/i/css/border_pane.css");
        scene.getStylesheets().add("/i/css/top.css");
        scene.getStylesheets().add("/i/css/left.css");
        scene.getStylesheets().add("/i/css/center.css");
        scene.getStylesheets().add("/i/css/right.css");
        scene.getStylesheets().add("/i/css/bottom.css");
        scene.getStylesheets().add("/i/css/sesion.css");
        scene.getStylesheets().add("/i/css/tema/general.css");
        
        borderPane.getStyleClass().add("border_pane");
        borderPane.getTop().getStyleClass().add("top");
        borderPane.getLeft().getStyleClass().add("left");
        borderPane.getCenter().getStyleClass().add("center");
        borderPane.getRight().getStyleClass().add("right");
        borderPane.getBottom().getStyleClass().add("bottom");
        
        Animacion.borderPane(borderPane);
        GetAndSet.setStage(stage);
        GetAndSet.setScene(scene);
    }
    private HBox 
    addHBoxTop() {
        return top;
    } 
    private VBox
    addVBoxLeft() {
        return left;
    }
    public StackPane 
    addStackPane() {
        GridPane grid = new GridPane();
        grid.getStyleClass().add("grid");

        Label preguntas = new Label();
        preguntas.setText("¿No tienes tiempo para aprender sobre el tema?\n¿No se te dan bien las Matematicas y las "
            + "Finanzas?\n¿Quisas no te gustan pero quieres controlar tu ingresos, finanzas y mas?");
        GridPane.setConstraints(preguntas, 0, 0, 3, 1);
        //----------------------------------------------------------------------
        GetAndSet.setCenter(center);
        //----------------------------------------------------------------------
        Text respuesta = new Text();
        respuesta.setText("Hecha un vistaso y veras que todo esta preparado para ti.");
        respuesta.getStyleClass().add("h");
        respuesta.setWrappingWidth(600);
        GridPane.setConstraints(respuesta, 0, 1, 3, 1);

        iniciarPane.getStyleClass().add("pane");
        GridPane.setConstraints(iniciarForm, 0, 2);
        iniciarForm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                iniciarForm.setDisable(true);
                iniciarPane.setVisible(true);
                registrarseForm.setDisable(false);
                registrarsePaneObject.setVisible(false);
                sugerenciaForm.setDisable(false);
                sugerenciaPane.setVisible(false);

                left.setPrefWidth(250);
                right.setPrefWidth(0);
                Animacion.abrirLeft(left);
                Animacion.abrirLeft(center);
                iniciarError.setText("");
                registrarseError.setText("");
                sugerenciaError.setText("");

                GridPane iniciarSesionGrid = new GridPane();
                iniciarSesionGrid.setManaged(true);
                iniciarSesionGrid.getStyleClass().add("grid");

                iniciarTitulo.setText("Cuenta");
                iniciarTitulo.getStyleClass().add("h");
                GridPane.setConstraints(iniciarTitulo, 0, 0, 1, 1, HPos.CENTER, VPos.BASELINE);

                iniciarFrase.setText("¡Estamos muy entusiasmados por verte de nuevo!");
                iniciarFrase.getStyleClass().add("frase");
                iniciarFrase.setWrappingWidth(250);
                GridPane.setConstraints(iniciarFrase, 0, 1, 1, 1, HPos.CENTER, VPos.BASELINE);

                iniciarCorreo.setPromptText("Introducir Correo");
                iniciarCorreo.setPrefWidth(200);
                GridPane.setConstraints(iniciarCorreo, 0, 2, 1, 1, HPos.CENTER, VPos.BASELINE);

                iniciarClave.setPromptText("Introducir Contraseña");
                iniciarClave.setPrefWidth(200);
                GridPane.setConstraints(iniciarClave, 0, 3, 1, 1, HPos.CENTER, VPos.BASELINE);

                iniciar.setText("Iniciar Sesion");
                iniciar.setPrefWidth(250);
                iniciar.getStyleClass().add("verde");
                GridPane.setConstraints(iniciar, 0, 4, 1, 1);
                iniciar.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent eventObject) {
                        IniciarSesion.iniciarSesion();
                    }    
                });
                
                iniciarCancelar.setText("Cancelar");
                iniciarCancelar.setPrefWidth(250);
                iniciarCancelar.getStyleClass().add("rojo");
                GridPane.setConstraints(iniciarCancelar, 0, 5, 1, 1);
                iniciarCancelar.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent eventObject) {
                        iniciarForm.setDisable(false);
                        iniciarPane.setVisible(false);

                        left.setPrefWidth(0);
                        iniciarError.setText("");
                        Animacion.cancelarLeft(center);
                    }
                });
                
                iniciarError.setId("error");
                GridPane.setConstraints(iniciarError, 0, 6, 1, 1, HPos.LEFT, VPos.BASELINE);
                
                iniciarSesionGrid.getChildren().addAll(
                    iniciarTitulo, 
                    iniciarFrase, 
                    iniciarCorreo, 
                    iniciarClave, 
                    iniciar,
                    iniciarCancelar, 
                    iniciarError
                );
                iniciarPane.getChildren().clear();
                iniciarPane.getChildren().add(
                    iniciarSesionGrid 
                );
                left.getChildren().clear();
                left.getChildren().add(
                    iniciarPane
                );
            }
        });

        registrarsePaneObject.getStyleClass().add("pane");
        GridPane.setConstraints(registrarseForm, 1, 2);
        registrarseForm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                registrarseForm.setDisable(true);
                registrarsePaneObject.setVisible(true);
                iniciarForm.setDisable(false);
                iniciarPane.setVisible(false);
                sugerenciaForm.setDisable(false);
                sugerenciaPane.setVisible(false);
                sugerenciaGrid.setManaged(false);
                
                left.setPrefWidth(0);
                right.setPrefWidth(250);
                Animacion.abrirRight(right);
                Animacion.abrirRight(center);
                iniciarError.setText("");
                registrarseError.setText("");
                sugerenciaError.setText("");

                registrarseGrid.setManaged(true);
                registrarseGrid.getStyleClass().add("grid");

                registrarseTitulo.setText("Registrarse");
                registrarseTitulo.getStyleClass().add("h");
                GridPane.setConstraints(registrarseTitulo, 0, 0, 1, 1, HPos.CENTER, VPos.BASELINE);

                registrarseFrase.setText("¡Estamos muy entusiasmados de que te unas!");
                registrarseFrase.getStyleClass().add("frase");
                registrarseFrase.setWrappingWidth(250);
                GridPane.setConstraints(registrarseFrase, 0, 1, 1, 1, HPos.CENTER, VPos.BASELINE);

                registrarseNombre.setPromptText("Introducir nombre");
                registrarseNombre.setPrefWidth(200);
                GridPane.setConstraints(registrarseNombre, 0, 2, 1, 1, HPos.CENTER, VPos.BASELINE);

                registrarseApellido.setPromptText("Introducir Apellidos");
                registrarseApellido.setPrefWidth(200);
                GridPane.setConstraints(registrarseApellido, 0, 3, 1, 1, HPos.CENTER, VPos.BASELINE);

                registrarseCorreo.setPromptText("Introducir Correo");
                registrarseCorreo.setPrefWidth(200);
                GridPane.setConstraints(registrarseCorreo, 0, 4, 1, 1, HPos.CENTER, VPos.BASELINE);

                registrarseClave.setPromptText("Introducir Contraseña");
                registrarseClave.setPrefWidth(200);
                GridPane.setConstraints(registrarseClave, 0, 5, 1, 1, HPos.CENTER, VPos.BASELINE);

                registrarse.setText("Registrarse");
                registrarse.setPrefWidth(250);
                registrarse.getStyleClass().add("verde");
                GridPane.setConstraints(registrarse, 0, 6, 1, 1, HPos.CENTER, VPos.BASELINE);
                registrarse.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent eventObject) {
                        Registrarse.registrarse();
                    }
                });

                registrarseCancelar.setText("Cancelar");
                registrarseCancelar.setPrefWidth(250);
                registrarseCancelar.getStyleClass().add("rojo");
                GridPane.setConstraints(registrarseCancelar, 0, 7, 1, 1, HPos.CENTER, VPos.BASELINE);
                registrarseCancelar.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent eventObject) {
                        registrarseForm.setDisable(false);
                        registrarsePaneObject.setVisible(false);
                        registrarseGrid.setManaged(false);
                        
                        right.setPrefWidth(0);
                        registrarseError.setText("");
                        Animacion.cancelarRight(center);
                    }
                });
                registrarseError.setId("error");
                GridPane.setConstraints(registrarseError, 0, 8, 1, 1, HPos.CENTER, VPos.BASELINE);
                
                registrarseGrid.getChildren().clear();
                registrarseGrid.getChildren().addAll(
                    registrarseTitulo, 
                    registrarseFrase, 
                    registrarseNombre, 
                    registrarseApellido, 
                    registrarseCorreo,
                    registrarseClave,
                    registrarse, 
                    registrarseCancelar,
                    registrarseError
                );
                registrarsePaneObject.getChildren().clear();
                registrarsePaneObject.getChildren().add(
                    registrarseGrid 
                );
                right.getChildren().clear();
                right.getChildren().add(
                    registrarsePaneObject
                );
            }
        });

        sugerenciaForm.setText("Sugerencias");
        sugerenciaPane.getStyleClass().add("pane");
        GridPane.setConstraints(sugerenciaForm, 2, 2);
        sugerenciaForm.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent eventObject) {
                sugerenciaForm.setDisable(true);
                sugerenciaPane.setVisible(true);
                iniciarForm.setDisable(false);
                iniciarPane.setVisible(false);
                registrarseForm.setDisable(false);
                registrarsePaneObject.setVisible(false);
                registrarseGrid.setManaged(false);

                left.setPrefWidth(0);
                right.setPrefWidth(250);
                Animacion.abrirRight(right);
                Animacion.abrirRight(center);
                iniciarError.setText("");
                registrarseError.setText("");
                sugerenciaError.setText("");

                sugerenciaGrid.setManaged(true);
                sugerenciaGrid.getStyleClass().add("grid");

                sugerenciasTituloObject.setText("¡Suregencia!");
                sugerenciasTituloObject.getStyleClass().add("h");
                GridPane.setConstraints(sugerenciasTituloObject, 0, 0, 1, 1, HPos.CENTER, VPos.BASELINE);

                sugerenciasFraseObject.setText("¡Dejanos tu sugerencia para seguir aportando valor!");
                sugerenciasFraseObject.getStyleClass().add("frase");
                sugerenciasFraseObject.setWrappingWidth(250);
                GridPane.setConstraints(sugerenciasFraseObject, 0, 1, 1, 1, HPos.CENTER, VPos.BASELINE);

                sugerenciaNombre.setPromptText("Nombre...");
                sugerenciaNombre.setPrefWidth(200);
                GridPane.setConstraints(sugerenciaNombre, 0, 2, 1, 1, HPos.CENTER, VPos.BASELINE);

                sugerenciaAsunto.setPromptText("Asunto..");
                sugerenciaAsunto.setPrefWidth(200);
                GridPane.setConstraints(sugerenciaAsunto, 0, 3, 1, 1, HPos.CENTER, VPos.BASELINE);

                sugerenciaCorreo.setPromptText("Correo...");
                sugerenciaCorreo.setPrefWidth(200);
                GridPane.setConstraints(sugerenciaCorreo, 0, 4, 1, 1, HPos.CENTER, VPos.BASELINE);

                sugerenciaMensaje.setPromptText("Mensaje...");
                GridPane.setConstraints(sugerenciaMensaje, 0, 5, 1, 1, HPos.CENTER, VPos.BASELINE);

                sugerencia.setText("Enviar");
                sugerencia.setPrefWidth(250);
                sugerencia.getStyleClass().add("verde");
                GridPane.setConstraints(sugerencia, 0, 6, 1, 1, HPos.CENTER, VPos.BASELINE);
                sugerencia.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent eventObject) {
                        Insertar.sugerencias();
                        
                        sugerenciaNombre.setText("");
                        sugerenciaAsunto.setText("");
                        sugerenciaCorreo.setText("");
                        sugerenciaMensaje.setText("");
                    }
                });

                sugerenciaCancelar.setText("Cancelar");
                sugerenciaCancelar.setPrefWidth(250);
                sugerenciaCancelar.getStyleClass().add("rojo");
                GridPane.setConstraints(sugerenciaCancelar, 0, 7, 1, 1, HPos.CENTER, VPos.BASELINE);
                sugerenciaCancelar.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent eventObject) {
                        sugerenciaForm.setDisable(false);
                        sugerenciaPane.setVisible(false);
                        sugerenciaGrid.setManaged(false);
                        
                        right.setPrefWidth(0);
                        sugerenciaError.setText("");
                        Animacion.cancelarRight(center);
                    }
                });
                sugerenciaError.setId("error");
                GridPane.setConstraints(sugerenciaError, 0, 8, 1, 1, HPos.CENTER, VPos.BASELINE);
                sugerenciaGrid.getChildren().clear();
                sugerenciaGrid.getChildren().addAll(
                    sugerenciasTituloObject, 
                    sugerenciasFraseObject, 
                    sugerenciaNombre, 
                    sugerenciaAsunto, 
                    sugerenciaCorreo, 
                    sugerenciaMensaje,
                    sugerencia,
                    sugerenciaCancelar,
                    sugerenciaError
                );
                sugerenciaPane.getChildren().clear();
                sugerenciaPane.getChildren().add(
                    sugerenciaGrid 
                );
                right.getChildren().clear();
                right.getChildren().add(
                    sugerenciaPane
                );
            }
        });
       
        grid.getChildren().addAll(
            preguntas, 
            respuesta,
            iniciarForm, 
            registrarseForm, 
            sugerenciaForm
        );
        center.getChildren().add(
            grid
        );
        
        return center;
    }
    private VBox
    addVBoxRight() {
        return right;
    }
    private HBox 
    addHBoxBottom() {
        //ToggleButton personal = new ToggleButton("Personal");
        Text p = GlyphsDude.createIcon(FontAwesomeIcons.USER, "2em");
        personal.setToggleGroup(toggleGroup);
        personal.setGraphic(p);
        personal.setContentDisplay(ContentDisplay.TOP);
        personal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                if(!personal.isSelected()) {
                    personal.setContentDisplay(ContentDisplay.TOP);
                    personal.setText("Personal");
                    borderPane.setCenter(center);
                } else {
                    personal.setText("Salir");
                    finanza.setText("Finanza");
                    general.setText("General");
                    estadistica.setText("Estadistica");
                    personal.setContentDisplay(ContentDisplay.TEXT_ONLY);
                    finanza.setContentDisplay(ContentDisplay.TOP);
                    general.setContentDisplay(ContentDisplay.TOP);
                    estadistica.setContentDisplay(ContentDisplay.TOP);
                    Personal temas = new Personal(); borderPane.setCenter(temas.personalEvento());
                }
            }
        });
        personal.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                personal.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        });
        personal.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                if(personal.isSelected()) { personal.setContentDisplay(ContentDisplay.TEXT_ONLY); } 
                else { personal.setContentDisplay(ContentDisplay.TOP); }
            }
        }); 
        //ToggleButton finanza = new ToggleButton("Finanza");
        Text f = GlyphsDude.createIcon(FontAwesomeIcons.EXCHANGE, "2em");
        finanza.setToggleGroup(toggleGroup);
        finanza.setGraphic(f);
        finanza.setContentDisplay(ContentDisplay.TOP);
        finanza.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                if(!finanza.isSelected()) {
                    finanza.setContentDisplay(ContentDisplay.TOP);
                    finanza.setText("Finanza");
                    borderPane.setCenter(center);
                } else {
                    personal.setText("Personal");
                    finanza.setText("Salir");
                    general.setText("General");
                    estadistica.setText("Estadistica");
                    personal.setContentDisplay(ContentDisplay.TOP);
                    finanza.setContentDisplay(ContentDisplay.TEXT_ONLY);
                    general.setContentDisplay(ContentDisplay.TOP);
                    estadistica.setContentDisplay(ContentDisplay.TOP);
                    Finanza temas = new Finanza(); borderPane.setCenter(temas.finanzaEvento());  
                }
            }
        });
        finanza.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                finanza.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        });
        finanza.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                if(finanza.isSelected()) { finanza.setContentDisplay(ContentDisplay.TEXT_ONLY); } 
                else { finanza.setContentDisplay(ContentDisplay.TOP); }
            }
        }); 
        //ToggleButton general = new ToggleButton("General");
        Text g = GlyphsDude.createIcon(FontAwesomeIcons.GLOBE, "2em");
        general.setToggleGroup(toggleGroup);
        general.setGraphic(g);
        general.setContentDisplay(ContentDisplay.TOP);
        general.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                if(!general.isSelected()) {
                    general.setContentDisplay(ContentDisplay.TOP);
                    general.setText("General");
                    borderPane.setCenter(center);
                } else {
                    personal.setText("Personal");
                    finanza.setText("Finanza");
                    general.setText("Salir");
                    estadistica.setText("Estadistica");
                    personal.setContentDisplay(ContentDisplay.TOP);
                    finanza.setContentDisplay(ContentDisplay.TOP);
                    general.setContentDisplay(ContentDisplay.TEXT_ONLY);
                    estadistica.setContentDisplay(ContentDisplay.TOP);
                    General temas = new General();borderPane.setCenter(temas.generalEvento()); 
                }
            }
        });
        general.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                general.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        });
        general.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                if(general.isSelected()) { general.setContentDisplay(ContentDisplay.TEXT_ONLY); } 
                else { general.setContentDisplay(ContentDisplay.TOP); }
            }
        }); 
        //ToggleButton estadistica = new ToggleButton("Estadistica");
        Text down = GlyphsDude.createIcon(FontAwesomeIcons.PIE_CHART, "2em");
        estadistica.setToggleGroup(toggleGroup);
        estadistica.setGraphic(down);
        estadistica.setContentDisplay(ContentDisplay.TOP);
        estadistica.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                if(!estadistica.isSelected()) {
                    estadistica.setContentDisplay(ContentDisplay.TOP);
                    estadistica.setText("Estadistica");
                    borderPane.setCenter(center);
                } else {
                    personal.setText("Personal");
                    finanza.setText("Finanza");
                    general.setText("General");
                    estadistica.setText("Salir");
                    personal.setContentDisplay(ContentDisplay.TOP);
                    finanza.setContentDisplay(ContentDisplay.TOP);
                    general.setContentDisplay(ContentDisplay.TOP);
                    estadistica.setContentDisplay(ContentDisplay.TEXT_ONLY);
                    Estadistica temas = new Estadistica(); borderPane.setCenter(temas.estadisticaEvento());   
                }
            }
        });
        estadistica.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                estadistica.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        });
        estadistica.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent eventObject) {
                if(estadistica.isSelected()) { estadistica.setContentDisplay(ContentDisplay.TEXT_ONLY); } 
                else { estadistica.setContentDisplay(ContentDisplay.TOP); }
            }
        });
        
        
        bottom.getChildren().addAll(
            personal, finanza, general, estadistica
        );

        return bottom;
    }
    @Override public void 
    start(Stage stage) throws Exception {
        init(stage);
        
        stage.setTitle("Itermatico");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/i/img/dukeWaveRed.gif")));
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
    }
}
