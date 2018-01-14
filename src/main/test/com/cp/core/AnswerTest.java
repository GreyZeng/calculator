package com.cp.core;

import com.cp.models.Expression;
import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {

    @Test
    public void answer() {
        String exp = "3 +  5 × 3  -  3 ÷ 4 ";
        Expression expression = Expression.create(exp);
        Answer.answer(expression);
        Assert.assertEquals(17.25f, expression.getValue(), 2);
    }
}