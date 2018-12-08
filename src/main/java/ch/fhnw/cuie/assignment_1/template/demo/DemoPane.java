package ch.fhnw.cuie.assignment_1.template.demo;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import ch.fhnw.cuie.assignment_1.template.SimpleControl;

/**
 * @author Dieter Holz
 */
public class DemoPane extends BorderPane {

    private final DemoPM pm;

    // declare the custom control
    private SimpleControl cc;

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

        cc = new SimpleControl();

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

        setCenter(cc);
        setRight(controlPane);
    }

    private void setupBindings() {
        //bindings for the "demo controls"
        //slider.valueProperty().bindBidirectional(pm.someValueProperty());


        //bindings for the Custom Control
        cc.valueProperty().bindBidirectional(pm.someValueProperty());
    }

}
