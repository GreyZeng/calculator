package com.cp.core;

import com.cp.ds.Expression;

import java.util.List;
import java.util.Stack;

import static com.cp.ds.BinaryTree.infixToSuffix;
import static java.lang.Double.parseDouble;

/**
 * @author zenghui
 * @date 2017/9/23
 */
public class Answer {


    public static void answer(Expression expression) {
        Stack<Double> s = new Stack<>();
        Double a, b, result = 0.0;
        List<String> suffix = infixToSuffix(expression.getExpression());
        boolean isNumber;
        for (String item : suffix) {
            try {
                isNumber = true;
                result = parseDouble(item);
            } catch (Exception e) {
                isNumber = false;
            }

            if (isNumber) {
                s.push(result);
            } else {
                switch (item) {
                    case "+":
                        a = s.pop();
                        b = s.pop();
                        s.push(b + a);
                        break;
                    case "-":
                        a = s.pop();
                        b = s.pop();
                        s.push(b - a);
                        break;
                    case "×":
                        a = s.pop();
                        b = s.pop();
                        s.push(b * a);
                        break;
                    case "÷":
                        a = s.pop();
                        b = s.pop();
                        s.push(b / a);
                        break;
                }
            }
        }
        expression.setValue(s.peek());
    }
}
