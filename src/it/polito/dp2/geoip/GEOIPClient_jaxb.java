package it.polito.dp2.geoip;

import javax.ws.rs.client.Client;

import java.net.URI;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/* 
 * Simple client for the GEOIP service
 * (http://www.webservicex.net/geoipservice.asmx)
 * Version based on JAXB
 */
public class GEOIPClient_jaxb {

	public static void main(String[] args) {
		GEOIPClient_jaxb clientGeo = new GEOIPClient_jaxb();
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
			GeoIP geoIP = target.request()
								.accept(MediaType.APPLICATION_XML)
								.get(GeoIP.class);
			// Print result
			System.out.println (" --------- First request completed --------- ");
			System.out.println ("Result of resolution of " + AddrToConvert);
			System.out.println ("GeoIP: ");
			printGeoIP(geoIP);
			
			// Second request: Get GeoIP XML for Context as a string
			GeoIP geoIPContext = target2.request()
											.accept(MediaType.APPLICATION_XML)
											.get(GeoIP.class);
			    
			// Print result   
			System.out.println (" --------- Second request completed --------- ");
			System.out.println ("Contex: ");
			printGeoIP(geoIPContext);
			    
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
	
	// this function exploits the JAXB-annotated GeoIP class 
	private void printGeoIP(GeoIP geoIP){
		if (geoIP == null)
			return;
		System.out.println("Return Code: " + geoIP.getReturnCode());
		System.out.println("IP: " + geoIP.getIP());
		System.out.println("Country Name: " + geoIP.getCountryName());
		System.out.println("Country Code: " + geoIP.getCountryCode());
	}
}
