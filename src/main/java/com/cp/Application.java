package com.cp;


import com.cp.ds.Config;
import com.cp.ds.Expression;
import com.cp.core.Generator;

import java.util.Set;

/**
 * @author zenghui
 * @date 2017/9/22
 */
public class Application {
    public static void main(String[] args) {
        Set<Expression> expressions = Generator.generate(Config.create(args));
        expressions.forEach(expression -> System.out.println(expression.getExpression()));
    }
}
