
package com.itheima.bos.service.takedelivery.impl;


import com.itheima.bos.domain.takeDelivery.Order;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "OrderService", targetNamespace = "http://takeDelivery.service.bos.itheima.com/")
@XmlSeeAlso({
})
public interface OrderService {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "save", targetNamespace = "http://takeDelivery.service.bos.itheima.com/", className = "com.itheima.bos.service.takedelivery.Save")
    @ResponseWrapper(localName = "saveResponse", targetNamespace = "http://takeDelivery.service.bos.itheima.com/", className = "com.itheima.bos.service.takedelivery.SaveResponse")
    public void save(
            @WebParam(name = "arg0", targetNamespace = "")
                    Order arg0);

}
