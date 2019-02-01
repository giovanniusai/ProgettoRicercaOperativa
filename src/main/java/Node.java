package main.java;

public class Node {

    private int x,y,q;

    public Node(int x, int y){
        this.x = x;
        this.y = y;

    }

    public Node(int x, int y, int q){
        this.x = x;
        this.y = y;
        this.q = q;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getQ(){
        return q;
    }
}
