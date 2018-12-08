package ch.fhnw.cuie.assignment_1.template;

import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;


/**
 * ToDo: CustomControl kurz beschreiben
 * <p>
 * ToDo: Autoren ergänzen / ersetzen
 *
 * @author Dieter Holz
 */
//Todo: Umbenennen.
public class MountainControl extends Region {
    private static final double ARTBOARD_WIDTH = 100;  // Todo: Breite der "Zeichnung" aus dem Grafik-Tool übernehmen
    private static final double ARTBOARD_HEIGHT = 100;  // Todo: Anpassen an die Breite der Zeichnung

    private static final double ASPECT_RATIO = ARTBOARD_WIDTH / ARTBOARD_HEIGHT;

    private static final double MINIMUM_WIDTH = 25;    // Todo: Anpassen
    private static final double MINIMUM_HEIGHT = MINIMUM_WIDTH / ASPECT_RATIO;

    private static final double MAXIMUM_WIDTH = 800;    // Todo: Anpassen


    private double liftHeight = 30;
    private double talstationHeight = 80;

    private StringProperty maxPoint = new SimpleStringProperty();
    private StringProperty minPoint = new SimpleStringProperty();

    private SVGPath mountainBackgroundSnow;
    private SVGPath mountainBackgroundBottom;

    private Line highestAltitude;
    private Circle highestAltitudeGraber;
    private Line lowestAltitude;
    private Circle lowestGraber;

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

        getStyleClass().add("simple-control");  // Todo: an den Namen der Klasse (des CustomControls) anpassen
    }

    private void initializeParts() {
        //ToDo: alle deklarierten Parts initialisieren
        double center = ARTBOARD_WIDTH * 0.5;

        initializeBackgroundMountain();
initializeAltBox();
        //mountainBackground.getStyleClass().add("background-mountain");


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

    }

    private void initializeAltBox(){
        highestAltBox = new HBox();
        highestAltitude = new Line(0,50,100,50);
        highestAltitudeGraber = new Circle(10f);
        highestAltitude.getStrokeDashArray().addAll(10d, 7d, 5d, 7d);
        highestAltBox.getChildren().add(highestAltitude);
        highestAltitude.getStyleClass().add("altitude-line");
        highestAltBox.getChildren().add(highestAltitudeGraber);
        highestAltBox.setAlignment(Pos.CENTER);
        highestAltBox.setLayoutX(50);
        highestAltBox.setLayoutY(liftHeight);
        highestAltitudeGraber.getStyleClass().add("altitude-graber");


        lowestAltBox = new HBox();
        lowestAltitude = new Line(0,50,100,50);
        lowestAltitude.getStrokeDashArray().addAll(10d, 7d, 5d, 7d);
        lowestGraber = new Circle(10);
        lowestAltitude.getStyleClass().add("altitude-line");
        lowestGraber.getStyleClass().add("altitude-graber");
        lowestAltBox.getChildren().add(lowestAltitude);
        lowestAltBox.getChildren().add(lowestGraber);
        lowestAltBox.setAlignment(Pos.CENTER);
        lowestAltBox.setLayoutX(50);
        lowestAltBox.setLayoutY(talstationHeight);

    }

    private void initializeDrawingPane() {
        drawingPane = new Pane();
        drawingPane.getStyleClass().add("drawing-pane");
        drawingPane.setMaxSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setMinSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setPrefSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
    }

    private void layoutParts() {
        // ToDo: alle Parts zur drawingPane hinzufügen
        //drawingPane.getChildren().addAll(backgroundCircle, display);
        drawingPane.getChildren().addAll(backgroundMountain, highestAltBox, lowestAltBox);

        getChildren().add(drawingPane);
    }

    private void setupEventHandlers(){
        // ToDo: meow
    }

    private void setupValueChangeListeners(){
        maxPointProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println("max: " + newValue);
        }));

        minPointProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println("min: " + newValue);
        }));
    }

    private void setupBinding() {
        //ToDo dieses Binding ersetzen
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

    public String getMaxPoint() {
        return maxPoint.get();
    }

    public StringProperty maxPointProperty() {
        return maxPoint;
    }

    public void setMaxPoint(String maxPoint) {
        this.maxPoint.set(maxPoint);
    }

    public String getMinPoint() {
        return minPoint.get();
    }

    public StringProperty minPointProperty() {
        return minPoint;
    }

    public void setMinPoint(String minPoint) {
        this.minPoint.set(minPoint);
    }
}
