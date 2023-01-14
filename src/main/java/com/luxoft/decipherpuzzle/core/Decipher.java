package com.luxoft.decipherpuzzle.core;

import com.luxoft.decipherpuzzle.core.expressions.Expression;
import com.luxoft.decipherpuzzle.core.exception.InputNotAcceptException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class Decipher {

    private final ExpressionFactory expressionFactory;

    public List<String> decode(String formula) throws InputNotAcceptException {

        List<String> possibleResult = new ArrayList<>();
        Expression expression = expressionFactory.create(formula);
        int start = expression.getMaxInput();
        for (int i = start; i >= 0; i--) {
            boolean isSuccess = checkExpression(expression, i);
            if (isSuccess) {
                String result = expression.show(expression.createInput(i));
                System.out.println(result);
                possibleResult.add(result);
            }
        }
        return possibleResult;
    }

    private boolean checkExpression(Expression expression, int i) {
        Map<Character, Integer> inputMap = expression.createInput(i);
        return expression.check(inputMap);
    }

}
