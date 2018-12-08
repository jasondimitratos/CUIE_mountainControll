package ch.fhnw.cuie.assignment_1.template.demo;

import javafx.beans.property.*;

/**
 * @author Dieter Holz
 */
public class DemoPM {
    // all the properties waiting for being displayed
    private final StringProperty demoTitle = new SimpleStringProperty("Custom Control Demo");
    private final DoubleProperty someValue = new SimpleDoubleProperty();


    private final IntegerProperty maxPoint = new SimpleIntegerProperty();
    private final IntegerProperty minPoint = new SimpleIntegerProperty();



    // all getters and setters (generated via "Code -> Generate -> Getter and Setter)

    public String getDemoTitle() {
        return demoTitle.get();
    }

    public StringProperty demoTitleProperty() {
        return demoTitle;
    }

    public void setDemoTitle(String demoTitle) {
        this.demoTitle.set(demoTitle);
    }

    public double getSomeValue() {
        return someValue.get();
    }

    public DoubleProperty someValueProperty() {
        return someValue;
    }

    public void setSomeValue(double someValue) {
        this.someValue.set(someValue);
    }

    public int getMaxPoint() {
        return maxPoint.get();
    }

    public IntegerProperty maxPointProperty() {
        return maxPoint;
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint.set(maxPoint);
    }

    public int getMinPoint() {
        return minPoint.get();
    }

    public IntegerProperty minPointProperty() {
        return minPoint;
    }

    public void setMinPoint(int minPoint) {
        this.minPoint.set(minPoint);
    }
}
