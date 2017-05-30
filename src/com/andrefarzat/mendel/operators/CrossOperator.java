package com.andrefarzat.mendel.operators;

import com.andrefarzat.mendel.nodes.Function;
import java.util.ArrayList;
import java.util.Random;


public abstract class CrossOperator {
    public abstract Function cross(Function nodeA, Function nodeB);

    public <T> T getRandomFromList(ArrayList<T> list){
        Random random = new Random();
        System.out.println(list.size());
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
