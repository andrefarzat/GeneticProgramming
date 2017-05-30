package com.andrefarzat.mendel.nodes;

import com.andrefarzat.mendel.MendelValue;

public abstract class Node {
    public abstract MendelValue getValue(MendelValue value);

    public abstract Node clone();

    public abstract void mutate();

    public abstract String toString();
}