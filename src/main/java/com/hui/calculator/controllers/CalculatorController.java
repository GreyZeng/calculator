package com.hui.calculator.controllers;

import com.hui.calculator.models.Config;
import com.hui.calculator.models.Expression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

import static com.google.common.base.Objects.equal;
import static com.hui.calculator.core.Generator.formatExpression;
import static com.hui.calculator.core.Generator.generate;
import static com.hui.calculator.models.Constants.*;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author zenghui
 */
@Controller
public class CalculatorController {
    private static final Logger logger = LogManager.getLogger(CalculatorController.class);
    @RequestMapping(value = "/result")
    public String result() {
        return "result";
    }
    @RequestMapping(value = {"", "/", "/index"})
    public String index() {
        return "index";
    }
 /*   @PostMapping("/getExpressions")
    public ModelMap getExpressions(@ModelAttribute Config config) {
        logger.debug("Config is {}",config);
        Set<Expression> expressions = generate(config);
        ModelMap result = new ModelMap();

        result.put(RESULT, formatExpression(expressions));
        result.put(RET_CODE, SUCCESS);
        logger.debug("result is {}",result);
        return result;
    }*/


    @PostMapping("/getExpressions")
    public ModelAndView getExpressions(@ModelAttribute Config config) {
        logger.debug("Config is {}",config);
        Set<Expression> expressions = generate(config);
        ModelAndView modelAndView = new ModelAndView("result");
        modelAndView.addObject("data",expressions);
        return modelAndView;
    }
}
