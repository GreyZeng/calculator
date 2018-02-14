package com.cp.controller;


import com.cp.models.Config;
import com.cp.models.Expression;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

import static com.cp.core.Generator.formatExpression;
import static com.cp.core.Generator.generate;
import static com.google.common.base.Objects.equal;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
public class AppController {
    @RequestMapping(value = "/getExpressions")
    @ResponseBody
    public ModelMap getExpressions(HttpServletRequest request, HttpServletResponse response) {
        String numberOfExpressionString = request.getParameter("numberOfExpression");
        String rangeString = request.getParameter("range");
        String maxNumberOfOperationString = request.getParameter("maxNumberOfOperation");
        String hasFractionString = request.getParameter("hasFraction");
        String hasParenthesesString = request.getParameter("hasParentheses");
        String hasNegativeString = request.getParameter("hasNegative");
        String hasMultipleAndDivideString = request.getParameter("hasMultipleAndDivide");

        Config config = Config.create();
        if (isNotEmpty(numberOfExpressionString)) {
            Integer numberOfExpression = parseInt(numberOfExpressionString);
            if (!equal(null, numberOfExpression)) {
                config.numberOfExpression(numberOfExpression);
            }
        }

        if (isNotEmpty(rangeString)) {
            Integer range = parseInt(rangeString);
            if (!equal(null, range)) {
                config.range(range);
            }
        }

        if (isNotEmpty(maxNumberOfOperationString)) {
            Integer maxNumberOfOperation = parseInt(maxNumberOfOperationString);
            if (!equal(null, maxNumberOfOperation)) {
                config.maxNumberOfOperation(maxNumberOfOperation);
            }
        }

        if (isNotEmpty(hasFractionString)) {
            Boolean hasFraction = parseBoolean(hasFractionString);
            if (!equal(null, hasFraction)) {
                config.hasFraction(hasFraction);
            }
        }
        if (isNotEmpty(hasParenthesesString)) {
            Boolean hasParentheses = parseBoolean(hasParenthesesString);
            if (!equal(null, hasParentheses)) {
                config.hasParentheses(hasParentheses);
            }
        }
        if (isNotEmpty(hasNegativeString)) {
            Boolean hasNegative = parseBoolean(hasNegativeString);
            if (!equal(null, hasNegative)) {
                config.hasNegative(hasNegative);
            }
        }
        if (isNotEmpty(hasMultipleAndDivideString)) {
            Boolean hasMultipleAndDivide = parseBoolean(hasMultipleAndDivideString);
            if (!equal(null, hasMultipleAndDivide)) {
                config.hasMultipleAndDivide(hasMultipleAndDivide);
            }
        }
        Set<Expression> expressions = generate(config);
        ModelMap result = new ModelMap();

        result.put("result", formatExpression(expressions));
        result.put("retcode", 1);
        return result;
    }

    @RequestMapping(value = "/locale", method = GET)
    public String getLocalePage() {
        return "my-locale";
    }
}
