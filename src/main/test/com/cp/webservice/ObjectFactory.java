
package com.cp.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.cp.webservice package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Answer_QNAME = new QName("http://webservice.cp.com/", "answer");
    private final static QName _AnswerResponse_QNAME = new QName("http://webservice.cp.com/", "answerResponse");
    private final static QName _Generate_QNAME = new QName("http://webservice.cp.com/", "generate");
    private final static QName _GenerateResponse_QNAME = new QName("http://webservice.cp.com/", "generateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cp.webservice
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Answer }
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link AnswerResponse }
     */
    public AnswerResponse createAnswerResponse() {
        return new AnswerResponse();
    }

    /**
     * Create an instance of {@link Generate }
     */
    public Generate createGenerate() {
        return new Generate();
    }

    /**
     * Create an instance of {@link GenerateResponse }
     */
    public GenerateResponse createGenerateResponse() {
        return new GenerateResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservice.cp.com/", name = "answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnswerResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservice.cp.com/", name = "answerResponse")
    public JAXBElement<AnswerResponse> createAnswerResponse(AnswerResponse value) {
        return new JAXBElement<AnswerResponse>(_AnswerResponse_QNAME, AnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Generate }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservice.cp.com/", name = "generate")
    public JAXBElement<Generate> createGenerate(Generate value) {
        return new JAXBElement<Generate>(_Generate_QNAME, Generate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservice.cp.com/", name = "generateResponse")
    public JAXBElement<GenerateResponse> createGenerateResponse(GenerateResponse value) {
        return new JAXBElement<GenerateResponse>(_GenerateResponse_QNAME, GenerateResponse.class, null, value);
    }

}
