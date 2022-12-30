package com.bahdanau.calculator.util;

public enum AimTypeFactor {
    LOSE_WEIGHT(-500),
    MAINTAIN_WEIGHT(0),
    ADD_WEIGHT(500);

    private final double factor;

    public double getFactor() {
        return factor;
    }

    AimTypeFactor(double factor) {
        this.factor = factor;
    }
}
