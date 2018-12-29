package ch.fhnw.cuie.assignment_1.template;

import javafx.event.EventHandler;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;

/**
 * Mountain Control stellt die Höhe der Tal- und Bergstation visuell dar.
 * Die Höhen lassen sich durch Mouse-Dragging verändern.
 *
 * @author Jessica Odermatt
 * @author Jason Dimitratos
 * @author Dieter Holz
 */
public class MountainControl extends Region {
    private static final double ARTBOARD_WIDTH = 210;
    private static final double ARTBOARD_HEIGHT = 190;
    private static final double ASPECT_RATIO = ARTBOARD_WIDTH / ARTBOARD_HEIGHT;
    private static final double MINIMUM_WIDTH = 210;
    private static final double MINIMUM_HEIGHT = MINIMUM_WIDTH / ASPECT_RATIO;
    private static final double MAXIMUM_WIDTH = 800;


    private int liftHeight = 30;
    private int talstationHeight = 80;
    private StringProperty liftHeightPoint = new SimpleStringProperty("3000 müM");
    private StringProperty talstationPoint = new SimpleStringProperty();

    private SVGPath mountainBackgroundSnow;
    private SVGPath mountainBackgroundBottom;

    private Line highestAltitude;
    private Circle highestAltitudeGraber;
    private Line lowestAltitude;
    private Circle lowestGraber;
    private Label highestLabel;
    private Label lowestLabel;

    private VBox backgroundMountain;
    private HBox highestAltBox;
    private HBox lowestAltBox;

    // needed for resizing
    private Pane drawingPane;

    public MountainControl() {
        initializeSelf();
        initializeParts();
        initializeDrawingPane();
        layoutParts();
        setupEventHandlers();
        setupValueChangeListeners();
        setupBinding();
    }

    private void initializeSelf() {
        String fonts = getClass().getResource("/fonts/fonts.css").toExternalForm();
        getStylesheets().add(fonts);

        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);

