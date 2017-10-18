package ch.fhnw.cuie.assignment_1.template.demo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author Dieter Holz
 */
public class PresentationModel {

    // all the properties waiting for being displayed
    private final DoubleProperty value = new SimpleDoubleProperty();


    // all getters and setters (generated via "Code -> Generate -> Getter and Setter)
    public double getValue() {
        return value.get();
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    public void setValue(double value) {
        this.value.set(value);
    }

}
