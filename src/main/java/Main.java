package main.java;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Instance instance = new InstanceReader().readInstance("prova.txt");
        SequentialClarkeWright cw = new SequentialClarkeWright(instance);
        cw.getRoutes().forEach(route -> route.getRoute().forEach(node -> System.out.println(node.getId())));
        }
}