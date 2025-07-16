package com.example.venture_engine.Datastructures;

import java.util.LinkedList;
import java.util.List;

public class LocationGraph {
    List<Location> locations = new LinkedList<>();
    List<Edge> edges = new LinkedList<>();

    public void addNode(Location location) {
        locations.add(location);
    }

    public void addNodes(List<Location> locations) {
        for (Location location : locations) {
            addNode(location);
        }
    }

    public void addEdge(Location from, Location to, double weight, String direction) { // Adds an undirected edge
        Edge e = new Edge(from, to, weight, direction);
        edges.add(e);
        from.adjacent_edges.add(e);
        to.adjacent_edges.add(e);
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public void addEdges(List<Edge> edges) {
        for (Edge e : edges) {
            addEdge(e);
        }
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Location> getLocations() {
        return locations;
    }

}
