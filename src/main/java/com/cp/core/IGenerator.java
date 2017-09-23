package com.cp.core;

import com.cp.Config;

import java.util.Set;

/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/23
 */
public interface IGenerator {
    // generate  exercise with answer
    Set<Expression> generate(Config config);
}
