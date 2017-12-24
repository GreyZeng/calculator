package com.cp;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * @author zenghui<4   1   0   4   8   6   0   4   7   @   qq.com>
 * @date 2017/9/22
 */
public class Config {
    @Parameter(names = {"-n", "-number"}, description = "number of expression")
    private int numberOfExpression = 10; // 生成表达式个数
    @Parameter(names = {"-r", "-range"}, description = "range of operand")
    private int range = 10; //控制题目中数值（自然数、真分数和真分数分母）的范围，该参数可以设置为1或其他自然数。该参数必须给定，否则程序报错并给出帮助信息
    @Parameter(names = "-hf", description = "has fraction or not", arity = 1)
    private boolean hasFraction = false; // 有无分数
    @Parameter(names = "-md", description = "has multiple or divide operation", arity = 1)
    private boolean hasMultipleAndDivide = false; // 是否有乘除法
    @Parameter(names = "-hp", description = "has parentheses or not", arity = 1)
    private boolean hasParentheses = false; // 是否有括号
    @Parameter(names = "-hn", description = "has negative or not", arity = 1)
    private boolean hasNegative = false; // 有无负数

    // 最大运算符数量 最多不能超过三个
    // @Parameter(names = "-mo", description = "max number of operation")
    private int maxNumberOfOperation = 3;

    private Config() {
    }

    public static Config create() {
        return new Config();
    }

    public static Config create(String[] args) {
        if (null == args) {
            return Config.create();
        }
        Config config = Config.create();
        // TODO handle arguments exception and show the error msg to user
        JCommander.newBuilder().addObject(config).build().parse(args);
        return config;
    }

    public Config maxNumberOfOperation(int maxNumberOfOperation) {
        this.maxNumberOfOperation = maxNumberOfOperation;
        return this;
    }

    public Config range(int range) {
        this.range = range;
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

    public void setRange(int range) {
        this.range = range;
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
                "------------------------------------------------------------------------------- ";
    }
}
