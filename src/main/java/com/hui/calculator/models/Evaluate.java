package com.hui.calculator.models;

import com.google.common.base.Objects;

import java.util.List;
import java.util.Map;

/**
 * @author zenghui
 */
public class Evaluate {
    private Config config;
    private List<Map<String, Map<Boolean, String>>> results;
    private boolean enough;
    private String remarks;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public List<Map<String, Map<Boolean, String>>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, Map<Boolean, String>>> results) {
        this.results = results;
    }

    public boolean isEnough() {
        return enough;
    }

    public void setEnough(boolean enough) {
        this.enough = enough;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Evaluate evaluate = (Evaluate) o;
        return enough == evaluate.enough &&
                Objects.equal(config, evaluate.config) &&
                Objects.equal(results, evaluate.results) &&
                Objects.equal(remarks, evaluate.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(config, results, enough, remarks);
    }
}
