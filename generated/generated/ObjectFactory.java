
package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Negotiate_QNAME = new QName("", "negotiate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Negotiate }
     * 
     */
    public Negotiate createNegotiate() {
        return new Negotiate();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Negotiate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "negotiate")
    public JAXBElement<Negotiate> createNegotiate(Negotiate value) {
        return new JAXBElement<Negotiate>(_Negotiate_QNAME, Negotiate.class, null, value);
    }

}
