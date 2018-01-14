package com.cp.core;

import com.cp.ds.Expression;
import org.apache.commons.math3.fraction.Fraction;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

import static com.cp.ds.BinaryTree.infixToSuffix;
import static com.cp.ds.Constants.*;
import static java.lang.Integer.parseInt;

/**
 * @author zenghui
 * @date 2017/9/23
 */
public class Answer {

    public static void answer(Collection<Expression> expressions) {
        if (null != expressions) {
            for (Expression expression : expressions) {
                answer(expression);
            }
        }
    }

    public static void answer(Expression expression) {
        Stack<Fraction> s = new Stack();

        Fraction a;
        Fraction b;
        Fraction result = new Fraction(0, 1);
        List<String> suffix = infixToSuffix(expression.getExpression());
        if (null == suffix) {
            return;
        }
        boolean isNumber;
        for (String item : suffix) {
            try {
                isNumber = true;
                result = getFraction(item);
            } catch (Exception e) {
                isNumber = false;
            }

            if (isNumber) {
                s.push(result);
            } else {
                switch (item) {
                    case PLUS:
                        a = s.pop();
                        b = s.pop();
                        s.push(a.add(b));
                        break;
                    case MINUS:
                        a = s.pop();
                        b = s.pop();
                        s.push(b.subtract(a));
                        break;
                    case MULTIPLY:
                        a = s.pop();
                        b = s.pop();
                        s.push(b.multiply(a));
                        break;
                    case DIVIDE:
                        a = s.pop();
                        b = s.pop();
                        s.push(b.divide(a));
                        break;
                    default:
                        break;
                }
            }
        }
        expression.setFraction(s.peek());
        expression.setValue(s.peek().doubleValue());
    }

    private static Fraction getFraction(String item) {
        int result;
        if (item.indexOf(VIRGULE) > 0) {
            int attach = 0;
            String right;
            if (item.indexOf(SINGLE_QUOTE) > 0) {
                String[] parts = item.split(SINGLE_QUOTE);
                attach = parseInt(parts[0]);
                right = parts[1];
            } else {
                right = item;
            }
            String[] parts = right.split(VIRGULE);
            return Fraction.getReducedFraction(attach * parseInt(parts[1]) + parseInt(parts[0]), parseInt(parts[1]));
        } else {
            result = parseInt(item);
            return Fraction.getReducedFraction(result, 1);
        }
    }
}
