package com.cp;


import com.cp.core.Answer;
import com.cp.ds.Config;
import com.cp.ds.Expression;

import java.util.Set;

import static com.cp.core.Generator.generate;

/**
 * @author zenghui
 * @date 2017/9/22
 */
public class Application {
    public static void main(String[] args) {
        Set<Expression> expressions = generate(Config.create(args));
        Answer.answer(expressions);
        expressions.forEach(expression -> System.out.println(expression));
    }
}
