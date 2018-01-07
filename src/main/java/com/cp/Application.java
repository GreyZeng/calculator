package com.cp;


import com.cp.core.Answer;
import com.cp.ds.Config;
import com.cp.ds.Expression;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Set;

import static com.cp.core.Generator.generate;

/**
 * @author zenghui
 * @date 2017/9/22
 */
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
