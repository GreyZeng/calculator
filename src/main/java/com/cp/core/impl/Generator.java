package com.cp.core.impl;

import com.cp.Config;
import com.cp.core.Expression;
import com.cp.core.IGenerator;
import com.google.common.base.Joiner;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.StringUtils.SPACE;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/23
 */
public class Generator implements IGenerator {

    @Override
    public Set<Expression> generate(Config config) {

        int numberOfExpression = config.getNumberOfExpression();
        Set<Expression> set = newHashSet();
        for (int i = 0; i < numberOfExpression; i++) {
            // number of operation could be from 0 to maxNum
            int randomNumberOfOperation = ThreadLocalRandom.current().nextInt(0, config.getMaxNumberOfOperation() + 1);
            if (randomNumberOfOperation == 0) {
                set.add(Expression.create(generateOprand(config.isHasFraction(), config.getRange())));
                continue;
            }
            int numberOfOpand = randomNumberOfOperation + 1;
            String[] exp = new String[randomNumberOfOperation + numberOfOpand];
            for (int j = 0; j < randomNumberOfOperation + numberOfOpand; j++) {
                if (j % 2 == 1) {
                    exp[j] = pickAnOperation(config.generateAvailableOperators());
                } else {
                    exp[j] = generateOprand(config.isHasFraction(), config.getRange());
                }
            }
            set.add(Expression.create(Joiner.on(SPACE).join(exp)));
        }
        return set;
    }

    private String pickAnOperation(String[] operators) {
        return operators[ThreadLocalRandom.current().nextInt(0, operators.length)];
    }

    private String generateOprand(boolean hasFraction, int range) {
        // TODO handle fraction
        return String.valueOf(ThreadLocalRandom.current().nextInt(0, range + 1));
    }
}
