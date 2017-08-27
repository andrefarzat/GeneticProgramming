package com.andrefarzat.GP;


public class CLI {

    public static void main(String[] args) {
        System.out.println("Starting GP!");

        String[] leftList = {"foo"};
        String[] rightList = {"bar"};

        GP gp = new GP("test", 0, leftList, rightList);
        gp.run();

        System.out.println("Done!");
    }
}
