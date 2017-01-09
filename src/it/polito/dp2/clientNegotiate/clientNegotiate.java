package it.polito.dp2.clientNegotiate;

import generated.Negotiate;

import java.net.URI;
import java.util.List;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

public class clientNegotiate {

	WebTarget target;
	 
	public clientNegotiate(){
			
		Client client = ClientBuilder.newClient();
		    
		// create a web target for the intended URI
		target = client.target(getBaseURI());
			
	}

	public static void main(String[] args) {
		
		try{
			
		    // create the client object 
		    clientNegotiate clientNeg = new clientNegotiate();
		    
			// and a negotiation object
		    Negotiate neg = new Negotiate();

		    // perform a post
			neg.setMax(7);
		    neg.setMin(5);
		    clientNeg.performPost(neg);
		    
		    // perform another post
		    neg.setMax(11);
		    neg.setMin(6);
		    clientNeg.performPost(neg);
		    
		    // now perform a get
		    clientNeg.performGet();
		    
		    // modify the first negotiation object by performing a put
		    neg.setMax(8);
		    neg.setMin(6);
		    clientNeg.performPut( 1, neg);
		    
		    // perform another get to check the difference
		    clientNeg.performGet();
		    
		    // perform a delete on the first element
		    clientNeg.performDelete( 1 );
		    
		    // perform another get to check the difference
		    clientNeg.performGet();
		    
		} catch(ProcessingException pe ){
			System.out.println("Error during JAX-RS request processing");
			pe.printStackTrace();
		} catch(WebApplicationException wae ){
			System.out.println("Server returned error");
			wae.printStackTrace();
		} catch(Exception e) {
			System.out.println("Unexpected exception");
			e.printStackTrace();
		}
	}
	
	private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://127.0.0.1:8080/negotiate-webdyn/webapi/").build();
	  }
	
	public void performPost(Negotiate neg) {
		
	    System.out.println();
	    System.out.println("--- Performing a Post --- \n");
    	System.out.println(" min " + neg.getMin());
    	System.out.println(" max " + neg.getMax());
		// Perform a POST http request using Json and accept the response as a Negotiate Object
	    // Jersey automatically parses the Json response and builds a Java object of the specified class
	    Negotiate response = target.path("negotiateService")
	    		                   .request(MediaType.APPLICATION_JSON)
	    		                   .post(Entity.entity(neg,MediaType.APPLICATION_JSON),Negotiate.class);
    	System.out.println();
    	System.out.println("--- Response of Post received --- \n");
    	System.out.println(" min " + response.getMin());
    	System.out.println(" max " + response.getMax());
    	System.out.println(" id  "+ response.getId());    	
	    	
	}

	public void performGet(){

    	System.out.println();
	    System.out.println(" --- Performing a GET --- ");
	    List<Negotiate> jsonResponse = target.path("negotiateService")
	    									 .request().accept(MediaType.APPLICATION_JSON)
	    									 .get(new GenericType<List<Negotiate>>() {});
    	System.out.println();
	    System.out.println(" --- Response of GET received --- ");
	    for(Negotiate n : jsonResponse){
	    	System.out.println(" min " + n.getMin());
	    	System.out.println(" max " + n.getMax());
	    	System.out.println(" id  "+ n.getId());    	
	    }
	    
	    // this is the response with json format in string format
	    String responseAsString = target.path("negotiateService")
	    								.request()
	    								.accept(MediaType.APPLICATION_JSON)
	    								.get(String.class);
    	System.out.println();
    	System.out.println(" --- Response of GET as string: --- ");
    	System.out.println(responseAsString);
	    
	    // If you want to see the response formatted as xml or text plain uncomment this code 
	    /*
	    System.out.println(" --- Response of GET as Text --- ");
		// get The text/plain format response 
		String TextResponse = target.path("negotiateService")
									.request()
									.accept(MediaType.TEXT_PLAIN)
									.get(String.class);
		System.out.println(TextResponse);
		System.out.println(" --- Response of GET as XML --- ");
		// get The XML format response 
		String xmlResponse= target.path("negotiateService")
								  .request()
								  .accept(MediaType.APPLICATION_XML_TYPE)
								  .get(String.class);
		System.out.println(xmlResponse);
	    */
   
	}

	public void performDelete( int id ){
		
    	System.out.println();
	    System.out.println(" --- Performing a DELETE --- ");
	    System.out.println(" id "+id);
	    
		// return the deleted element
		Negotiate response = target.path("negotiateService")
								   .path(Integer.toString(id))
								   .request(MediaType.APPLICATION_JSON)
								   .delete(Negotiate.class);
		System.out.println();
	    System.out.println("--- Response of Delete received --- \n");
    	System.out.println(" min " + response.getMin());
    	System.out.println(" max " + response.getMax());
    	System.out.println(" id  "+ response.getId());    	
	
	}
	
	public void performPut( int index, Negotiate neg){
	    System.out.println();
	    System.out.println("--- Performing a Put --- \n");
    	System.out.println(" min " + neg.getMin());
    	System.out.println(" max " + neg.getMax());
    	System.out.println(" id " + index);
		// return the element modified. 
		 Negotiate response = target.path("negotiateService")
				 					.path(Integer.toString(index))
				 					.request(MediaType.APPLICATION_JSON)
				 					.put(Entity.entity(neg,MediaType.APPLICATION_JSON),Negotiate.class);
		 System.out.println();
		 System.out.println("--- Put --- \n");
		 System.out.println(" min " + response.getMin());
		 System.out.println(" max " + response.getMax());
		 System.out.println(" id  "+ response.getId());    	
		
	}
		
}
