import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.microsoft.z3.Context;

import it.polito.verifoo.rest.jaxb.*;
import it.polito.verifoo.rest.common.*;

public class Main {
	Context ctx;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            // create a JAXBContext capable of handling the generated classes
            JAXBContext jc = JAXBContext.newInstance( "it.polito.verifoo.rest.jaxb" );
            
            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
            Schema schema = sf.newSchema( new File( "./xsd/nfvInfo.xsd" )); 
            u.setSchema(schema);
            // unmarshal a document into a tree of Java content objects
            NFV root = (NFV) u.unmarshal( new FileInputStream( "./xsd/nfvInfo.xml" ));
			// make changes here to the root
             //TODO after routing table and acl are implemented
            
			VerifooProxy test = new VerifooProxy(root.getNFFG(), root.getHosts(), root.getConnections(), root.getVNFCatalog());
			
            test.checkNFFGProperty(root.getNFFG());
            
            // create a Marshaller and marshal to std out
            Marshaller m = jc.createMarshaller();
            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            m.setProperty( Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,"/xsd/nfvInfo.xsd");
            m.marshal( root, System.out );
        } catch( JAXBException je ) {
        	System.out.println("Error while unmarshalling or marshalling");
            je.printStackTrace();
            System.exit(1);
        } catch( IOException ioe ) {
            ioe.printStackTrace();
            System.exit(1);
        } catch( ClassCastException cce) {
        	System.out.println("Wrong data type found in XML document");
        	cce.printStackTrace();
            System.exit(1);
        } catch (BadNffgException e) {
			System.out.println("NFFG semantically incorrect");
        	e.printStackTrace();
            System.exit(1);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
