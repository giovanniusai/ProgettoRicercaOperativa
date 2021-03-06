package main.java;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class ClarkeWright {
    private Instance instance;
    protected List<Route> routes;
    protected DistanceMatrix distances;
    protected SavingsMatrix savings;
    private double time;

    public ClarkeWright(Instance instance){
        long currrentTime = System.currentTimeMillis();
        this.setInstance(instance);
        distances = new DistanceMatrix(getInstance().getNodesList(), getInstance().getdNode());

        savings = new SavingsMatrix(getInstance().getNodesList(), getInstance().getdNode(), distances);

        routes = new ArrayList<>();


        solveCW();
        setTime(System.currentTimeMillis()- currrentTime);


    }

    protected void mergeLeft(Pair<Node, Node> pair, List<Route> routes) {
        Node iNode = pair.getKey();
        Node jNode = pair.getValue();

        //cerca una rotta il cui penultimo elemento sia j
        Route first = null;
        for (Route r : routes) {
            if (r.getRoute().get(r.getRoute().size() - 2).equals(jNode))
                first = r;
        }

        if (first != null) {
            //cerco una rotta (diversa) il cui secondo elemento sia i
            Route second = null;
            int secondIdx = -1;

            int i = 0;
            for (Route r : routes) {
                if (!r.equals(first) && r.getRoute().get(1).equals(iNode)) {
                    second = r;
                    secondIdx = i;
                    break;
                }
                i++;
            }

            //se ho trovato entrambe le rotte, uniscile
            if (second != null) {
                try {
                    first.merge(second);
                    routes.remove(secondIdx);
                } catch (CapacityExceeded e) {
                }
            }
        }
    }


    protected void mergeRight(Pair<Node, Node> pair, List<Route> routes) {
        Node iNode = pair.getKey();
        Node jNode = pair.getValue();

        //cerca una rotta il cui secondo elemento sia j
        Route first = null;
        for (Route r : routes) {
            if (r.getRoute().get(1).equals(jNode))
                first = r;
        }

        if (first != null) {
            //cerco una rotta (diversa) il cui penultimo elemento sia i
            Route second = null;
            int secondIdx = -1;

            int i = 0;
            for (Route r : routes) {
                if (!r.equals(first) && r.getRoute().get(r.getRoute().size() - 2).equals(iNode)) {
                    second = r;
                    secondIdx = i;
                    break;
                }
                i++;
            }

            //se ho trovato entrambe le rotte, uniscile
            if (second != null) {

                try {
                    first.merge(second);
                    routes.remove(secondIdx);
                } catch (CapacityExceeded e) {}
            }
        }
    }




    protected abstract void solveCW();

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public void setTime(double time){
        this.time = time;
    }

    public double getTime(){
        return time;
    }

    public Instance getInstance(){
        return instance;
    }

    public List<Route> getRoutes(){
        return routes;
    }

    public Double getTotalCost(){
        return routes.stream().map(route -> route.getTotalDistance()).reduce(Double::sum).get();
    }
}
