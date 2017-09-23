package com.cp.core.impl;

import com.cp.Config;
import com.cp.core.Expression;
import com.cp.core.IGenerator;
import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.base.Objects.equal;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.StringUtils.SPACE;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/23
 */
public class Generator implements IGenerator {

    @Override
    public Set<Expression> generate(Config config) {

        System.out.println(config);
        System.out.println("Start generate expression...\n");
        int numberOfExpression = config.getNumberOfExpression();
        Set<Expression> set = newHashSet();
        boolean isHasFraction = config.isHasFraction();

        for (int i = 0; i < numberOfExpression; i++) {
            // number of operation could be from 0 to maxNum
            int randomNumberOfOperation = ThreadLocalRandom.current().nextInt(0, config.getMaxNumberOfOperation() + 1);

            if (randomNumberOfOperation == 0) {
                set.add(Expression.create(generateOprand(isHasFraction, config.getRange())));
                continue;
            }
            int markFraction = 0;
            if (isHasFraction) {
                markFraction = ThreadLocalRandom.current().nextInt(0, randomNumberOfOperation + 1) * 2;
            }
            int numberOfOpand = randomNumberOfOperation + 1;
            String[] exp = new String[randomNumberOfOperation + numberOfOpand];
            for (int j = 0; j < randomNumberOfOperation + numberOfOpand; j++) {
                if (j % 2 == 0) {
                    if (j == markFraction && isHasFraction) {
                        exp[j] = generateOprand(true, config.getRange());
                    } else {
                        exp[j] = generateOprand(randomFlag(isHasFraction), config.getRange());
                    }
                    if (j > 0 && equal(exp[j - 1], "÷") && equal(exp[j], "0")) {
                        // could not be ÷ 0 , re generator operator from + - x
                        exp[j - 1] = pickAnOperation(config.generateAvailableOperators(), "÷");
                    }
                } else {
                    exp[j] = pickAnOperation(config.generateAvailableOperators());
                }
            }
            set.add(Expression.create(Joiner.on(SPACE).join(exp)));
        }
        return set;
    }

    private boolean randomFlag(boolean isHasFraction) {
        return isHasFraction ? new Random().nextBoolean() : false;
    }

    private String pickAnOperation(String[] operators, String except) {
        String[] newOperators = Arrays.stream(operators).filter(operator -> !equal(operator, except)).toArray(String[]::new);
        return pickAnOperation(newOperators);
    }

    private String pickAnOperation(String[] operators) {
        return operators[ThreadLocalRandom.current().nextInt(0, operators.length)];
    }

    private String generateOprand(boolean isFraction, int range) {
        return isFraction ? generateAnFraction(range) : String.valueOf(ThreadLocalRandom.current().nextInt(0, range));
    }

    private String generateAnFraction(int range) {
        int denominator = ThreadLocalRandom.current().nextInt(2, range + 1);
        int numerator = ThreadLocalRandom.current().nextInt(1, denominator);
        int leftInteger = ThreadLocalRandom.current().nextInt(0, range);
        String fraction;
        int maxMultiple = gcd(denominator, numerator);
        denominator = denominator / maxMultiple;
        numerator = numerator / maxMultiple;
        if (leftInteger == 0) {
            fraction = String.valueOf(numerator) + "/" + String.valueOf(denominator);
        } else {
            fraction = String.valueOf(leftInteger) + "'" + String.valueOf(numerator) + "/" + String.valueOf(denominator);
        }
        return fraction;
    }

    private int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

}
