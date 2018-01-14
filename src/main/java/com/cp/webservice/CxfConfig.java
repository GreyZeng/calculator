package com.cp.webservice;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author zenghui
 * @create 2018-01-14 7:37
 **/
@Configuration
public class CxfConfig {
    @Autowired
    private Bus bus;

    @Autowired
    CalculateService calculateService;


    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, calculateService);
        endpoint.publish("/CalculateService");
        return endpoint;
    }
}
