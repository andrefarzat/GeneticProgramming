package com.andrefarzat.GP.nodes;


import com.andrefarzat.GP.Utils;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Terminal implements Node {
    public static final List<String> options = new ArrayList<>(
        Arrays.asList("a-z", "A-Z", ".")
    );


    protected static final int optionsLength = options.size();
    public String value = "";

    public Terminal() {}
    public Terminal(String value) { this.value = value; }

    public void mutate() {
        this.value = this.generateRandomValue();
    }
    public void mutate(List<String> options) { this.value = Utils.getFromListRandomly(options); }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Terminal clone() {
        Terminal terminal = new Terminal();
        terminal.value = this.value;
        return terminal;
    }

    public String generateRandomValue() {
        return Utils.getFromListRandomly(Terminal.options);
    }

    public static Terminal create() {
        Terminal terminal = new Terminal();
        terminal.mutate();
        return terminal;
    }

    public static Terminal create(List<String> options) {
        Terminal terminal = new Terminal();
        terminal.mutate(options);
        return terminal;
    }

    public String toString() {
        return this.value;
    }
}


/*
 Instance independent terminals are:
    * the alphabeticalranges a-z and A-Z,
    * the start of string anchor ^
    * the end of string anchor $
    * the wildcard character .

 Instance dependent terminals are:
    * all characters appearing in leftList
    * partial ranges appearing in leftList
    * n-grams

   Partial ranges are obtained as follows.  We (i) build the sequenceCof all characters appearing inM (without rep-etitions),  sorted  according  to  natural  order;  (ii)  for  each maximal subsequence ofCwhich includesall characters be-tween subsequence headchand tailct , build a partial range ch-ct.  For example, ifM=fbar;den;foo;cang , then the partial ranges area-eandn-o . n-grams are obtained as follows.  We (i) build the setN of all n-grams occurring inMandUstrings, with2n4 ; (ii) give a score to each n-gram as follows:+1 for each string in Mwhich contains the n-gram and1for each stringU which contains the n-gram;  (iii) sortN according to descending score and (iv) select the smallest subsetN0 of all top-scoring n-grams such that the sum of their scores is at leastjM j and each individual score is positive.
 */
