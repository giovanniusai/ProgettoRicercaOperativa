package main.java;

import java.io.*;
import java.util.ArrayList;

public class InstanceReader {

    private BufferedReader fileReader;
    private String split;
    private File file;

    public InstanceReader(){
        fileReader = null;
        file = null;
        split = " ";
    }

    public Instance readInstance(String nameInstance){
        ArrayList<Node> nodesList = new ArrayList<>();
        int nCustomers = 0;
        int capacity = 0;
        DepositNode dNode = null;
        int rTime = 0;
        int dTime = 0;

        try{
            String line;
            fileReader = new BufferedReader(new FileReader("files\\" + nameInstance));
            int i = 0;
            while ((line = fileReader.readLine()) != null){
                int x = 0;
                int y = 0;
                int q = 0;


                switch (i){
                    case 0:
                        String[] col1 = line.split(split);
                        nCustomers = Integer.parseInt(col1[1]);
                        capacity = Integer.parseInt(col1[2]);
                        rTime = Integer.parseInt(col1[3]);
                        dTime = Integer.parseInt(col1[4]);
                        break;

                    case 1:
                        String[] colDeposit = line.split(split);
                        x = Integer.parseInt(colDeposit[1]);
                        y = Integer.parseInt(colDeposit[2]);
                        dNode = new DepositNode(x, y, i-1);
                        break;

                    default:
                        String[]colCustomer = line.split(split);
                        x = Integer.parseInt(colCustomer[1]);
                        y = Integer.parseInt(colCustomer[2]);
                        q = Integer.parseInt(colCustomer[3]);
                        nodesList.add(new Node(x, y, q, i-1));


                }
                i++;

            }

        }
        catch (FileNotFoundException e){
            System.out.println("Error in InstanceReader");
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                fileReader.close();
            }
            catch (IOException e){
                System.out.println("Error while closing FileReader");
                e.printStackTrace();
            }
        }

        return new Instance(nCustomers, capacity, rTime, dTime, dNode, nodesList);

    }

}
