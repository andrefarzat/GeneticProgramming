package com.andrefarzat.GP;


public class CLI {

    public static int[][] getParams() {
        int[][] values = {
            {1, 14},
            {2, 13},
            {3, 12},
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
                    String solution = node.toString();
                    System.out.println("F(x) = " + solution);
                    System.out.println("Results: ");

                    for(int[] param: params) {
                        String msg = ("F(x) = " + solution).replace("x", Integer.toString(param[0]));
                        System.out.println(msg + " = " + param[1]);
                    }
                    System.exit(0);
                }
            }

            population = gp.mutatePopulation(population);
        }

        System.out.println("É, tentamos...");
        System.exit(1);
    }
}
