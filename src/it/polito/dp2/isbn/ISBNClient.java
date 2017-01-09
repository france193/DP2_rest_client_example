package it.polito.dp2.isbn;

import javax.ws.rs.client.Client;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class ISBNClient {

	public static void main(String[] args) {
		try{
			ISBNClient clientIsbn = new ISBNClient();
			clientIsbn.PerformGet();
		}catch(Exception ex ){
			System.err.println("Error during execution of remote operation");
			System.err.println(ex.getMessage());
		}
	}
	
	public void PerformGet(){
		// build the client Jersey object 
		Client client = ClientBuilder.newClient();
		
		// chose the isbn to check
		String isbn = "1400095727";
		System.out.println("Checking ISBN "+isbn);
		
		// chose the web target
		WebTarget target = client.target(getBaseURI());
		
		// perform a get request using mediaType=APPLICATION_XML
		// and convert the response into a normal string
		String xmlISBN = target.path(isbn)
							   .path("editions.xml")
							   .request()
							   .accept(MediaType.APPLICATION_XML)
							   .get(String.class);
		// perform a get request using mediaType=APPLICATION_JSON and accept
		// the response as a normal string
		String xmlJSON = target.path(isbn)
							   .path("metadata.js")
							   .request()
							   .accept(MediaType.APPLICATION_JSON)
							   .get(String.class);
		    
		System.out.println("--- XML response ---");
		ISBNClient.printStatusXML(xmlISBN);
		System.out.println();
		System.out.println("--- JSON response ---");
		System.out.println(xmlJSON);
	}
	
	private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://xisbn.worldcat.org/webservices/xid/isbn/").build();
	}
	
	// this function develops the parsing of an XMLDocument. 
	private static void printStatusXML(String xmlISBN){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
		    db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
		    is.setCharacterStream(new StringReader(xmlISBN));
		    try {
		        Document doc = db.parse(is);
		        
		        String ReturnCode = doc.getDocumentElement().getAttribute("stat");
		        System.out.println("Operation completed." );
		        System.out.println("XML Status: "+ ReturnCode );
		        
		    } catch (SAXException e) {
				System.err.println("Error during the decoding of the XML response");
		    } catch (IOException e) {
				System.err.println("Error while internally reading the XML response");
		    }
		} catch (ParserConfigurationException e1) {
			System.err.println("Misconfiguration in the XML parser");
		}		
	}
}
