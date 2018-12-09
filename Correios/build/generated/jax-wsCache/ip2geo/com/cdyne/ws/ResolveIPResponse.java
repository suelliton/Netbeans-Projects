
package com.cdyne.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResolveIPResult" type="{http://ws.cdyne.com/}IPInformation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "resolveIPResult"
})
@XmlRootElement(name = "ResolveIPResponse")
public class ResolveIPResponse {

    @XmlElement(name = "ResolveIPResult")
    protected IPInformation resolveIPResult;

    /**
     * Obtém o valor da propriedade resolveIPResult.
     * 
     * @return
     *     possible object is
     *     {@link IPInformation }
     *     
     */
    public IPInformation getResolveIPResult() {
        return resolveIPResult;
    }

    /**
     * Define o valor da propriedade resolveIPResult.
     * 
     * @param value
     *     allowed object is
     *     {@link IPInformation }
     *     
     */
    public void setResolveIPResult(IPInformation value) {
        this.resolveIPResult = value;
    }

}
