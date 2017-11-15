//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.11.15 alle 02:53:55 PM CET 
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
 *       &lt;attribute name="FunctionalType" use="required" type="{}F-name" />
 *       &lt;attribute name="reqDiskStorage" use="required" type="{}positiveFloat" />
 *       &lt;attribute name="configuration" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "VNF")
public class VNF {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "FunctionalType", required = true)
    protected FName functionalType;
    @XmlAttribute(name = "reqDiskStorage", required = true)
    protected float reqDiskStorage;
    @XmlAttribute(name = "configuration")
    protected String configuration;

    /**
     * Recupera il valore della proprietà name.
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
     * Imposta il valore della proprietà name.
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
     * Recupera il valore della proprietà functionalType.
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
     * Imposta il valore della proprietà functionalType.
     * 
     * @param value
     *     allowed object is
     *     {@link FName }
     *     
     */
    public void setFunctionalType(FName value) {
        this.functionalType = value;
    }

    /**
     * Recupera il valore della proprietà reqDiskStorage.
     * 
     */
    public float getReqDiskStorage() {
        return reqDiskStorage;
    }

    /**
     * Imposta il valore della proprietà reqDiskStorage.
     * 
     */
    public void setReqDiskStorage(float value) {
        this.reqDiskStorage = value;
    }

    /**
     * Recupera il valore della proprietà configuration.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfiguration() {
        return configuration;
    }

    /**
     * Imposta il valore della proprietà configuration.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfiguration(String value) {
        this.configuration = value;
    }

}
