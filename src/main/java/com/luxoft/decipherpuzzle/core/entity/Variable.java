package com.luxoft.decipherpuzzle.core.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
@ToString
public class Variable implements Element {

    private List<VariableItem> variables;

    public Variable() {
        this.variables = new CopyOnWriteArrayList<>();
    }

    public void addVariable(VariableItem variable) {
        this.variables.add(variable);
    }

    @Override
    public BigDecimal value(Map<Character, Integer> values) {
        //Collections.sort(this.variables);
        BigDecimal result = BigDecimal.ZERO;
        for (VariableItem variable : this.variables) {
            result = result.add(variable.value(values));
        }
        return result;
    }

    public Character getFirstCharacter() {
        return this.variables.get(this.variables.size() - 1).key();
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
