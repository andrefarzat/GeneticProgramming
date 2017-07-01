package com.andrefarzat.GP;


import java.util.ArrayList;

public class CLI {
    public static final int TIMES = 30;

    public static void main(String[] args) {
        System.out.println("Starting GP!");
        int index = 0;

        double[][] params = new double[][] {
//            {0.0d, 32.0d},
            {1.0d, 33.8d},
            {10.0d, 50.0d},
//            {-5.0d, 23.0d},
//            {100.0d, 212.0d},
        };

        for(int i = 0; i < CLI.TIMES; i++) {
            GP gp = new GP("test", i, params);
            System.out.println(String.format("Running %s. Round: %s", gp.getId(), i));
            gp.run();
        }

        System.out.println("Done!");
    }

    public static ArrayList<double[][]> getParams() {
        ArrayList<double[][]> params = new ArrayList<>();
        params.add(new double[][] { {12d, 13d}, {14d, 15d} }); // Simple Example
        params.add(new double[][] { {12d, 32d}, {14d, 34d}, {120d, 140d} }); // Ease Example
        params.add(new double[][] { {100.0d, 200.0d}, {350.0d, 450.0d} }); //  Not Ease Example
        params.add(new double[][] { {1.0d, 33.8d}, {10.0d, 50.0d} }); // celsius to Fahrenheit
        params.add(new double[][] { {20.0d, 293.15d}, {40.0d, 313.15d} }); // celsius to Kelvin

        return params;
    }
}
