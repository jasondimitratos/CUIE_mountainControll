package ch.fhnw.cuie.assignment_1.template.demo;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import ch.fhnw.cuie.assignment_1.template.MountainControl;

/**
 * @author Dieter Holz
 */
public class DemoPane extends BorderPane {

    private final DemoPM pm;

    // declare the custom control
    private MountainControl mc;

    // all controls you need to show the features of the custom control
    private TextField maxField;
    private TextField minField;

    public DemoPane(DemoPM pm) {
        this.pm = pm;
        initializeControls();
        layoutControls();
        setupBindings();
    }

    private void initializeControls() {
        setPadding(new Insets(10));

        mc = new MountainControl();

        maxField = new TextField();
        minField = new TextField();
    }

    private void layoutControls() {
        VBox controlPane = new VBox(
                new Label("Talstation (m.ü.M."),
                maxField,
                new Label("Bergstation (m.ü.M."),
                minField
        );
        controlPane.setPadding(new Insets(0, 50, 0, 50));
        controlPane.setSpacing(10);

        setCenter(mc);
        setRight(controlPane);
    }

    private void setupBindings() {
        //bindings for the "demo controls"
        //maxField.textProperty().bindBidirectional(pm.maxPoint());
        //minField.textProperty().bindBidirectional(pm.minPoint());


        //bindings for the Custom Control
        mc.valueProperty().bindBidirectional(pm.someValueProperty());
    }

}
