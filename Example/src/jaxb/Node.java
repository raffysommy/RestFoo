//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.11.10 alle 11:41:35 PM CET 
//


package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="host" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="NF-FG" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FunctionalType" use="required" type="{}F-name" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Node")
public class Node {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "host")
    protected String host;
    @XmlAttribute(name = "NF-FG", required = true)
    protected String nffg;
    @XmlAttribute(name = "FunctionalType", required = true)
    protected FName functionalType;

    /**
     * Recupera il valore della propriet� name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della propriet� name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della propriet� host.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * Imposta il valore della propriet� host.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Recupera il valore della propriet� nffg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNFFG() {
        return nffg;
    }

    /**
     * Imposta il valore della propriet� nffg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNFFG(String value) {
        this.nffg = value;
    }

    /**
     * Recupera il valore della propriet� functionalType.
     * 
     * @return
     *     possible object is
     *     {@link FName }
     *     
     */
    public FName getFunctionalType() {
        return functionalType;
    }

    /**
     * Imposta il valore della propriet� functionalType.
     * 
     * @param value
     *     allowed object is
     *     {@link FName }
     *     
     */
    public void setFunctionalType(FName value) {
        this.functionalType = value;
    }

}
