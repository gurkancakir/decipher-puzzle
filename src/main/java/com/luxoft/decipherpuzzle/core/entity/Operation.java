package com.luxoft.decipherpuzzle.core.entity;

import com.luxoft.decipherpuzzle.core.exception.InputNotAcceptException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.BiFunction;

public abstract class Operation implements Element, BiFunction<BigDecimal, BigDecimal, BigDecimal> {

    @Override
    public BigDecimal value(Map<Character, Integer> values) throws InputNotAcceptException {
        throw new InputNotAcceptException("Operations not accept inputs");
    }

    public abstract OperationType type();
}
