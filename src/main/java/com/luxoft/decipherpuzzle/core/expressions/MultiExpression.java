package com.luxoft.decipherpuzzle.core.expressions;


import com.luxoft.decipherpuzzle.core.exception.InputNotAcceptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MultiExpression extends AbstractExpression {

    private List<Expression> expressionList;

    @Autowired
    private ApplicationContext context;

    public MultiExpression() {
        this.clear();
    }

    public void clear() {
        this.expressionList = new ArrayList<>();
    }

    public void addExpression(Expression expression) {
        this.expressionList.add(expression);
    }

    @Override
    public boolean check(Map<Character, Integer> values) {
        boolean result = true;
        for (Expression e : this.expressionList) {
            result = result && e.check(values);
        }
        return result;
    }

    @Override
    public String show(Map<Character, Integer> values) throws InputNotAcceptException {
        StringBuilder builder = new StringBuilder();
        for (Expression e : this.expressionList) {
            builder.append(e.show(values));
            builder.append("  ");
        }
        return builder.toString();
    }

    @Override
    public Expression createExpression(String exp) {
        this.clear();
        this.addInputExpression(exp);
        String[] expressions = exp.split(";");
        for (String expression : expressions) {
            Expression ex = context.getBean(SingleExpression.class).createExpression(expression);
            this.addExpression(ex);
        }
        return this;
    }

    @Override
    public Map<Character, Integer> getChars() {
        Map<Character, Integer> map = new HashMap<>();
        for (Expression e : this.expressionList) {
            e.getChars().forEach((key, value) -> {
                if (!map.containsKey(key))
                    map.put(key, value);
            });

        }
        return map;
    }

    @Override
    public boolean support(String expression) {
        return expression.contains(";");
    }
}
