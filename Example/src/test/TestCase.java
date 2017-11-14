/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jaxb.NFV;

/**
 * @author Raffaele
 *
 */
public class TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
            // create a JAXBContext capable of handling the generated classes
            JAXBContext jc = JAXBContext.newInstance( "jaxb" );
            
            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();
            
            // unmarshal a document into a tree of Java content objects
            NFV root = (NFV) u.unmarshal( new FileInputStream( "./testfile/nfvIn.xml" ) );
            FileOutputStream out=new FileOutputStream("nfvOut.xml");
            // create a Marshaller and marshal to std out
            Marshaller m = jc.createMarshaller();
            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            m.setProperty( Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,"nfvInfo.xsd");
            m.marshal( root, out );
            out.close();
            Path file1 = Paths.get("./testfile/nfvIn.xml");
            Path file2 = Paths.get("./testfile/nfvOut.xml");
            List<String> f1 = Files.readAllLines(file1);
            List<String> f2 = Files.readAllLines(file2);
            f1.remove(0);
            f2.remove(0);         
            org.junit.Assert.assertEquals(f1, f2);
            
        } catch( JAXBException je ) {
        	fail("Error while unmarshalling or marshalling");
        } catch( IOException ioe ) {
        	fail(ioe.getMessage());
        } catch( ClassCastException cce) {        	
    		fail("Wrong data type found in XML document");
        }
	}

}
