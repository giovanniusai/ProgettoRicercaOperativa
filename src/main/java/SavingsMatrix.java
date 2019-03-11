package main.java;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class SavingsMatrix {

    private List<Node> nodes;
    private DepositNode dNode;
    private DistanceMatrix distances;

    private Map<Pair<Node, Node>, Double> savings = new HashMap<>();
    private List<Pair<Node, Node>> sortedSavings;

    public SavingsMatrix(List<Node> nodes, DepositNode dNode, DistanceMatrix distances) {
        this.nodes = nodes;
        this.dNode = dNode;
        this.setDistances(distances);
        computeMatrix();
        computeSavings();
    }

    private void computeMatrix() {

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                Node first = nodes.get(i);
                Node second = nodes.get(j);

                //non metto coppie riflessive ne simmetriche
                if (!first.equals(second) && !getSavings().containsKey(new Pair<>(nodes.get(j), nodes.get(i))) && first.getId() != 0 && second.getId() != 0) {
                    double sav = getDistances().getDistance(first, dNode) + getDistances().getDistance(dNode, second) - getDistances().getDistance(first, second);
                    getSavings().put(new Pair<>(nodes.get(i), nodes.get(j)), sav);
                }
            }
        }
    }

    private void computeSavings() {
        //ottengo la lista dei savings ordinata
        sortedSavings = getSavings().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .map(pair -> pair.getKey())
                .collect(Collectors.toList());
    }


    public List<Pair<Node, Node>> getSortedSavings() {
        return sortedSavings;
    }

    public DistanceMatrix getDistances() {
        return distances;
    }

    public void setDistances(DistanceMatrix distances) {
        this.distances = distances;
    }

    public Map<Pair<Node, Node>, Double> getSavings() {
        return savings;
    }

}
