package com.cp.core.impl;

import com.cp.Config;
import com.cp.core.Expression;
import com.cp.core.IGenerator;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/23
 */
public class Generator implements IGenerator {

    @Override
    public Set<Expression> generate(Config config) {
        // prepare available operators
        Set<String> operators = generateAvailableOperators(config.isHasMultipleAndDivide());
        // TODO
        return newHashSet();
    }

    private Set<String> generateAvailableOperators(boolean hasMultipleAndDivide) {
        Set<String> operators = newHashSet();
        operators.add("+");
        operators.add("-");
        if (hasMultipleAndDivide) {
            operators.add("×");
            operators.add("÷");
        }
        return operators;
    }


}
