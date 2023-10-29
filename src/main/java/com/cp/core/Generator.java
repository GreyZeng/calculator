package com.cp.core;

import com.cp.models.BinaryTree;
import com.cp.models.Config;
import com.cp.models.Expression;
import com.google.common.base.Joiner;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.cp.models.BinaryTree.generateExpression;
import static com.cp.models.Constants.*;
import static com.google.common.base.Objects.equal;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.StringUtils.SPACE;

/**
 * @author zenghui
 * @date 2017/9/23
 */
public class Generator {

    public static String formatExpression(Set<Expression> expressions) {
        if (CollectionUtils.isEmpty(expressions)) {
            return null;
        } else {
            StringBuilder sbuilder = new StringBuilder();
            for (Expression expression : expressions) {
                sbuilder.append(expression.toString()).append("\n");
            }

            return sbuilder.toString();
        }
    }
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
            if (hasDuplicate(set, expression)) {
                i--;
                continue;
            } else {
                set.add(Expression.create(expression));
            }


        }
        if (config.getAnswer()) {
            Answer.answer(set);
        }
        return set;
    }

    private static List<String> markParentheses(String[] exp) {
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

    private static boolean hasDuplicate(Set<Expression> expressions, String expression) {
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