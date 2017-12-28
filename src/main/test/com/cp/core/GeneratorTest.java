package com.cp.core;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void isDulplicate() {
        Assert.assertTrue(Generator.isDulplicate("( 1 + 2 ) + 3", "3 + ( 1 + 2 )"));
    }
}