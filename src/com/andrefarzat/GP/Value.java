package com.andrefarzat.GP;


import com.andrefarzat.mendel.MendelValue;
import com.andrefarzat.mendel.Utils;

import java.math.BigDecimal;

public class Value implements MendelValue {
    private BigDecimal value;

    public BigDecimal get() { return this.value; }

    public void set(Object value) { this.set((BigDecimal) value); }
    public void set(int value)    { this.set(new BigDecimal(value)); }
    public void set(Double value) { this.value = new BigDecimal(value); }
    public void set(BigDecimal value) {
        value.setScale(1, BigDecimal.ROUND_DOWN);
        this.value = value;
    }

    public String toString() {
        return String.format("%.1f", this.value);
    }
}
