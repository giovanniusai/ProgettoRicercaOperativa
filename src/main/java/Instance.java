package main.java;

import java.util.ArrayList;

public class Instance {
    private int capacity = 0;
    private int nCustomers = 0;
    private int rTime =0;
    private int dTime =0;
    private DepositNode dNode;
    private ArrayList<Node> nodesList;
    private String instanceName;

    public Instance(int nCustomers, int capacity, int rTime, int dTime, DepositNode dNode, ArrayList<Node> nodesList){

        setCapacity(capacity);
        setnCustomers(nCustomers);
        setdNode(dNode);
        setInstanceName(instanceName);
        setNodesList(nodesList);

    }

    private void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity(){ return capacity; }

    private void setnCustomers(int nCustomers){
        this.nCustomers = nCustomers;
    }

    private void setdNode(DepositNode dNode){
        this.dNode = dNode;
    }

    public DepositNode getdNode() {
        return dNode;
    }

    private void setInstanceName(String instanceName){
        this.instanceName = instanceName;
    }

    public String getInstanceName(){
        return instanceName;
    }

    private void setNodesList(ArrayList<Node> nodesList){
        this.nodesList = nodesList;
    }

    public ArrayList<Node> getNodesList() {
        return nodesList;
    }


    private void setrTime(int rTime){
        this.rTime = rTime;
    }

    private void setdTime(int dTime){
        this.dTime = dTime;
    }
}
