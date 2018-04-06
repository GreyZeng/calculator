package com.hui.calculator.core;


import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.hui.calculator.models.BinaryTree;
import com.hui.calculator.models.Config;
import com.hui.calculator.models.Expression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.base.Objects.equal;
import static com.google.common.collect.Lists.newArrayList;
import static com.hui.calculator.models.BinaryTree.generateExpression;
import static com.hui.calculator.models.Constants.*;
import static org.apache.commons.lang3.StringUtils.SPACE;

/**
 * @author zenghui
 * @date 2017/9/23
 */
public class Generator {
    private static final Logger logger = LogManager.getLogger(Generator.class);


    public static Set<Expression> generate(Config config) {

        logger.debug("start generate expressions, config is {}", config);
        int numberOfExpression = config.getNumberOfExpression();
        Set<Expression> set = Sets.newConcurrentHashSet();
        for (int i = 0; i < numberOfExpression; i++) {
            int randomNumberOfOperation = ThreadLocalRandom.current().nextInt(1, config.getMaxNumberOfOperation() + 1);
            boolean isHasFraction = config.isHasFraction();
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
                    if (j > 0 && equal(exp[j - 1], DIVIDE) && equal(exp[j], ZERO)) {
                        // could not be ÷ 0 , re generator operator from + - x
                        exp[j - 1] = pickAnOperation(generateAvailableOperators(config.isHasMultipleAndDivide()), DIVIDE);
                    }
                } else {
                    exp[j] = pickAnOperation(generateAvailableOperators(config.isHasMultipleAndDivide()));
                }
            }
            String expression;
            if (config.isHasParentheses()) {
                expression = Joiner.on(SPACE).join(markParentheses(exp));
            } else {
                expression = Joiner.on(SPACE).join(exp);
            }

            if (!hasDuplicate(set, expression)) {
                set.add(Expression.create(expression));
            }
        }

        if (config.getAnswer()) {
            Answer.answer(set);
        }
        return set;
    }

    public static List<String> markParentheses(String[] exp) {
        List<String> expression = newArrayList();
        if (null != exp) {
            int length = exp.length;
            int leftPosition = ThreadLocalRandom.current().nextInt(0, (length / 2));
            int rightPosition = ThreadLocalRandom.current().nextInt(leftPosition + 1, (length / 2) + 1);
            int mark = -1;
            for (int i = 0; i < length; i++) {
                if (isOperator(exp[i])) {
                    expression.add(exp[i]);
                } else {
                    mark++;
                    if (mark == leftPosition) {
                        expression.add(LEFT_PARENTHESES);
                        expression.add(exp[i]);
                    } else if (mark == rightPosition) {
                        expression.add(exp[i]);
                        expression.add(RIGHT_PARENTHESES);
                    } else {
                        expression.add(exp[i]);
                    }
                }
            }
        }
        return expression;
    }

    private static boolean isOperator(String item) {
        switch (item) {
            case PLUS:
                return true;
            case MINUS:
                return true;
            case MULTIPLY:
                return true;
            case DIVIDE:
                return true;
            default:
                return false;
        }
    }

    public static boolean hasDuplicate(Set<Expression> expressions, String expression) {
        for (Expression item : expressions) {
            if (isDuplicate(item.getExpression(), expression)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDuplicate(String expression1, String expression2) {
        if (equal(generateExpression(BinaryTree.create(expression1).getRoot()),
                generateExpression(BinaryTree.create(expression2).getRoot()))) {
            return true;
        }
        return false;
    }

    public static String[] generateAvailableOperators(boolean hasMultipleAndDivide) {
        if (hasMultipleAndDivide) {
            return new String[]{PLUS, MINUS, MULTIPLY, DIVIDE};
        }
        return new String[]{PLUS, MINUS};
    }

    public static boolean randomFlag(boolean isHasFraction) {
        return isHasFraction ? new Random().nextBoolean() : false;
    }

    public static String pickAnOperation(String[] operators, String except) {
        String[] newOperators = Arrays.stream(operators).filter(operator -> !equal(operator, except)).toArray(String[]::new);
        return pickAnOperation(newOperators);
    }

    public static String pickAnOperation(String[] operators) {
        return operators[ThreadLocalRandom.current().nextInt(0, operators.length)];
    }

    public static String generateOprand(boolean isFraction, int range) {
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
            fraction = String.valueOf(numerator) + VIRGULE + String.valueOf(denominator);
        } else {
            fraction = String.valueOf(leftInteger) + SINGLE_QUOTE + String.valueOf(numerator) + VIRGULE + String.valueOf(denominator);
        }
        return fraction;
    }

    private static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

}



