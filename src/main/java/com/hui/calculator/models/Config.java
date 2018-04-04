package com.hui.calculator.models;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

/**
 * @author zenghui
 * @date 2017/9/22
 */
public class Config {
    /**
     * 生成表达式个数
     */
    private int numberOfExpression = 10;
    /**
     * 控制题目中数值（自然数、真分数和真分数分母）的范围，该参数可以设置为1或其他自然数。该参数必须给定，否则程序报错并给出帮助信息
     */
    private int range = 10;
    /**
     * 有无分数
     */
    private boolean hasFraction = false;
    /**
     * 是否有乘除法
     */
    private boolean hasMultipleAndDivide = true;
    /**
     * 是否有括号
     */
    private boolean hasParentheses = false;
    /**
     * 有无负数 TODO
     */
    private boolean hasNegative = true;
    private boolean answer = true;

    /**
     * 最大运算符数量 至少1个，目前最多不能超过3个 TODO: 后续扩展多个
     * 配置：@Parameter(names = "-mo", description = "max number of operation")
     */
    private int maxNumberOfOperation = 3;

    private Config() {
    }

    public static Config create() {
        return new Config();
    }



    public static Config create(String config) {
        if (StringUtils.isEmpty(config)) {
            return Config.create();
        } else {
            Gson gson = new Gson();
            Map<String, String> map = gson.fromJson(config, Map.class);
            // TODO handler exception
            return Config.create().hasNegative(parseBoolean(map.get("hasNegative")))
                    .hasParentheses(parseBoolean(map.get("hasParentheses")))
                    .hasMultipleAndDivide(parseBoolean(map.get("hasMultipleAndDivide")))
                    .hasFraction(parseBoolean(map.get("hasFraction")))
                    .numberOfExpression(parseInt(map.get("numberOfExpression")))
                    .range(parseInt(map.get("range")))
                    .answer(parseBoolean(map.get("answer")));
        }
    }


    public Config maxNumberOfOperation(int maxNumberOfOperation) {
        this.maxNumberOfOperation = maxNumberOfOperation;
        return this;
    }

    public Config range(int range) {
        this.range = range;
        return this;
    }

    public Config answer(boolean answer) {
        this.answer = answer;
        return this;
    }

    public Config numberOfExpression(int numberOfExpression) {
        this.numberOfExpression = numberOfExpression;
        return this;
    }

    public Config hasFraction(boolean hasFraction) {
        this.hasFraction = hasFraction;
        return this;
    }

    public Config hasMultipleAndDivide(boolean hasMultipleAndDivide) {
        this.hasMultipleAndDivide = hasMultipleAndDivide;
        return this;
    }

    public Config hasParentheses(boolean hasParentheses) {
        this.hasParentheses = hasParentheses;
        return this;
    }

    public Config hasNegative(boolean hasNegative) {
        this.hasNegative = hasNegative;
        return this;
    }

    public int getMaxNumberOfOperation() {
        return maxNumberOfOperation;
    }

    public void setMaxNumberOfOperation(int maxNumberOfOperation) {
        this.maxNumberOfOperation = maxNumberOfOperation;
    }

    public boolean isHasFraction() {
        return hasFraction;
    }

    public void setHasFraction(boolean hasFraction) {
        this.hasFraction = hasFraction;
    }

    public boolean isHasMultipleAndDivide() {
        return hasMultipleAndDivide;
    }

    public void setHasMultipleAndDivide(boolean hasMultipleAndDivide) {
        this.hasMultipleAndDivide = hasMultipleAndDivide;
    }

    public boolean isHasParentheses() {
        return hasParentheses;
    }

    public void setHasParentheses(boolean hasParentheses) {
        this.hasParentheses = hasParentheses;
    }

    public boolean isHasNegative() {
        return hasNegative;
    }

    public void setHasNegative(boolean hasNegative) {
        this.hasNegative = hasNegative;
    }


    public int getNumberOfExpression() {
        return numberOfExpression;
    }

    public void setNumberOfExpression(int numberOfExpression) {
        this.numberOfExpression = numberOfExpression;
    }

    public int getRange() {
        return range;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "------------------------------------Config-------------------------------------" + "\n" +
                "number of expression(-n):" + numberOfExpression + "\n" +
                "range of operand(-r):" + range + "\n" +
                "max number of operation(-mo):" + maxNumberOfOperation + "\n" +
                "has fraction?(-hf):" + hasFraction + "\n" +
                "has multiple or divide operation?(-md):" + hasMultipleAndDivide + "\n" +
                "has parentheses?(-hp):" + hasParentheses + "\n" +
                "has negative?(-hn):" + hasNegative + "\n" +
                "with answer?(-as):" + answer + "\n" +
                "------------------------------------------------------------------------------- ";
    }
}
