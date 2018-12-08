package ch.fhnw.cuie.assignment_1.template.demo;

import javafx.beans.property.*;

/**
 * @author Dieter Holz
 */
public class DemoPM {
    // all the properties waiting for being displayed
    private final StringProperty demoTitle = new SimpleStringProperty("Mountain Control Demo");
    private final StringProperty maxPoint = new SimpleStringProperty();
    private final StringProperty minPoint = new SimpleStringProperty();

    // getters and setters
    public StringProperty demoTitleProperty() {
        return demoTitle;
    }
    public StringProperty maxPointProperty() { return maxPoint; }
    public StringProperty minPointProperty() { return minPoint; }

    public void setDemoTitle(String demoTitle) {
        this.demoTitle.set(demoTitle);
    }

    public void setMaxPoint(String maxPoint) {
        this.maxPoint.set(maxPoint);
    }

    public void setMinPoint(String minPoint) {
        this.minPoint.set(minPoint);
    }
}
