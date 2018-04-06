package com.hui.calculator.controllers;

import com.hui.calculator.models.Config;
import com.hui.calculator.models.Expression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

import static com.hui.calculator.core.Generator.generate;

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


    @PostMapping("/getExpressions")
    public ModelAndView getExpressions(@ModelAttribute Config config) {
        logger.debug("Config is {}",config);
        Set<Expression> expressions = generate(config);
        ModelAndView modelAndView = new ModelAndView("result");
        modelAndView.addObject("data",expressions);
        modelAndView.addObject("answer",config.getAnswer());
        return modelAndView;
    }
}
