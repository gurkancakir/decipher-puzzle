package com.luxoft.decipherpuzzle.core.expressions;

import com.luxoft.decipherpuzzle.core.entity.Element;
import com.luxoft.decipherpuzzle.core.entity.Operation;
import com.luxoft.decipherpuzzle.core.exception.InputNotAcceptException;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public abstract class AbstractExpression implements Expression {

    protected String inputExpression;

    protected Map<Character, Integer> chars;

    public AbstractExpression() {
        this.chars = new HashMap<>();
    }

    protected void foreach(List<Element> elementList, Map<Character, Integer> values, StringBuilder builder) throws InputNotAcceptException {
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

    public BigDecimal execute(List<Element> elementList, Map<Character, Integer> values) throws InputNotAcceptException {
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
        if (chars.containsKey(character)) {
            chars.put(character, chars.get(character) + 1);
        } else {
            chars.put(character, 1);
        }
    }

    public Map<Character, Integer> getChars() {
        return this.chars;
    }

    public void addInputExpression(String inputExpression) {
        this.inputExpression = inputExpression;
    }

    public int getMaxInput() {
        return Integer.parseInt("9".repeat(this.getChars().size()));
    }

    public Map<Character, Integer> createInput(int input) {
        Map<Character, Integer> resultMap = new HashMap<>(this.getChars());
        for (Map.Entry<Character, Integer> item : resultMap.entrySet()) {
            resultMap.put(item.getKey(), Math.max((input % 10), 0));
            input = input / 10;
        }
        return resultMap;
    }

    protected void checkSameValueExists(Map<Character, Integer> values) throws InputNotAcceptException {
        boolean[] exists = new boolean[10];
        for (Integer value : values.values()) {
            if (exists[value]) {
                throw new InputNotAcceptException("Duplicated value exists. Value is : " + value);
            } else {
                exists[value] = true;
            }
        }
    }
}