        getStyleClass().add("mountain-control");
    }

    private void initializeParts() {
        double center = ARTBOARD_WIDTH * 0.5;

        initializeBackgroundMountain();
        initializeAltBox();
    }

    private void initializeBackgroundMountain() {
        backgroundMountain = new VBox();
        mountainBackgroundSnow = new SVGPath();
        mountainBackgroundSnow.setContent("M123,76.2851562 C127.119792,87.0403646 133.303385,92.4179688 141.550781,92.4179688 C153.921875,92.4179688 149.368434,86.0488281 159.430934,76.2851562 C164.070716,71.7831625 169.205633,80.0752159 178.855469,81.8007812 C181.723588,82.313653 187.193641,78.6873509 195.265625,70.921875 L159.430934,6 L123,76.2851562 Z");
        mountainBackgroundSnow.getStyleClass().add("background-snow");
        mountainBackgroundBottom = new SVGPath();
        mountainBackgroundBottom.setContent("M123,76.2851562 C127.119792,87.0403646 133.303385,92.4179687 141.550781,92.4179687 C153.921875,92.4179687 149.368434,86.0488281 159.430934,76.2851562 C164.070716,71.7831625 169.205633,80.0752159 178.855469,81.8007812 C181.723588,82.313653 187.193641,78.6873509 195.265625,70.921875 C239.088542,151.829148 261,192.282785 261,192.282785 C261,192.282785 193.428515,191.85519 58.285545,191 L123,76.2851562 Z");
        mountainBackgroundBottom.getStyleClass().add("background-bottom");
        backgroundMountain.getChildren().add(mountainBackgroundSnow);
        backgroundMountain.getChildren().add(mountainBackgroundBottom);
        backgroundMountain.setSpacing(-25);
        backgroundMountain.setAlignment(Pos.CENTER);
        backgroundMountain.getStyleClass().add("whole-mountain");
        backgroundMountain.setLayoutX(0);
        backgroundMountain.setLayoutY(0);
        backgroundMountain.setPrefSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
    }

    private void initializeAltBox() {
        highestAltBox = new HBox();
        highestAltitude = new Line(0, 50, 100, 50);
        highestAltitude.setStrokeWidth(2);
        highestAltitudeGraber = new Circle(10);
        highestAltitude.getStrokeDashArray().addAll(7d);
        //@Todo setup binding
        highestLabel = new Label("3000 müM");
        highestLabel.setMinWidth(50);
        highestAltitude.getStyleClass().add("altitude-line");
        highestLabel.getStyleClass().add("highest-label");
        highestAltBox.getChildren().add(highestAltitude);
        highestAltBox.getChildren().add(highestAltitudeGraber);
        highestAltBox.getChildren().add(highestLabel);
        highestAltBox.setAlignment(Pos.CENTER_LEFT);
        highestAltBox.setLayoutX(20);
        highestAltBox.setLayoutY(liftHeight);
        highestAltitudeGraber.getStyleClass().add("altitude-graber");
        highestAltBox.setPrefWidth(ARTBOARD_WIDTH);
        highestAltBox.getStyleClass().add("highest-altitude-Box");
        
        lowestAltBox = new HBox();
        lowestAltitude = new Line(0, 50, 100, 50);
        lowestAltitude.setStrokeWidth(2);
        lowestAltitude.getStrokeDashArray().addAll(7d);
        lowestGraber = new Circle(10);
        lowestLabel = new Label("100 müM");
        lowestLabel.setMinWidth(50);
        lowestAltitude.getStyleClass().add("altitude-line");
        lowestGraber.getStyleClass().add("altitude-graber");
        lowestLabel.getStyleClass().add("lowest-label");
        lowestAltBox.getStyleClass().add("lowest-altitude-Box");
        lowestAltBox.getChildren().add(lowestAltitude);
        lowestAltBox.getChildren().add(lowestGraber);
        lowestAltBox.getChildren().add(lowestLabel);
        lowestAltBox.setAlignment(Pos.CENTER_LEFT);
        lowestAltBox.setLayoutX(20);
        lowestAltBox.setLayoutY(talstationHeight);
        lowestAltBox.setPrefWidth(ARTBOARD_WIDTH);
    }

    private void initializeDrawingPane() {
        drawingPane = new Pane();
        drawingPane.getStyleClass().add("drawing-pane");
        drawingPane.setMaxSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setMinSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setPrefSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);

    }

    private void layoutParts() {
        drawingPane.getChildren().addAll(backgroundMountain, highestAltBox, lowestAltBox);
        layoutChildren();
        getChildren().add(drawingPane);
    }

    private void setupEventHandlers() {
        System.out.println("im events");
        /*highestAltBox.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println(event.getY());
            }
        });*/
        highestAltBox.setOnMouseDragged(event ->{
            double eventheight = event.getSceneY();
            //@Todo get height even if resized
            double maxheight = backgroundMountain.getHeight();
            double maxrealheight = 3000;
            System.out.println(eventheight);

            if (eventheight > 0 && eventheight < maxheight && eventheight < lowestAltBox.getLayoutY()) {

                highestAltBox.setLayoutY(eventheight);
                double temp = maxrealheight - (maxrealheight*eventheight)/maxheight;
                liftHeightPoint.setValue(Math.round (temp) + " müM");
                //liftHeightPoint.setValue(temp + " müM");
                //System.out.println("sfsf"+ backgroundMountain.getHeight());
            }

        } );
        lowestAltBox.setOnMouseDragged(event ->{
            double eventheight = event.getSceneY();
            double maxrealheight = 3000;
            double maxheight = backgroundMountain.getHeight();
            System.out.println(eventheight);
            if (eventheight > 0 && eventheight < backgroundMountain.getHeight() && eventheight > highestAltBox.getLayoutY()) {
                lowestAltBox.setLayoutY(eventheight);
                double temp = maxrealheight - (maxrealheight*eventheight)/maxheight;
                talstationPoint.setValue(Math.round (temp) + " müM");
            }

        } );

    }

    private void setupValueChangeListeners(){
        liftHeightPointProperty().addListener(((observable, oldValue, newValue) -> {
            liftHeight = getInt(newValue);
            System.out.println("liftHeight: " + liftHeight);
        }));

        talstationPointProperty().addListener(((observable, oldValue, newValue) -> {
            talstationHeight = getInt(newValue);
            System.out.println("talstationHeight: " + talstationHeight);
        }));
    }

    public int getInt(String test){
        try {
            return Integer.parseInt(test.trim());
        } catch (Exception e){
            return 0;
        }
    }

    private void setupBinding() {
        //ToDo dieses Binding ersetzen
        highestLabel.textProperty().bind(liftHeightPointProperty());
        lowestLabel.textProperty().bind(talstationPointProperty());
    }


    //resize by scaling
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        resize();
    }

    private void resize() {
        Insets padding = getPadding();
        double availableWidth = getWidth() - padding.getLeft() - padding.getRight();
        double availableHeight = getHeight() - padding.getTop() - padding.getBottom();

        double width = Math.max(Math.min(Math.min(availableWidth, availableHeight * ASPECT_RATIO), MAXIMUM_WIDTH), MINIMUM_WIDTH);

        double scalingFactor = width / ARTBOARD_WIDTH;

        if (availableWidth > 0 && availableHeight > 0) {
            drawingPane.relocate((getWidth() - ARTBOARD_WIDTH) * 0.5, (getHeight() - ARTBOARD_HEIGHT) * 0.5);
            drawingPane.setScaleX(scalingFactor);
            drawingPane.setScaleY(scalingFactor);
        }
    }
    // compute sizes

    @Override
    protected double computeMinWidth(double height) {
        Insets padding = getPadding();
        double horizontalPadding = padding.getLeft() + padding.getRight();

        return MINIMUM_WIDTH + horizontalPadding;
    }

    @Override
    protected double computeMinHeight(double width) {
        Insets padding = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();

        return MINIMUM_HEIGHT + verticalPadding;
    }

    @Override
    protected double computePrefWidth(double height) {
        Insets padding = getPadding();
        double horizontalPadding = padding.getLeft() + padding.getRight();

        return ARTBOARD_WIDTH + horizontalPadding;
    }

    @Override
    protected double computePrefHeight(double width) {
        Insets padding = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();

        return ARTBOARD_HEIGHT + verticalPadding;
    }

    // alle getter und setter  (generiert via "Code -> Generate... -> Getter and Setter)
    public String getLiftHeightPoint() {
        return liftHeightPoint.get();
    }

    public StringProperty liftHeightPointProperty() {
        return liftHeightPoint;
    }

    public void setLiftHeightPoint(String liftHeightPoint) {
        this.liftHeightPoint.set(liftHeightPoint);
    }

    public String getTalstationPoint() {
        return talstationPoint.get();
    }

    public StringProperty talstationPointProperty() {
        return talstationPoint;
    }

    public void setTalstationPoint(String talstationPoint) {
        this.talstationPoint.set(talstationPoint);
    }
}
