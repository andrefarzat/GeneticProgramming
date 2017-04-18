package com.andrefarzat.GP;


import java.util.ArrayList;


public class CLI {

    public static int[][] getParams() {
        int[][] values = {
            {1, 2}
        };

        return values;
    }

    public static void main(String[] args) {
        int n = 100;
        int budget = 10;
        int[][] params = CLI.getParams();

        GP gp = new GP();
        nodes.Node[] population = gp.generateInitialPopulation(n, params[0][1]);
        ArrayList<nodes.Node> validNodes = new ArrayList<nodes.Node>();

        while (--budget > 0) {
            System.out.println("Budget: " + budget);
            for(nodes.Node node : population) {
                int y = params[0][1];
                int value = node.getValue();

                System.out.println(node.toString());

                if (value == y) {
                    validNodes.add(node);
                    System.out.println("tem: " + node.toString());
                }
            }

            if (!validNodes.isEmpty()) {
                break;
            } else {
                population = gp.mutatePopulation(population, params[0][1]);
            }
        }
    }
}
