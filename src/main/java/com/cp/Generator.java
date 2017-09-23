package com.cp;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Set;
import java.util.Stack;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.StringUtils.SPACE;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/23
 */
public class Generator implements IGenerator {

    @Override
    public Set<Expression> generate(Config config) {
        // TODO
        System.out.println(config);
        System.out.println("Start to generate...");
        return newHashSet();
    }

    @Override
    public Expression answer(String expression) {

        if (StringUtils.isEmpty(expression)) {
            // TODO handle Exception
            return null;
        }
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        String[] exprs = expression.split(SPACE);

        for (String op : exprs) {
            if (Objects.equals(op, "(")) {
                continue;
            } else if (Objects.equals(op, "+")) {
                ops.push(op);
            } else if (Objects.equals(op, "-")) {
                ops.push(op);
            } else if (Objects.equals(op, "×")) {
                ops.push(op);
            } else if (Objects.equals(op, "÷")) {
                ops.push(op);
            } else if (Objects.equals(op, ")")) {
                String lastOp = ops.pop();
                double val = vals.pop();
                if (Objects.equals(lastOp, "+")) {
                    val = vals.pop() + val;
                }
                if (Objects.equals(lastOp, "-")) {
                    val = vals.pop() - val;
                }
                if (Objects.equals(lastOp, "×")) {
                    val = vals.pop() * val;
                }
                if (Objects.equals(lastOp, "÷")) {
                    val = vals.pop() / val;
                }
                vals.push(val);
            } else {
                vals.push(Double.parseDouble(op));
            }
        }
        return Expression.create(expression).value(vals.pop());
    }

}
