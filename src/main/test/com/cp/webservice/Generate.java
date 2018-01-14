
package com.cp.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>generate complex type�� Java �ࡣ
 * <p>
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;complexType name="generate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="configs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generate", propOrder = {
        "configs"
})
public class Generate {

    protected String configs;

    /**
     * ��ȡconfigs���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getConfigs() {
        return configs;
    }

    /**
     * ����configs���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConfigs(String value) {
        this.configs = value;
    }

}
