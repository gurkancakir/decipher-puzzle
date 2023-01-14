package com.luxoft.decipherpuzzle.core;

import com.luxoft.decipherpuzzle.core.entity.Operation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OperationFactory {

    private final List<Operation> operationList;

    public Operation create(Character character) {
        for (Operation operation : operationList) {
            if (operation.type().getType().equals(String.valueOf(character)))
                return operation;
        }
        return null;
    }
}
