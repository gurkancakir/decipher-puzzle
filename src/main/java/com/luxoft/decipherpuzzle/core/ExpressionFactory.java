package com.luxoft.decipherpuzzle.core;

import com.luxoft.decipherpuzzle.core.expressions.Expression;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ExpressionFactory {

    private final List<Expression> expressionList;

    public Expression create(String expressions) {
        for (Expression expression : expressionList) {
            if (expression.support(expressions))
                return expression.createExpression(expressions);
        }
        return null;
    }
}
