package com.luxoft.decipherpuzzle.core.entity;


import java.math.BigDecimal;
import java.util.Map;
import java.util.function.BiFunction;

public abstract class Operation implements Element, BiFunction<BigDecimal, BigDecimal, BigDecimal> {

    @Override
    public BigDecimal value(Map<Character, Integer> values) {
        return BigDecimal.ZERO;
    }

    public abstract OperationType type();
}
