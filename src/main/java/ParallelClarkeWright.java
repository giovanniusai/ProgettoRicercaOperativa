package main.java;

import java.util.List;

public class ParallelClarkeWright  extends ClarkeWright{
    public ParallelClarkeWright(Instance instance){
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
        savings.getSortedSavings().forEach(pair ->
                mergeRight(pair, routes));


    }
}
