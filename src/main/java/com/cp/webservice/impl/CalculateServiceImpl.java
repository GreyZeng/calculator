package com.cp.webservice.impl;

import com.cp.core.Answer;
import com.cp.core.Generator;
import com.cp.models.Config;
import com.cp.models.Expression;
import com.cp.webservice.CalculateService;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.LinkedHashMap;
import java.util.Set;

import static com.google.common.collect.Maps.newLinkedHashMap;
import static org.apache.commons.lang3.StringUtils.EMPTY;

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
        Set<Expression> expressions = Generator.generate(Config.create(configs));
        return expressions.toString();
    }

    @Override
    public String answer(String expression) {
        return Answer.answer(expression);
    }

    @Override
    public String retrieveDefaultTemplate() {
        Gson gson = new Gson();
        LinkedHashMap<String, Object> map = newLinkedHashMap();
        map.put("numberOfExpression", "10");
        map.put("range", "10");
        map.put("hasFraction", "false");
        map.put("hasMultipleAndDivide", "true");
        map.put("hasParentheses", "false");
        map.put("hasNegative", "true");
        map.put("answer", "true");
        return gson.toJson(map);
    }

    public static void main(String[] args) {
        CalculateServiceImpl service = new CalculateServiceImpl();
        System.out.println(service.retrieveDefaultTemplate());
    }
}
