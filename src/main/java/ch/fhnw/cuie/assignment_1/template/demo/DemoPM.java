package ch.fhnw.cuie.assignment_1.template.demo;

import javafx.beans.property.*;

/**
 * @author Dieter Holz
 */
public class DemoPM {
    // all the properties waiting for being displayed
    private final StringProperty demoTitle = new SimpleStringProperty("Mountain Control Demo");
    private final StringProperty liftPoint = new SimpleStringProperty();
    private final StringProperty talstationPoint = new SimpleStringProperty();

    // getters and setters
    public StringProperty demoTitleProperty() {
        return demoTitle;
    }
    public StringProperty liftPointProperty() { return liftPoint; }
    public StringProperty talstationPointProperty() { return talstationPoint; }

}
