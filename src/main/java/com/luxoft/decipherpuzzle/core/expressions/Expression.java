package com.luxoft.decipherpuzzle.core.expressions;

import com.luxoft.decipherpuzzle.core.entity.OperationType;
import com.luxoft.decipherpuzzle.core.exception.InputNotAcceptException;

import java.util.Map;

public interface Expression {

    Map<Character, Integer> createInput(int input);
    boolean check(Map<Character, Integer> values);
    String show(Map<Character, Integer> values) throws InputNotAcceptException;

    int getMaxInput();

    void addInputExpression(String expression);

    Expression createExpression(String exp);

    Map<Character, Integer> getChars();

    boolean support(String expression);

    default String findLeftSide(String exp) {
        return exp.split("=")[0];
    }

    default String findRightSide(String exp) {
        return exp.split("=")[1];
    }

    default boolean isOperation(Character chr) {
        for (OperationType value : OperationType.values()) {
            if (value.getType().equals(String.valueOf(chr)))
                return true;
        }
        return false;
    }

}
