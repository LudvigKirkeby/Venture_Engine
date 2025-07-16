package com.example.venture_engine.Datastructures;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Location extends StackPane { // Extends node in order to be added to the list of nodes
    public String description;
    public String name;
    private int x, y;
    private Rectangle rect;
    private Text label1, label2;
    List<Action> actions;
    List<Edge> adjacent_edges = new ArrayList<>(); // Knows its adjacent connections (and thus rooms, with a getter for "to")

    public Location(double x, double y, String name, String description, List<Action> actions) {
        this(x, y, name, description);
        this.actions = actions;
    }

    public Location(double x, double y, String name, String description) {
        this.name = name;
        this.description = description;
        this.setLayoutX(x);
        this.setLayoutY(y);
        setShape();
    }

    public Location() {}

    public void addAction(Action action) {
        actions.add(action);
    }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public List<Action> getActions() { return actions; }

    public List<Edge> getAdjacentEdges() { return adjacent_edges; }

    public void printDescription() {  StringBuilder segmented = new StringBuilder();
        String[] words = description .split(" ");
        short count = 0;
        for (String word : words) {
            segmented.append(word);
            segmented.append(" ");
            count++;
            if (count == 20) {
                System.out.println(segmented);
                System.out.println();
                segmented = new StringBuilder();
                count = 0;
            }
        }
        System.out.println(segmented);
    }

    public String getName() { return name; }

    public void setShape() {
        rect = new Rectangle(80, 80);
        rect.setFill(Color.LIGHTGRAY);
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(10);
        rect.setArcHeight(10);
        this.getChildren().add(rect);

        VBox location_labels = new VBox();

        ArrayList<Text> l = new ArrayList<>();
        label1 = new Text(name);
        label2 = new Text(description);

        l.add(label1);
        l.add(label2);

        for (Text label : l) {
            label.setFont(Font.font(14));
            label.setFill(Color.DARKBLUE);
            label.wrappingWidthProperty().bind(rect.widthProperty().subtract(10)); // allow wrapping
            label.setTextOrigin(javafx.geometry.VPos.CENTER);
            location_labels.getChildren().add(label);
        }
        
        this.getChildren().add(location_labels);

        // Position elements in a stack (square at bottom, text on top)
        this.setAlignment(Pos.CENTER);
        

        // Optionally, enable mouse hover tooltip or click handler here
        this.setOnMouseClicked(evt -> {
            // e.g., show description in console or a detail pane
            System.out.println("Location clicked: " + name + " - " + description + " Actions: " + actions);
        });
    }
}
