package com.hui.calculator.core;


import com.hui.calculator.models.Expression;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerTest {

    @Test
    public void answer() throws Exception {
        String exp = "3 +  5 × 3  +  2 ÷ 4 ";
        Expression expression = Expression.create(exp);
        Answer.answer(expression);
        Assert.assertEquals(17.25f, expression.getValue(), 2);
    }
}