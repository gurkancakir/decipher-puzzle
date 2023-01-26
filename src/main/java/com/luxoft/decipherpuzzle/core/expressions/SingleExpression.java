package com.luxoft.decipherpuzzle.core.expressions;

import com.luxoft.decipherpuzzle.core.OperationFactory;
import com.luxoft.decipherpuzzle.core.entity.Element;
import com.luxoft.decipherpuzzle.core.entity.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SingleExpression extends AbstractExpression {

    protected List<Element> leftElements;
    protected List<Element> rightElements;

    @Autowired
    protected OperationFactory operationFactory;

    public SingleExpression() {
        this.clear();
    }

    private void clear() {
        this.chars = new HashMap<>();
        this.leftElements = new ArrayList<>();
        this.rightElements = new ArrayList<>();
    }

    public boolean check(Map<Character, Integer> values) {

        BigDecimal leftSide = execute(this.leftElements, values);
        BigDecimal rightSide = execute(this.rightElements, values);
        return leftSide.compareTo(rightSide) == 0;
    }

    public String show(Map<Character, Integer> values) {
        StringBuilder formula = new StringBuilder();
        foreach(leftElements, values, formula);
        formula.append("=");
        foreach(rightElements, values, formula);
        return formula.toString();
    }

    public void addLeftElement(Element element) {
        if (element instanceof Variable variable) {
            this.setFirstCharacter(variable.getFirstCharacter());
        }
        this.leftElements.add(element);
    }

    public void addRightElement(Element element) {
        if (element instanceof Variable variable) {
            this.setFirstCharacter(variable.getFirstCharacter());
        }
        this.rightElements.add(element);
    }

    protected void setFirstCharacter(Character character) {
        this.getChars().put(character, true);
    }

    @Override
    public Expression createExpression(String exp) {
        this.clear();
        this.addInputExpression(exp);
        String leftSide = this.findLeftSide(exp);
        String rightSide = this.findRightSide(exp);

        int factor = 1;
        Variable variable = new Variable();
        for (int i = leftSide.length() - 1; i >= 0; i--) {
            char chr = leftSide.charAt(i);
            if (chr == ' ') continue;
            if (isOperation(chr)) {
                this.addLeftElement(variable);
                this.addLeftElement(operationFactory.create(chr));
                variable = new Variable();
                factor = 1;
            } else {
                this.addChars(chr);
                variable.addVariable(new Variable.VariableItem(chr, factor));
                factor = factor * 10;
            }
        }
        this.addLeftElement(variable);

        variable = new Variable();
        factor = 1;
        for (int i = rightSide.length() - 1; i >= 0; i--) {
            char chr = rightSide.charAt(i);
            if (chr == ' ') continue;
            if (isOperation(chr)) {
                this.addRightElement(variable);
                this.addRightElement(operationFactory.create(chr));
                variable = new Variable();
                factor = 1;
            } else {
                this.addChars(chr);
                variable.addVariable(new Variable.VariableItem(chr, factor));
                factor = factor * 10;
            }
        }
        this.addRightElement(variable);

        return this;
    }

    @Override
    public boolean support(String expression) {
        return !expression.contains(";");
    }
}
