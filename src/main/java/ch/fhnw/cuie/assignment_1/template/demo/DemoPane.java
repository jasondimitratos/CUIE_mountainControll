package ch.fhnw.cuie.assignment_1.template.demo;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import ch.fhnw.cuie.assignment_1.template.MountainControl;

import java.awt.desktop.SystemSleepEvent;

/**
 * @author Dieter Holz
 */
public class DemoPane extends BorderPane {

    private final DemoPM pm;

    // declare the custom control
    private MountainControl mc;

    // all controls you need to show the features of the custom control
    private TextField liftField;
    private TextField talstationField;

    public DemoPane(DemoPM pm) {
        this.pm = pm;
        initializeControls();
        layoutControls();
        setupBindings();
    }

    private void initializeControls() {
        setPadding(new Insets(10));

        mc = new MountainControl();

        liftField = new TextField();
        talstationField = new TextField();
    }

    private void layoutControls() {
        setCenter(mc);
        VBox controlPane = new VBox(
                new Label("Bergstation (m.ü.M."), liftField,
                new Label("Talstation (m.ü.M."), talstationField
        );
        controlPane.setPadding(new Insets(10));
        controlPane.setSpacing(10);
        setRight(controlPane);
    }

    private void setupBindings() {
        liftField.textProperty().bindBidirectional(pm.liftPointProperty());
        talstationField.textProperty().bindBidirectional(pm.talstationPointProperty());

        pm.liftPointProperty().bindBidirectional(mc.liftHeightPointProperty());
        pm.talstationPointProperty().bindBidirectional(mc.talstationPointProperty());
    }
}
