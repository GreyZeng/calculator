package com.cp;


import com.cp.core.Expression;
import com.cp.core.impl.Generator;

import java.util.Set;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/22
 */
public class Application {
    public static void main(String[] args) {
        Set<Expression> expressions = new Generator().generate(Config.create(args));
        expressions.forEach(expression -> System.out.println(expression.getExpression()));

    }
}
