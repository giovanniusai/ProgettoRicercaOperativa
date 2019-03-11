package main.java;

import java.util.List;

public class DistanceMatrix {
    private double matrix[][];
    private List<Node> nodes;

    public DistanceMatrix(List<Node> nodes, DepositNode dNode){
        this.nodes = nodes;
        this.nodes.add(dNode);
        this.matrix = new double[nodes.size()+1][nodes.size()+1];

        calculateMatrix();
    }

    private void calculateMatrix(){
        for (int i = 0; i < nodes.size(); i++){
            for (int j = 0; j < nodes.size(); j++){
                matrix[nodes.get(i).getId()][nodes.get(j).getId()] = calculateDistance(nodes.get(i), nodes.get(j));
            }
        }
    }

    public static double calculateDistance(Node a, Node b){
        return Math.sqrt(Math.pow(a.getX()-b.getX(),2) + Math.pow(a.getY()- b.getY(),2));
    }

    public double getDistance(Node a, Node b){
        return matrix[a.getId()] [b.getId()];
    }
}
