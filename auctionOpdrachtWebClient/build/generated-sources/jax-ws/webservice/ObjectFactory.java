
package webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice package. 
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

    private final static QName _DatabaseCleaner_QNAME = new QName("http://webservice/", "databaseCleaner");
    private final static QName _DatabaseCleanerResponse_QNAME = new QName("http://webservice/", "databaseCleanerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DatabaseCleaner }
     * 
     */
    public DatabaseCleaner createDatabaseCleaner() {
        return new DatabaseCleaner();
    }

    /**
     * Create an instance of {@link DatabaseCleanerResponse }
     * 
     */
    public DatabaseCleanerResponse createDatabaseCleanerResponse() {
        return new DatabaseCleanerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatabaseCleaner }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "databaseCleaner")
    public JAXBElement<DatabaseCleaner> createDatabaseCleaner(DatabaseCleaner value) {
        return new JAXBElement<DatabaseCleaner>(_DatabaseCleaner_QNAME, DatabaseCleaner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatabaseCleanerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "databaseCleanerResponse")
    public JAXBElement<DatabaseCleanerResponse> createDatabaseCleanerResponse(DatabaseCleanerResponse value) {
        return new JAXBElement<DatabaseCleanerResponse>(_DatabaseCleanerResponse_QNAME, DatabaseCleanerResponse.class, null, value);
    }

}
