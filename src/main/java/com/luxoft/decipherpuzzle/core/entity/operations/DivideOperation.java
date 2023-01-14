package com.luxoft.decipherpuzzle.core.entity.operations;

import com.luxoft.decipherpuzzle.core.entity.Operation;
import com.luxoft.decipherpuzzle.core.entity.OperationType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DivideOperation extends Operation {

    @Override
    public BigDecimal apply(BigDecimal first, BigDecimal second) {
        return first.divide(second, 6, RoundingMode.HALF_UP);
    }

    @Override
    public OperationType type() {
        return OperationType.DIVIDE;
    }
}
