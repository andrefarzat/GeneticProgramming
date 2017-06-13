package com.andrefarzat.GP;


import com.andrefarzat.GP.logging.Neo4Logger;

public class CLI {

    public static void main(String[] args) {
        Neo4Logger logger = new Neo4Logger();


        GP gp = new GP();
        gp.run();
    }
}
