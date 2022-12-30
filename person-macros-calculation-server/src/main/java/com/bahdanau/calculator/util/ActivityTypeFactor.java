package com.bahdanau.calculator.util;

public enum ActivityTypeFactor {
    SITTING(1.2),
    MODERATE_ACTIVITY(1.375),
    AVERAGE_ACTIVITY(1.55),
    ABOVE_AVERAGE_ACTIVITY(1.725),
    PRO_SPORTS_ACTIVITY(1.9);

    private final double factor;

    public double getFactor() {
        return factor;
    }

    ActivityTypeFactor(double factor) {
        this.factor = factor;
    }
}
