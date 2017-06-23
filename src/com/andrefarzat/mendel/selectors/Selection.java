package com.andrefarzat.mendel.selectors;


import com.andrefarzat.mendel.Individual;

public class Selection {
    private Types type;
    private Individual father;
    private Individual mother;

    public Types getType() { return this.type; }
    public Individual getFather() { return this.father; }
    public Individual getMother() { return this.mother; }

    public Selection(Types type) {
        this.type = type;
    }

    public Selection(Types type, Individual father) {
        this.type = type;
        this.father = father;
    }

    public Selection(Types type, Individual father, Individual mother) {
        this.type = type;
        this.father = father;
        this.mother = mother;
    }

    public enum Types {
        clone,
        crossover,
        mutation,
        discarded
    }

}
