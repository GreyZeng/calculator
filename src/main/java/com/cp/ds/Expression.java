package com.cp.ds;

import org.apache.commons.math3.fraction.Fraction;

/**
 * @author zenghui
 * @date 2017/9/23
 */
public class Expression {
    private String expression;
    private double value;
    private Fraction fraction;

    private Expression(String expression) {
        this.expression = expression;
    }

    public static Expression create(String expression) {
        return new Expression(expression);
    }

    public Expression expression(String expression) {
        this.expression = expression;
        return this;
    }

    public Expression value(double value) {
        this.value = value;
        return this;
    }

    public Expression fraction(Fraction fraction) {
        this.fraction = fraction;
        return this;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Fraction getFraction() {
        return fraction;
    }

    public void setFraction(Fraction fraction) {
        this.fraction = fraction;
    }

    @Override
    public String toString() {
        return this.expression + " = " + this.value;
    }
}
