package com.hui.calculator.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneratorTest {

    @Test
    public void isDuplicate() {
        Assert.assertTrue(Generator.isDuplicate("1 + 2", "2 + 1"));
        Assert.assertTrue(Generator.isDuplicate("3 + ( 2 + 1 )", "1 + 2 + 3"));
        Assert.assertTrue(!Generator.isDuplicate("1 + 2 + 3", "3 + 2 + 1"));
        Assert.assertTrue(Generator.isDuplicate("23 + 45", "45 + 23"));
    }
}