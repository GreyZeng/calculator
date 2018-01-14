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
    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    String generate(@WebParam(name = "configs") String configs);

    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    String answer(@WebParam(name = "expression") String expressions);
}
