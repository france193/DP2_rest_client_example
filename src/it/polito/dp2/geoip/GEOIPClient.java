package it.polito.dp2.geoip;

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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* 
 * Simple client for the GEOIP service
 * (http://www.webservicex.net/geoipservice.asmx)
 * Version based on JAXP
 */
public class GEOIPClient {

	public static void main(String[] args) {
		GEOIPClient clientGeo = new GEOIPClient();
		clientGeo.PerformGet();
	}
	
	public void PerformGet() {
		String AddrToConvert = "72.5.124.92"; 	// this is the address to convert

		try{
			// build the JAX-RS client. 
			Client client = ClientBuilder.newClient();
			  
			// make the web targets with the URIs of the get requests
			WebTarget target = client.target(getBaseURI()+ AddrToConvert);
			WebTarget target2 = client.target(getBaseURI2());
			    
			// Perform two get http requests using mediaType=APPLICATION_XML
			// and turn the response into a normal string

			// First request: Get GeoIP XML for IP address resolution as a string
			String xmlGetGeoIP = target.request()
										.accept(MediaType.APPLICATION_XML)
										.get(String.class);
			// Print result
			System.out.println (" --------- First request completed --------- ");
			System.out.println ("Result of resolution of " + AddrToConvert);
			System.out.println ("GeoIP: ");
			printGeoIP(xmlGetGeoIP);
			
			// Second request: Get GeoIP XML for Context as a string
			String xmlGetGeoContex = target2.request()
											.accept(MediaType.APPLICATION_XML)
											.get(String.class);
			    
			// Print result   
			System.out.println (" --------- Second request completed --------- ");
			System.out.println ("Contex: ");
			printGeoIP(xmlGetGeoContex);
			    
		} catch(Exception ex ) {
			System.out.println("Error while invoking the service");
			ex.printStackTrace();
		}
	}
	
	// returns base URI for first resource
	private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://www.webservicex.net/geoipservice.asmx/GetGeoIP?IPAddress=").build();
	}
	
	// returns base URI for second resource
	private static URI getBaseURI2() {
	    return UriBuilder.fromUri("http://www.webservicex.net/geoipservice.asmx/GetGeoIPContext?").build();
	}
	
	// this function is a JAXP parser for the XML response 
	private void printGeoIP(String xmlGeoIp){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
		    db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
		    is.setCharacterStream(new StringReader(xmlGeoIp));
		    try {
		        Document doc = db.parse(is);
		        
		        NodeList ReturnCode = doc.getElementsByTagName("ReturnCode");
		        if (ReturnCode.getLength()>0)
		        	System.out.println("Return Code: " +ReturnCode.item(0).getTextContent());
		        NodeList IP = doc.getElementsByTagName("IP");
		        if (IP.getLength()>0)
		        	System.out.println("IP: " +IP.item(0).getTextContent());
		        NodeList CountryName = doc.getElementsByTagName("CountryName");
		        if (CountryName.getLength()>0)
		        	System.out.println("Country Name: " +CountryName.item(0).getTextContent());
		        NodeList CountryCode = doc.getElementsByTagName("CountryCode");
		        if (CountryCode.getLength()>0)
		        	System.out.println("Country Code: " +CountryCode.item(0).getTextContent());
		        
		    } catch (SAXException e) {
		        // handle SAXException
		    	System.out.println("Error parsing response");
		    	e.printStackTrace();
		    } catch (IOException e) {
		        // handle IOException
		    	System.out.println("Error reading response");
		    	e.printStackTrace();
		    }
		} catch (ParserConfigurationException e1) {
		    // handle ParserConfigurationException
	    	System.out.println("Parser configuration error");
			e1.printStackTrace();
		}
	}
}
