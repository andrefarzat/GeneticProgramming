package com.andrefarzat.GP;


import java.util.ArrayList;


public class CLI {

    public static int[][] getParams() {
        int[][] values = {
            {8, 200},
            {4, 90},
            {80, -53},
            {27, 50},
            {32, 34}
        };

        return values;
    }

    public static void main(String[] args) {
        int n = 100;
        int budget = 1000;
        int[][] params = CLI.getParams();

        GP gp = new GP();
        nodes.Node[] population = gp.generateInitialPopulation(n);

        while (budget-- > 0) {
            System.out.println("Budget: " + budget);

            for(nodes.Node node : population) {
                boolean isValidSolution = gp.givenNodeIsValidSolution(node, params);

                if (isValidSolution) {
                    System.out.println("Solução: " + node.toString());
                    System.exit(0);
                }
            }

            population = gp.mutatePopulation(population);
        }

        System.out.println("É, tentamos...");
        System.exit(1);
    }
}
