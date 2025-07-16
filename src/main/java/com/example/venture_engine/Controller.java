package com.example.venture_engine;

import java.util.HashMap;

import com.example.venture_engine.Datastructures.Location;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;

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

                locationInputPopup(world);

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

    public void locationInputPopup(Point2D world) { 
        Popup location_input_popup = new Popup();
                VBox location_input_popup_vbox = new VBox(10);

                location_input_popup_vbox.setStyle("-fx-background-color: white; -fx-border-color: black;");
                location_input_popup_vbox.setPadding(new Insets(10));
                
                TextField location_name = new TextField();
                location_name.setPromptText("Location Name...");
                
                TextField location_description = new TextField();
                location_description.setPromptText("Location Description...");
                
                // should have the option to add multiple actions, should be returned as a list
                TextField location_actions = new TextField();
                location_actions.setPromptText("Location Actions...");
                
                location_input_popup_vbox.getChildren().addAll(location_name, location_description, location_actions);

        
                location_input_popup.getContent().add(location_input_popup_vbox);
                
            
                location_input_popup_vbox.setOnKeyPressed(b -> {
                    if (b.getCode() == KeyCode.ENTER) {
                        b.consume();
                        location_input_popup.hide();
                        String n = location_name.getText();
                        String d = location_description.getText();


                        // HashMap<String, String> a = location_actions.getText(); Actions should be a hashmap, that is, a trigger key and a reaction. Could include value changes?

                        
                        if (n != null && d != null) {
                            Location location = new Location(world.getX(), world.getY(), n, d);
                            view.getGroupNodes().getChildren().add(location);
                        }
                    }
                });

                location_input_popup.show(view.getStage());

                view.getClearButton().setDisable(true);

                // 2) when the popup hides, re‑enable it
                location_input_popup.setOnHidden(ev -> {
                    view.getClearButton().setDisable(false);
                });

                location_input_popup.setAutoHide(true);

    }

    public void setView(View view) { this.view = view; }
}