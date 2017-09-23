package com.cp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/23
 */
public class GeneratorTest {
    @Test
    public void answer() throws Exception {
        Assert.assertEquals(new Generator().answer("( 1 + ( ( 3 - 1 ) × ( 8 ÷ 2 ) ) )").getValue(), 9d, 0);
    }

}