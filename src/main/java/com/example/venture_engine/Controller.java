package com.example.venture_engine;

import com.example.venture_engine.Datastructures.Location;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller {
    private double last_x = 0;
    private double last_y = 0;
    private View view;
    private StackPane stackPane;

    // Currently, view is set using setView() in main, and thus initialized is first called when view is set and canvas is created
    public void initialize() {
        stackPane = view.getParent();

        System.out.println(stackPane.getWidth());

        view.getClearButton().setOnAction(e -> view.clear());

        stackPane.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {

                double sceneX = e.getSceneX();
                double sceneY = e.getSceneY();

                Point2D world = view.getGroupNodes().sceneToLocal(sceneX, sceneY);

                Circle c = new Circle(world.getX(), world.getY(), 20, Color.BLUE); // Should be Location nodes
                view.getGroupNodes().getChildren().add(c);

            } else if (e.getButton() == MouseButton.SECONDARY) {
                last_x = e.getX();
                last_y = e.getY();
                // consume so that drag/scroll also sees focus etc
                stackPane.requestFocus();
            }
        });

        stackPane.setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.SECONDARY) { // Pan on right click drag
                stackPane.requestFocus();
                double dx = e.getX() - last_x;
                double dy = e.getY() - last_y;
                view.pan(dx, dy);

                last_x = e.getX();
                last_y = e.getY();
            }
        });

        stackPane.setOnScroll(e -> {
            double factor = e.getDeltaY();

            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();

            Point2D world = view.getGroupNodes().sceneToLocal(sceneX, sceneY);
            view.zoom(world.getX(), world.getY(), Math.pow(1.01, factor));
        });
    }

    public void addLocation(Location location) {
        view.getGroupNodes().getChildren().add(location);
    }

    public void setView(View view) { this.view = view; }
}