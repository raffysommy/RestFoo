//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.11.16 alle 12:05:13 PM CET 
//


package it.polito.verifoo.rest.jaxb;

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
 *       &lt;attribute name="sourceNode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="destNode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="reqLatency" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="NF-FG" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Link")
public class Link {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "sourceNode", required = true)
    protected String sourceNode;
    @XmlAttribute(name = "destNode", required = true)
    protected String destNode;
    @XmlAttribute(name = "reqLatency", required = true)
    protected int reqLatency;
    @XmlAttribute(name = "NF-FG", required = true)
    protected String nffg;

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
     * Recupera il valore della propriet� sourceNode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceNode() {
        return sourceNode;
    }

    /**
     * Imposta il valore della propriet� sourceNode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceNode(String value) {
        this.sourceNode = value;
    }

    /**
     * Recupera il valore della propriet� destNode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestNode() {
        return destNode;
    }

    /**
     * Imposta il valore della propriet� destNode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestNode(String value) {
        this.destNode = value;
    }

    /**
     * Recupera il valore della propriet� reqLatency.
     * 
     */
    public int getReqLatency() {
        return reqLatency;
    }

    /**
     * Imposta il valore della propriet� reqLatency.
     * 
     */
    public void setReqLatency(int value) {
        this.reqLatency = value;
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

}
