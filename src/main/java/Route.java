package main.java;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private double totalDistance = 0;

    private int capacity;
    private List<Node> route = new ArrayList<>();
    private DistanceMatrix dMatrix;
    private int totalQuantity;

    public Route(Node dNode, Node node, DistanceMatrix dMatrix, int capacity) {
        this.dMatrix = dMatrix;
        this.capacity = capacity;
        double distance = dMatrix.getDistance(dNode, node);
        this.totalQuantity = node.getQ();


        route.add(dNode);
        route.add(node);
        route.add(dNode);

        totalDistance = distance * 2;

    }

    public void merge(Route r) {

        //check if the merge is allowed
        if (this.totalQuantity + r.totalQuantity > capacity)
            throw new CapacityExceeded();


        //togli i warehouse dalle rotte
        Node last = this.route.remove(this.route.size() - 1);
        if (!(last instanceof DepositNode))
            throw new RuntimeException("Rotta che non finisce in deposito!");

        Node first = r.route.remove(0);
        if (!(first instanceof DepositNode))
            throw new RuntimeException("Rotta che non inizia in deposito!");

        Node newLast = this.route.get(this.route.size() - 1);
        Node newFirst = r.route.get(0);

        double newEdgeDistance = dMatrix.getDistance(newFirst, newLast);

        //aggiorno la rotta
        this.route.addAll(r.route);


        //aggiorno la distanza
        this.totalDistance -= dMatrix.getDistance(newLast, last);
        this.totalDistance -= dMatrix.getDistance(newFirst, first);
        this.totalDistance += newEdgeDistance;
        this.totalDistance += r.totalDistance;


        //aggiorno i carichi
        this.totalQuantity += r.totalQuantity;

    }

    public List<Node> getRoute() {
        return route;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public int getTotalQuantity(){
        return totalQuantity;
    }


    }
