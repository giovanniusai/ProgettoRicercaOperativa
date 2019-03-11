package main.java;

import java.util.ArrayList;
import java.util.List;

public class SequentialClarkeWright extends ClarkeWright {


    public SequentialClarkeWright(Instance instance){
        super(instance);
    }


    @Override
    protected void solveCW(){
        List<Node> nodeList = getInstance().getNodesList();
        for (Node node : nodeList){
            if (node.getId() !=0){
                Route route = new Route(getInstance().getdNode(), node, distances, getInstance().getCapacity());
                routes.add(route);
            }
        }

        List<Route> temp = new ArrayList<>(routes);
        routes.forEach(route ->
            savings.getSortedSavings().forEach(pair -> {
                mergeRight(pair, temp);
                mergeLeft(pair, temp);
            })
        );
        routes = temp;
    }
}
