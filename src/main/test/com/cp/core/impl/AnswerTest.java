package com.cp.core.impl;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/23
 */
public class AnswerTest {
    @Test
    public void answer() throws Exception {
        Assert.assertEquals(new Answer().answer("( 1 + ( ( 3 - 1 ) × ( 8 ÷ 2 ) ) )").getValue(), 9d, 0);
    }

}