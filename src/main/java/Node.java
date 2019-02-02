package main.java;

public class Node {

    private int x,y,q, id;

    public Node(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;

    }

    public Node(int x, int y, int q, int id){
        this.x = x;
        this.y = y;
        this.q = q;
        this.id = id;
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

    public int getId(){
        return id;
    }
}
