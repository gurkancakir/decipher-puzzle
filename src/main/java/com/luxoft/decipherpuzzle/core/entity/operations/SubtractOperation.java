package com.luxoft.decipherpuzzle.core.entity.operations;

import com.luxoft.decipherpuzzle.core.entity.Operation;
import com.luxoft.decipherpuzzle.core.entity.OperationType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SubtractOperation extends Operation {

    @Override
    public BigDecimal apply(BigDecimal first, BigDecimal second) {
        return first.subtract(second);
    }

    @Override
    public OperationType type() {
        return OperationType.SUBTRACT;
    }
}
