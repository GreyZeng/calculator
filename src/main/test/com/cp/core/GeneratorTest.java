package com.cp.core;

import org.junit.Assert;
import org.junit.Test;

public class GeneratorTest {

    @Test
    public void isDuplicate() {
        Assert.assertTrue(Generator.isDuplicate("( 1 + 2 ) + 3", "3 + ( 1 + 2 )"));
        Assert.assertTrue(Generator.isDuplicate("3 + ( 2 + 1 )", "1 + 2 + 3"));
        Assert.assertTrue(!Generator.isDuplicate("1 + 2 + 3", "3 + 2 + 1"));
        Assert.assertTrue(Generator.isDuplicate("23 + 45", "45 + 23"));
    }
}