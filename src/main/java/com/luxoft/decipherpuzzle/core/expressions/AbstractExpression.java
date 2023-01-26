package com.luxoft.decipherpuzzle.core.expressions;

import com.luxoft.decipherpuzzle.core.entity.Element;
import com.luxoft.decipherpuzzle.core.entity.Operation;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public abstract class AbstractExpression implements Expression {

    protected String inputExpression;

    protected Map<Character, Boolean> chars;

    public AbstractExpression() {
        this.chars = new HashMap<>();
    }

    protected void foreach(List<Element> elementList, Map<Character, Integer> values, StringBuilder builder) {
        Element element;
        for (int i = elementList.size() - 1; i >= 0; i--) {
            element = elementList.get(i);
            if (element instanceof Operation operation) {
                builder.append(operation.type().getType());
            } else {
                builder.append(element.value(values));
            }
        }
    }

    public BigDecimal execute(List<Element> elementList, Map<Character, Integer> values) {
        BigDecimal result = BigDecimal.ZERO;
        Element element;
        for (int i = elementList.size() - 1; i >= 0; i--) {
            element = elementList.get(i);
            if (element instanceof Operation operation) {
                Element nextElement = elementList.get(--i);
                result = operation.apply(result, nextElement.value(values));
            } else {
                result = result.add(element.value(values));
            }
        }
        return result;
    }

    public void addChars(Character character) {
        if (!chars.containsKey(character)) {
            chars.put(character, false);
        }
    }

    public Map<Character, Boolean> getChars() {
        return this.chars;
    }

    public void addInputExpression(String inputExpression) {
        this.inputExpression = inputExpression;
    }

}
