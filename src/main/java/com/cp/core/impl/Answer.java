package com.cp.core.impl;

import com.cp.core.Expression;
import org.apache.commons.lang3.StringUtils;

import java.util.Stack;

import static com.google.common.base.Objects.equal;
import static java.lang.Double.parseDouble;
import static org.apache.commons.lang3.StringUtils.SPACE;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/23
 */
public class Answer {
    public Expression answer(String expression) {
        if (StringUtils.isEmpty(expression)) {
            // TODO handle Exception
            return null;
        }
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        String[] exprs = expression.split(SPACE);

        for (String op : exprs) {
            if (equal(op, "(")) {
                continue;
            } else if (equal(op, "+")) {
                ops.push(op);
            } else if (equal(op, "-")) {
                ops.push(op);
            } else if (equal(op, "×")) {
                ops.push(op);
            } else if (equal(op, "÷")) {
                ops.push(op);
            } else if (equal(op, ")")) {
                String lastOp = ops.pop();
                double val = vals.pop();
                if (equal(lastOp, "+")) {
                    val = vals.pop() + val;
                }
                if (equal(lastOp, "-")) {
                    val = vals.pop() - val;
                }
                if (equal(lastOp, "×")) {
                    val = vals.pop() * val;
                }
                if (equal(lastOp, "÷")) {
                    val = vals.pop() / val;
                }
                vals.push(val);
            } else {
                vals.push(parseDouble(op));
            }
        }
        return Expression.create(expression).value(vals.pop());
    }
}
