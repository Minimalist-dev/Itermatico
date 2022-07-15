package l;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Constructor {
    SimpleIntegerProperty fila;
    SimpleStringProperty historial;

    public 
    Constructor(Integer fila, String historial) {
        this.fila = new SimpleIntegerProperty(fila);
        this.historial = new SimpleStringProperty(historial);
    }

    public Integer getFila() {
        return fila.get();
    }

    public void setFila(Integer fila) {
        this.fila.set(fila);
    }

    public String getHistorial() {
        return historial.get();
    }

    public void setHistorial(String historial) {
        this.historial.set(historial);
    }
}
