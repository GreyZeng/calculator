
package com.cp.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>generateResponse complex type�� Java �ࡣ
 * <p>
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;complexType name="generateResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="String" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generateResponse", propOrder = {
        "string"
})
public class GenerateResponse {

    @XmlElement(name = "String")
    protected String string;

    /**
     * ��ȡstring���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getString() {
        return string;
    }

    /**
     * ����string���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setString(String value) {
        this.string = value;
    }

}
