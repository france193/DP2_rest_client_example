package it.polito.dp2.counterClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class CounterClient {
	
    private WebTarget target;

    public CounterClient() {
		java.lang.Integer incrementValue=new java.lang.Integer(1); // increment for test
		try
		  { 
	        // create the client
	        Client c = ClientBuilder.newClient();
	        target = c.target("http://localhost:8080/counter/webapi/");
	        
			System.out.println ("Ready to invoke remote operations");	    
	              
            System.out.print("Setting counter to zero....");
            this.resetCounter();
            System.out.println("..Counter set to zero !");
            System.out.println("Counter value: "+ this.getCounterValue());
             
            // incrementValue= 100;
            System.out.print("Incrementing counter....");
            this.incrementCounter(incrementValue);
            System.out.println("..Counter incremented !");
            System.out.println("Counter value: "+ this.getCounterValue());
         
            System.out.print("Setting counter to zero....");
            this.resetCounter();
            System.out.println("..Counter set to zero !");
            System.out.println("Counter value: "+ this.getCounterValue());
             
            System.out.println("Closing Client....");
           
		  }
		  catch (Exception e)
		  {
			 System.out.println ("Fatal error: " + e);
		  }
	   }
    
	    public void incrementCounter(int arg) {
	        target.path("mycounter")
	        	  .request()
	        	  .post(Entity.entity(arg, MediaType.TEXT_PLAIN),Integer.class);
	        System.out.println("Increment operation succeeded!");
	    }
	    
	    public int getCounterValue() {
	        String responseMsg = target.path("mycounter")
	        						   .request()
	        						   .get(String.class);
	        return Integer.valueOf(responseMsg);
	    }
	    
	    public void resetCounter() {
	        target.path("mycounter")
	        	  .request()
	        	  .put(Entity.entity("", MediaType.TEXT_PLAIN),Integer.class);
	        System.out.println("Reset operation succeeded!");
	    }
    
	    public static void main(String[] args) {
			new CounterClient();
	    }
		  
}
