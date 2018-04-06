package com.hui.calculator.core;

import com.google.common.base.Joiner;
import com.hui.calculator.models.Config;
import com.hui.calculator.models.Expression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.base.Objects.equal;
import static com.google.common.collect.Sets.newConcurrentHashSet;
import static com.hui.calculator.core.Generator.*;
import static com.hui.calculator.core.Generator.hasDuplicate;
import static com.hui.calculator.models.Constants.DIVIDE;
import static com.hui.calculator.models.Constants.ZERO;
import static org.apache.commons.lang3.StringUtils.SPACE;


/**
 * @author zenghui
 */
public class GenerateTask implements Runnable {
    private static final Logger logger = LogManager.getLogger(GenerateTask.class);
    CountDownLatch countDownLatch;
    Set<Expression> set;
    Config config;

    public GenerateTask(Set<Expression> data, Config config, CountDownLatch countDownLatch) {
        this.set = data;
        this.config = config;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

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
            countDownLatch.countDown();



    }

}
