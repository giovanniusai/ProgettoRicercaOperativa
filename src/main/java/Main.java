package main.java;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        for (int cont = 1; cont < 15; cont++) {
        Instance instance = new InstanceReader().readInstance("vrpnc" + Integer.toString(cont)+ ".txt");
        SequentialClarkeWright cw = new SequentialClarkeWright(instance);
        ParallelClarkeWright cwp = new ParallelClarkeWright(instance);




            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("files/Results/results" + Integer.toString(cont) +".txt")))) {
                int i = 0;
                bw.write("RotteSeq, Costo RottaSeq, Costo GlobaleSeq, TempoSeq");

                for (Route route : cw.getRoutes()) {
                    writeText("\n", bw);
                    route.getRoute().forEach(node -> {
                        writeText(Integer.toString(node.getId()) + " ", bw);
                    });

                    writeText(", " + Double.toString(route.getTotalDistance()), bw);
                    if (i == 0) {
                        writeText(", " + Double.toString(cw.getTotalCost()), bw);
                        writeText(", " + Double.toString(cw.getTime()), bw);
                    }
                    i++;

                }

                i = 0;
                bw.write("\n\nRottePar, Costo RottaPar, Costo GlobalePar, TempoPar");
                for (Route route : cwp.getRoutes()) {
                    writeText("\n", bw);
                    route.getRoute().forEach(node -> {
                        writeText(Integer.toString(node.getId()) + " ", bw);
                    });

                    writeText(", " + Double.toString(route.getTotalDistance()), bw);
                    if (i == 0) {
                        writeText(", " + Double.toString(cwp.getTotalCost()), bw);
                        writeText(", " + Double.toString(cwp.getTime()), bw);
                    }
                    i++;

                }

            }
        }
    }

        private static void writeText (String s, BufferedWriter bw){
            try {
                bw.write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
