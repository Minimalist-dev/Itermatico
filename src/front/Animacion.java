package front;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animacion {
    public static FadeTransition 
    borderPane(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
        return fadeTransition;
    }
    public static ParallelTransition 
    escalar(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), node);
        scaleTransition.setFromX(0f);
        scaleTransition.setFromY(0f);
        scaleTransition.setToX(1f);
        scaleTransition.setToY(1f);
        scaleTransition.setCycleCount(1);
 
        ParallelTransition parallelTransitionObject = new ParallelTransition(
           node, fadeTransition, scaleTransition
        );
        
        parallelTransitionObject.play();
        
        return parallelTransitionObject;
    }
    public static TranslateTransition
    abrirLeft(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), node);
        translateTransition.setFromX(-300f);
        translateTransition.setByX(300f);
        translateTransition.setCycleCount(1);
        translateTransition.play();
        return translateTransition;
    }
    public static TranslateTransition 
    cancelarLeft(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), node);
        translateTransition.setFromX(300f);
        translateTransition.setByX(-300f);
        translateTransition.setCycleCount(1);
        translateTransition.play();
        return translateTransition;
    }
    public static TranslateTransition
    abrirRight(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), node);
        translateTransition.setFromX(300f);
        translateTransition.setByX(-300f);
        translateTransition.setCycleCount(1);
        translateTransition.play();
        return translateTransition;
    }
    public static TranslateTransition 
    cancelarRight(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), node);
        translateTransition.setFromX(-300f);
        translateTransition.setByX(300f);
        translateTransition.setCycleCount(1);
        translateTransition.play();
        return translateTransition;
    }  
}
