//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.11.17 alle 10:20:28 PM CET 
//


package it.polito.verifoo.rest.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element ref="{}NF-FG"/>
 *         &lt;element ref="{}Hosts"/>
 *         &lt;element ref="{}Connections"/>
 *         &lt;element ref="{}VNF-Catalog"/>
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
    "nffg",
    "hosts",
    "connections",
    "vnfCatalog"
})
@XmlRootElement(name = "NFV")
public class NFV {

    @XmlElement(name = "NF-FG", required = true)
    protected NFFG nffg;
    @XmlElement(name = "Hosts", required = true)
    protected Hosts hosts;
    @XmlElement(name = "Connections", required = true)
    protected Connections connections;
    @XmlElement(name = "VNF-Catalog", required = true)
    protected VNFCatalog vnfCatalog;

    /**
     * Recupera il valore della propriet� nffg.
     * 
     * @return
     *     possible object is
     *     {@link NFFG }
     *     
     */
    public NFFG getNFFG() {
        return nffg;
    }

    /**
     * Imposta il valore della propriet� nffg.
     * 
     * @param value
     *     allowed object is
     *     {@link NFFG }
     *     
     */
    public void setNFFG(NFFG value) {
        this.nffg = value;
    }

    /**
     * Recupera il valore della propriet� hosts.
     * 
     * @return
     *     possible object is
     *     {@link Hosts }
     *     
     */
    public Hosts getHosts() {
        return hosts;
    }

    /**
     * Imposta il valore della propriet� hosts.
     * 
     * @param value
     *     allowed object is
     *     {@link Hosts }
     *     
     */
    public void setHosts(Hosts value) {
        this.hosts = value;
    }

    /**
     * Recupera il valore della propriet� connections.
     * 
     * @return
     *     possible object is
     *     {@link Connections }
     *     
     */
    public Connections getConnections() {
        return connections;
    }

    /**
     * Imposta il valore della propriet� connections.
     * 
     * @param value
     *     allowed object is
     *     {@link Connections }
     *     
     */
    public void setConnections(Connections value) {
        this.connections = value;
    }

    /**
     * Recupera il valore della propriet� vnfCatalog.
     * 
     * @return
     *     possible object is
     *     {@link VNFCatalog }
     *     
     */
    public VNFCatalog getVNFCatalog() {
        return vnfCatalog;
    }

    /**
     * Imposta il valore della propriet� vnfCatalog.
     * 
     * @param value
     *     allowed object is
     *     {@link VNFCatalog }
     *     
     */
    public void setVNFCatalog(VNFCatalog value) {
        this.vnfCatalog = value;
    }

}
