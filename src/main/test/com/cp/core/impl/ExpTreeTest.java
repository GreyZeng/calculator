package com.cp.core.impl;

import com.cp.core.ExpTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/24
 */
public class ExpTreeTest {
    @Test
    public void testCompareTree() throws Exception {
        ExpTree tree = new ExpTree();
        tree.insert("-");
        tree.insert("5");
        tree.insert("+");
        tree.insert("8");
        tree.insert("4");

        ExpTree tree3 = new ExpTree();
        tree3.insert("-");
        tree3.insert("5");
        tree3.insert("+");

        tree3.insert("4");
        tree3.insert("8");

        Assert.assertEquals(tree3.generateExpression(), tree.generateExpression());
    }

}
