package com.cp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/22
 */

public class ConfigTest {

    @Test
    public void testCreateDefaultConfig() {
        Config config = Config.create();
        assertEquals(10, config.getNumberOfExpression());
        assertEquals(10, config.getRange());
        assertEquals(3, config.getMaxNumberOfOperation());
        assertEquals(false, config.isHasFraction());
        assertEquals(false, config.isHasMultipleAndDivide());
        assertEquals(false, config.isHasParentheses());
        assertEquals(false, config.isHasNegative());
    }

    @Test
    public void testCreateCustomerConfig() {
        String[] args = {"-n", "1000", "-r", "112", "-hf", "true", "-mo", "111", "-md", "true", "-hn", "true", "-hp", "true"};
        Config config = Config.create(args);
        assertEquals(1000, config.getNumberOfExpression());
        assertEquals(112, config.getRange());
        assertEquals(111, config.getMaxNumberOfOperation());
        assertEquals(true, config.isHasFraction());
        assertEquals(true, config.isHasMultipleAndDivide());
        assertEquals(true, config.isHasParentheses());
        assertEquals(true, config.isHasNegative());
    }
}
