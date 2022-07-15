package l.botones;

import front.Itermatico;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Personal extends Itermatico {
    //Botones ------------------------------------------------------------------
    private static Button demo = new Button("Personal");
    
    public StackPane personalEvento() {
        //Button demo = new Button("Personal");
        demo.setMaxSize(320, 40);
        GridPane.setConstraints(demo, 0, 0, 1, 1, HPos.LEFT, VPos.BASELINE);
        demo.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent t) {
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(10, 10, 10, 10));
                grid.setGridLinesVisible(true);
                grid.setStyle("-fx-background-color: white;");
                
                TextField textField = new TextField();
                textField.setPromptText("Write here 1");
                GridPane.setRowIndex(textField, 0);
                GridPane.setColumnIndex(textField, 1);
                
                Label label = new Label();
                label.setText("Se esta calculando");
                GridPane.setConstraints(label, 2, 0); // column=2 row=0
                
                Button button = new Button("Calcular");
                GridPane.setConstraints(button, 1, 1); // column=1 row=0

                Label labelResultado = new Label();
                labelResultado.setText("Resultado");
                GridPane.setConstraints(labelResultado, 2, 1);  // column=2 row=0

                Button atras = new Button("Atras");
                GridPane.setConstraints(atras, 1, 2);

                button.setOnAction(new EventHandler<ActionEvent> () {
                    @Override public void handle(ActionEvent event) {
                        int tempFahr = (int)((Double.parseDouble(textField.getText()))
                                * 1.8 + 32);
                        labelResultado.setText(tempFahr + " Resultadoooooooooooooooooooo");
                    }
                });
                atras.setOnAction(new EventHandler<ActionEvent> () {
                    @Override public void handle(ActionEvent event) {
                        grid.toBack();
                        grid.setVisible(false);
                    }
                });         

                grid.getChildren().addAll(
                    textField,
                    label,
                    button,
                    labelResultado,
                    atras
                );
                center.getChildren().add(
                    grid
                );
            }
        });

        temas.getChildren().clear();
        temas.getChildren().addAll(
            demo
        );
        center.getChildren().add(
            temas
        );
        center.getStyleClass().add("center");
        temas.getStyleClass().add("temas");
        
        return center;
    }
}
