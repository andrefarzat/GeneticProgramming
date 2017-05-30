package com.andrefarzat.GP;


import com.andrefarzat.mendel.MendelValue;

public class Value implements MendelValue {
    private Double value;

    public Double get() {
        return this.value;
    }

    public void set(Object value) {
        this.set((Double) value);
    }

    public void set(Double value) {
        this.value = value;
    }

    public String toString() {
        return String.format("%s", this.value);
    }
}
