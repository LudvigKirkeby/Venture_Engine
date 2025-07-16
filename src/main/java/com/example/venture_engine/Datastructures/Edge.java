package com.example.venture_engine.Datastructures;

public class Edge implements Comparable<Edge> {
    Location from, to;
    double weight;
    String direction; // Customizable. Used for traversing. Example value: North

    Edge(Location from, Location to, double weight, String direction) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.direction = direction;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
    }
}
