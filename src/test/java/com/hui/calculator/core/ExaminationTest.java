package com.hui.calculator.core;

import com.google.gson.Gson;
import com.hui.calculator.models.Config;
import com.hui.calculator.models.Examination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExaminationTest {
    @Test
    public void testConvert() {
        Examination examination = new Examination();
        Config config = Config.create().answer(false).hasFraction(true).hasMultipleAndDivide(true)
                .hasNegative(false).hasParentheses(true).numberOfExpression(30).range(30).maxNumberOfOperation(3);
        examination.setConfig(config);
        Map<String,String> expressions = newHashMap();
        expressions.put("( 3 + 3 )","6");
        expressions.put("( 3 - 1 )","2");
        System.out.println(expressions.size());
        examination.setExpressions(expressions);
        Gson gson = new Gson();
        String plain = gson.toJson(examination);
        Examination convertedExamination = gson.fromJson(plain, Examination.class);
        System.out.println(plain);
        assertTrue(examination.equals(convertedExamination));
    }


}
