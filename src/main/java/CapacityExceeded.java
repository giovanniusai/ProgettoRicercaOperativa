package main.java;

public class CapacityExceeded extends RuntimeException {
    public CapacityExceeded(){
        super("Routes cannot be merged, capacity exceeded!");
    }
}
