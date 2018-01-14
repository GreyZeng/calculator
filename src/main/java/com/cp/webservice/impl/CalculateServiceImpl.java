package com.cp.webservice.impl;

import com.cp.webservice.CalculateService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @author zenghui
 * @create 2018-01-13 23:45
 **/
@WebService(serviceName = "CalculateService",
        targetNamespace = "http://webservice.cp.com/",
        endpointInterface = "com.cp.webservice.CalculateService"
)
@Component
public class CalculateServiceImpl implements CalculateService {
    @Override
    public String generate(String configs) {
        /*Set<Expression> expressions =  Generator.generate(Config.create(configs));
        return expressions.toString();*/
        // TODO
        return "Success";
    }

    @Override
    public String answer(String expressions) {
        /*if (null != expressions) {
            for (Expression expression : expressions) {
                Answer.answer(expression);
            }
        }

        return expressions;*/
        // TODO
        return "Success";
    }
}
