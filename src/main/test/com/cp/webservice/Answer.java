
package com.cp.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>answer complex type�� Java �ࡣ
 * <p>
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;complexType name="answer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="expression" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "answer", propOrder = {
        "expression"
})
public class Answer {

    protected String expression;

    /**
     * ��ȡexpression���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getExpression() {
        return expression;
    }

    /**
     * ����expression���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setExpression(String value) {
        this.expression = value;
    }

}
