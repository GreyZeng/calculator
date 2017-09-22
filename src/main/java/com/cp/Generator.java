package com.cp;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

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
        // TODO
        return Expression.create(expression);
    }
}
