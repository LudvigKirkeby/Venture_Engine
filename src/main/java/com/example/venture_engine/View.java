package com.example.venture_engine;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;



public class View {
    GraphicsContext gc;
    Affine trans;
    private Canvas canvas;
    private Controller controller;
    private Stage stage;
    Button clearButton;
    StackPane root;
    Group nodeGroup; // Should be moved to Model since Model holds all data

    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    View() throws IOException {
        trans = new Affine();
    }

    public void initialize(Stage stage) throws IOException {
        this.stage = stage;

        stage.setWidth(screenWidth);
        stage.setHeight(screenHeight);
        stage.setTitle("Venture Engine");

        root = new StackPane();
        canvas = new Canvas(); // Empty, gets set to the size of its container (stackpane, borderpane...) in setScene
        nodeGroup = new Group();

        nodeGroup.getChildren().add(new Circle(50, 50, 20, Color.RED));
        nodeGroup.getTransforms().add(trans);

        Scene scene = setScene();
        stage.setScene(scene);
        stage.show();
    }

    public void pan(double dx, double dy) {
        trans.prependTranslation(dx, dy);
        // update the group’s transforms so the scene graph re‐renders
        nodeGroup.getTransforms().setAll(trans);
    }

    public void zoom(double Δx, double Δy, double factor) {
        pan(-Δx, -Δy);
        trans.prependScale(factor, factor);
        pan(Δx, Δy);
    }

    public Scene setScene() throws IOException {
        gc = canvas.getGraphicsContext2D();

        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());


        HBox topArea = new HBox(10);
        clearButton = new Button("Clear");
        clearButton.setDefaultButton(false);

        topArea.getChildren().add(clearButton);

        StackPane.setAlignment(topArea, Pos.TOP_LEFT);

        // CHANGE: nodeGroup should be a getter from Model, so that it initializes the UI with the group, and then you can add elements dynamically w/ model
        root.getChildren().addAll(canvas, nodeGroup, topArea); // List; works as a stack, displaying in that order

        Scene scene = new Scene(root, screenWidth, screenHeight);
        return scene;
    }

    public StackPane getParent() { return root; }

    public void clear() { nodeGroup.getChildren().clear(); } // empties the node list

    public void setController(Controller controller) { this.controller = controller; }

    public Button getClearButton() { return clearButton; }

    public Group getGroupNodes() { return nodeGroup; }

    public Stage getStage() { return stage; }
}
