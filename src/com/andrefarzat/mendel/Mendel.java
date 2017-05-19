package com.andrefarzat.mendel;

public class Mendel {
    private Individual[] terminalSet;
    private Individual[] functionSet;

    public void setTerminals(Individual[] terminalSet) {
        this.terminalSet = terminalSet;
    }

    public void setFunctions(Individual[] functionSet) {
        this.functionSet = functionSet;
    }
}