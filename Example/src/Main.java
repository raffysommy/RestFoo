
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.microsoft.z3.Context;

import it.polito.verifoo.rest.jaxb.*;

public class Main {
	Context ctx;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            // create a JAXBContext capable of handling the generated classes
            JAXBContext jc = JAXBContext.newInstance( "jaxb" );
            
            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();
            
            // unmarshal a document into a tree of Java content objects
            NFV root = (NFV) u.unmarshal( new FileInputStream( "./xsd/nfvInfo.xml" ) );
			//JAXBElement<NFV> root = (JAXBElement<NFV>)jaxbElement;
			//NFV el = (NFV)root.getValue();
            // make changes here to the po

            // create a Marshaller and marshal to std out
            Marshaller m = jc.createMarshaller();
            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
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
        }
	}

}
