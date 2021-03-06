package l.botones;

import l.Expresion;
import front.Itermatico;
import static java.lang.System.out;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import l.G;
import s.inserciones.Statistics;

public class General extends Itermatico {
    // Botones -----------------------------------------------------------------
    public static Button celsiusFahrenheit = new Button("Celsius a Fahrenheit");
    //Contenido de Paneles Internos --------------------------------------------
    private static Text uso     = new Text("");
    private static Button atras = new Button("X");
    //PieChart --------------------------------------------------------------------------------------------
    private static final PieChart pieChart          = new PieChart(FXCollections.observableArrayList());
    private static final PieChart.Data congelacion  = new PieChart.Data(null, 0);
    private static final PieChart.Data ebullicion   = new PieChart.Data(null, 0);
    
    public StackPane 
    generalEvento() { 
//        temas.setPrefWrapLength(170);
        atras.getStyleClass().add("cerrar_tema");
        uso.getStyleClass().add("uso");
        
//        Button celsiusFahrenheit = new Button("Celsius a Fahrenheit");
        celsiusFahrenheit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent eventObject) {
                GridPane grid = new GridPane();
                grid.getStyleClass().add("tema");

                atras.setOnAction(new EventHandler<ActionEvent> () {
                    @Override public void handle(ActionEvent eventObject) {
                        grid.toBack();
                        grid.setVisible(false);
                    } 
                });
                GridPane.setConstraints(atras, 0, 1);
                
                Label label = new Label();
                label.setText("Celsius a Fahrenheit");
                GridPane.setConstraints(label, 0, 2, 2, 1, HPos.LEFT, VPos.CENTER);
                
                TextField textField = new TextField();
                textField.setPromptText("Ingresar los Celsius");
                GridPane.setConstraints(textField, 0, 3, 1, 1, HPos.LEFT, VPos.CENTER);
                
                Button boton = new Button("Convertir");
                GridPane.setConstraints(boton, 1, 3, 1, 1, HPos.LEFT, VPos.CENTER);
                boton.setOnAction(new EventHandler<ActionEvent> () {
                    @Override public void handle(ActionEvent eventObject) {
                        pieChart.getData().clear();
                        
                        double fahrenheit;
                        
                        String s = textField.getText();

                        if(Expresion.numeros(s)) {
                            double decimal  = Double.parseDouble(s);
                            int entero      = (int) decimal;
                            
                            if(entero >= 1 && entero <= 100 ) {
                                Statistics.celsius(entero);
                            }
                            
                            uso.setText("Uso\n"
                                + "Se utiliza por la poblaci??n para usos no cient??ficos \ny en determinadas industrias muy "
                                + "r??gidas, como la del \npetr??leo. Adem??s, se utiliza esta escala en los \ninformes "
                                + "meteorol??gicos y en gastronom??a. "
                            );
                            
                            fahrenheit = (double)((Double.parseDouble(s)) * 1.8 + 32);

                            if(fahrenheit > 213) {
                                PieChart.Data ebullicion = new PieChart.Data("Has superado el limite de 212 de ebullicion ??F agua totalmente", 212);
                                pieChart.getData().add(ebullicion);
                            } else if(fahrenheit == 32) {
                                double ebullicion = 212 - fahrenheit;
                                PieChart.Data data = new PieChart.Data(
                                    "Estas en congelacion ??F " + G.decimales(fahrenheit, 2), 
                                    fahrenheit
                                );
                                pieChart.getData().add(data);
                                PieChart.Data ebullicionObject = new PieChart.Data(
                                    "A " + G.decimales(ebullicion, 2) + " de ebullicion ??F", 
                                    ebullicion
                                );
                                pieChart.getData().add(ebullicionObject);
                            } else if(fahrenheit >= 33 && fahrenheit <= 212){
                                double ebullicion = 212 - fahrenheit;
                                PieChart.Data data = new PieChart.Data(
                                    "A " + G.decimales(fahrenheit, 2) + " de congelacion ??F ", 
                                    fahrenheit
                                );
                                pieChart.getData().add(data);
                                PieChart.Data ebullicionObject = new PieChart.Data(
                                    "A " + G.decimales(ebullicion, 2) + " de ebullicion ??F", 
                                    ebullicion
                                );
                                pieChart.getData().add(ebullicionObject);
                            }
                        } else { uso.setText("Solo se permiten numeros enteros del 1 al 100");}
                    } 
                });

                uso.setText("Uso\n"
                    + "Se utiliza por la poblaci??n para usos no cient??ficos \ny en determinadas industrias muy "
                    + "r??gidas, como la del \npetr??leo. Adem??s, se utiliza esta escala en los \ninformes "
                    + "meteorol??gicos y en gastronom??a. "
                );
                GridPane.setConstraints(uso, 0, 4, 2, 1, HPos.LEFT, VPos.CENTER);

                pieChart.getData().clear();
                pieChart.setMaxSize(450, 350);
                pieChart.setTitle("Celsius a Fahrenheit");
                pieChart.setTitleSide(Side.TOP);
                pieChart.setLegendSide(Side.BOTTOM);
                congelacion.setName("Limite del Estado de congelacion ??F 32 (congelado)");
                congelacion.setPieValue(32);
                pieChart.getData().add(congelacion);
                ebullicion.setName("Limite del Estado de ebullicion ??F 212 (descongelado)");
                ebullicion.setPieValue(180);
                pieChart.getData().add(ebullicion);
                GridPane.setConstraints(pieChart, 2, 0, 1, 5, HPos.CENTER, VPos.CENTER);

                grid.getChildren().clear();
                grid.getChildren().addAll(
                    atras,
                    textField, 
                    label, 
                    boton, 
                    uso, 
                    pieChart
                );
                center.getChildren().add(
                    grid
                );
            }
        });
        
        temas.getChildren().clear();
        temas.getChildren().addAll(
            celsiusFahrenheit
        );
        center.getChildren().add(
            temas
        );
        center.getStyleClass().add("center");
        temas.getStyleClass().add("temas");
        
        return center;
    }
}
