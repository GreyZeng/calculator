package com.cp.core;

import com.cp.ds.BinaryTree;
import com.cp.ds.Config;
import com.cp.ds.Expression;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.cp.ds.BinaryTree.generateExpression;
import static com.google.common.base.Objects.equal;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.StringUtils.SPACE;

/**
 * @author zenghui
 * @date 2017/9/23
 */
public class Generator {

    public static Set<Expression> generate(Config config) {

        System.out.println(config);
        System.out.println("Start generate expression...\n");
        int numberOfExpression = config.getNumberOfExpression();
        Set<Expression> set = newHashSet();
        boolean isHasFraction = config.isHasFraction();

        for (int i = 0; i < numberOfExpression; i++) {

            int randomNumberOfOperation = ThreadLocalRandom.current().nextInt(1, config.getMaxNumberOfOperation() + 1);

            int markFraction = 0;
            if (isHasFraction) {
                // isHasFraction == true 说明至少有一个分数，标识一下分数所在的位置
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
                        exp[j - 1] = pickAnOperation(generateAvailableOperators(config.isHasMultipleAndDivide()), "÷");
                    }
                } else {
                    exp[j] = pickAnOperation(generateAvailableOperators(config.isHasMultipleAndDivide()));
                }
            }
            String expression = Joiner.on(SPACE).join(exp);
            if (hasDulplicate(set, expression)) {
                i--;
            } else {
                set.add(Expression.create(expression));
            }

        }
        return set;
    }

    private static boolean hasDulplicate(Set<Expression> expressions, String expression) {
        for (Expression item : expressions) {
            if (isDulplicate(item.getExpression(), expression)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDulplicate(String expression1, String expression2) {
        if (equal(generateExpression(BinaryTree.create(expression1).getRoot()),
                generateExpression(BinaryTree.create(expression2).getRoot()))) {
            return true;
        }
        return false;
    }
    public static String[] generateAvailableOperators(boolean hasMultipleAndDivide) {
        if (hasMultipleAndDivide) {
            return new String[]{"+", "-", "×", "÷"};
        }
        return new String[]{"+", "-"};
    }

    private static boolean randomFlag(boolean isHasFraction) {
        return isHasFraction ? new Random().nextBoolean() : false;
    }

    private static String pickAnOperation(String[] operators, String except) {
        String[] newOperators = Arrays.stream(operators).filter(operator -> !equal(operator, except)).toArray(String[]::new);
        return pickAnOperation(newOperators);
    }

    private static String pickAnOperation(String[] operators) {
        return operators[ThreadLocalRandom.current().nextInt(0, operators.length)];
    }

    private static String generateOprand(boolean isFraction, int range) {
        return isFraction ? generateAnFraction(range) : String.valueOf(ThreadLocalRandom.current().nextInt(0, range));
    }

    private static String generateAnFraction(int range) {
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

    private static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

}
