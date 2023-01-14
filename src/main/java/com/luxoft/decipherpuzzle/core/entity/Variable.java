package com.luxoft.decipherpuzzle.core.entity;

import com.luxoft.decipherpuzzle.core.exception.InputNotAcceptException;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@ToString
public class Variable implements Element {

    private List<VariableItem> variables;

    public Variable() {
        this.variables = new ArrayList<>();
    }

    public void addVariable(VariableItem variable) {
        this.variables.add(variable);
    }

    @Override
    public BigDecimal value(Map<Character, Integer> values) throws InputNotAcceptException {
        Collections.sort(this.variables);
        if (!checkAcceptable(values)) {
            throw new InputNotAcceptException("Inputs not started zero");
        }
        BigDecimal result = BigDecimal.ZERO;
        for (VariableItem variable : this.variables) {
            result = result.add(variable.value(values));
        }
        return result;
    }

    private boolean checkAcceptable(Map<Character, Integer> values) {
        return values.get(this.variables.get(0).key()) != 0;
    }

    public record VariableItem(Character key, Integer factor) implements Comparable<VariableItem> {

        public BigDecimal value(Map<Character, Integer> values) {
            return new BigDecimal(factor * values.get(key));
        }

        @Override
        public int compareTo(VariableItem o) {
            return o.factor().compareTo(this.factor());
        }
    }
}
