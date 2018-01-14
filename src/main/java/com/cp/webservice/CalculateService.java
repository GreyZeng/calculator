package com.cp.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author zenghui
 * @create 2018-01-13 23:20
 **/
@WebService(name = "CalculateService",
        targetNamespace = "http://webservice.cp.com/"
)
public interface CalculateService {
    /**
     * 生成表达式API
     *
     * @param configs
     * @return
     */
    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    String generate(@WebParam(name = "configs") String configs);

    /**
     * 答题API 暂时支持一题
     * @param expression
     * @return
     */
    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    String answer(@WebParam(name = "expression") String expression);

    /**
     * 获取默认的配置信息模板，可用于生成表达式的配置
     *
     * @return
     */
    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    String retrieveDefaultTemplate();
}
