package com.andrefarzat.mendel.selectors;

import com.andrefarzat.mendel.Population;


public interface Selector {
    Selection get(int i, int size, Population population);
}
