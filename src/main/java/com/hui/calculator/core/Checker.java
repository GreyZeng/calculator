package com.hui.calculator.core;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.hui.calculator.models.Config;
import com.hui.calculator.models.Evaluate;
import com.hui.calculator.models.Examination;
import com.hui.calculator.models.Expression;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.fraction.Fraction;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;
import static com.hui.calculator.models.Constants.*;

/**
 * @author zenghui
 */
public class Checker {
    public static String getCheckedInfo(String answer) throws Exception {
        Examination examination = getExamination(answer);
        return check(examination);
    }

    private static String check(Examination examination) throws Exception {
        Config config = examination.getConfig();
        Map<String,String> expressions = examination.getExpressions();
        return check(expressions, config);
    }

    private static String check(Map<String,String> expressions, Config config) throws Exception {
        Evaluate evaluate = new Evaluate();
        evaluate.setConfig(config);
        List<Map<String, Map<Boolean, String>>> results = newArrayList();
        evaluate.setEnough(expressions.size() == config.getNumberOfExpression());


        Set<Expression> unique = newHashSet();
        for (Map.Entry<String, String> entry : expressions.entrySet()) {
            if (!Generator.hasDuplicate(unique, entry.getKey())) {
                unique.add(Expression.create(entry.getKey()));
                Map<String, Map<Boolean, String>> result = null;
                result = check(entry.getKey(),entry.getValue(), config);
                results.add(result);
            } else {
                results.add(failReason(entry.getKey(), "包含重复的表达式"));
            }
        }

        evaluate.setResults(results);
        return new Gson().toJson(evaluate);
    }

    private static Map<String, Map<Boolean, String>> check(String expression, String answer, Config config) throws Exception {
        // 待重构 TODO
        if (StringUtils.isEmpty(expression)) {
            return failReason(expression, "不合法的表达式");
        }
        String[] elements = expression.split(" ");

        if (elements == null || elements.length < 3) {
            return failReason(expression, "不合法的表达式");
        }
        if (!checkAnswer(expression,answer)) {
            return failReason(expression, "计算结果不符合要求");
        }
        if (!checkRange(elements, config.getRange())) {
            return failReason(expression, "题目中数值不符合要求");
        }
        if (!checkParentheses(expression, config.isHasParentheses())) {
            return failReason(expression, "题目中的括号不符合要求");
        }
        if (!checkMultipleAndDivide(expression, config.isHasMultipleAndDivide())) {
            return failReason(expression, "题目中的乘除法不符合要求");
        }
        if (!checkFraction(elements, config.isHasFraction())) {
            return failReason(expression, "题目中的分数不符合要求");
        }
        return approve(expression);
    }

    private static boolean checkFraction(String[] elements, boolean hasFraction) {
        for (String element : elements) {
            if (element.indexOf(VIRGULE) > 0 || element.indexOf(SINGLE_QUOTE) > 0) {
                if (hasFraction) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        if (hasFraction) {
            return false;
        } else {
            return true;
        }

    }

    private static boolean checkParentheses(String expression, boolean hasParentheses) {
        if (hasParentheses) {
            if (expression.indexOf( LEFT_PARENTHESES) >= 0 && expression.indexOf( RIGHT_PARENTHESES) > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (expression.indexOf( LEFT_PARENTHESES) < 0 && expression.indexOf( RIGHT_PARENTHESES) < 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static boolean checkMultipleAndDivide(String expression, boolean hasMultipleAndDivide) {



        if (hasMultipleAndDivide) {
            if (expression.indexOf( MULTIPLY) >= 0 || expression.indexOf( DIVIDE) > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (expression.indexOf( MULTIPLY) < 0 && expression.indexOf( DIVIDE) < 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static boolean checkAnswer(String expression,String answer) {

        try {
            return Objects.equal(format(Double.parseDouble(answer)), format(Answer.answer(Expression.create(expression))));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String format(double value) {

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }
    private static boolean checkRange(String[] elements, int range) {
        Fraction base = Fraction.getReducedFraction(range, 1);
        for (String element : elements) {
            try {
                Fraction result = Answer.parseToFraction(element);
                if (result.compareTo(base) >= 0) {
                    return false;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return true;
    }


    private static Map<String, Map<Boolean, String>> approve(String expression) {
        Map<String, Map<Boolean, String>> result = newHashMap();
        Map<Boolean, String> reason = newHashMap();
        reason.put(true, "符合要求的表达式");
        result.put(expression, reason);
        return result;
    }

    private static Map<String, Map<Boolean, String>> failReason(String expression, String msg) {
        Map<String, Map<Boolean, String>> result = newHashMap();
        Map<Boolean, String> reason = newHashMap();
        reason.put(false, msg);
        result.put(expression, reason);
        return result;
    }

    private static Examination getExamination(String answer) throws Exception {
        Examination examination = new Gson().fromJson(answer, Examination.class);
        return examination;
    }

    public static String errorResult(String error) {
        Map<String, String> result = newHashMap();
        result.put(RET_CODE, ERROR);
        result.put(RESULT, "表达式解析有误：" + error);
        return new Gson().toJson(result);
    }

}
