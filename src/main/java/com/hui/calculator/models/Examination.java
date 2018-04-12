package com.hui.calculator.models;

import com.google.common.base.Objects;

import java.util.List;
import java.util.Map;

/**
 * @author
 */
public class Examination {
    private Config config;
    private Map<String, String> expressions;

    @Override
    public String toString() {
        return "Examination{" +
                "config=" + config +
                ", expressions=" + expressions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Examination that = (Examination) o;
        return Objects.equal(config, that.config) &&
                Objects.equal(expressions, that.expressions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(config, expressions);
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Map<String, String> getExpressions() {
        return expressions;
    }

    public void setExpressions(Map<String, String> expressions) {
        this.expressions = expressions;
    }
}
