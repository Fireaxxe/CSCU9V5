import java.net.*;

public class Server{
	
	
	private ServerSocket	s;	
	private Socket		client;	
	private Connection	c;
	private boolean flag=true;
	
	public Server(){ 
	  int counter = 0;
		// create the socket the server will listen to 
		try {
			s = new ServerSocket(5155);
		}		
		catch (java.io.IOException e) {
			System.out.println(" " + e); System.exit(1);
		}		

		System.out.println("Server is listening ....");		
		
		
		// OK, now listen for connections and create them
		while(flag) {
   	    try {
		    
   	    	client = s.accept();
   	     System.out.println("Server: " + counter + "request made");
		    counter++;
   	    	System.out.println("SERVER:  connection accepted\n\n");
   	    	System.out.println(counter);
   	    	// create a separate thread to service the client
   	    	c = new Connection(client);
   	    	c.start(); 
   	    	
   	        System.out.println("Thread Name=" + c.getName());
   	      
   	    	try{Thread.sleep(1000);}
   	    	
		    catch(Exception e) {System.out.print(e);
		    }
   	    	
   	    }
   	      catch (java.io.IOException e) {System.out.println(e);}}}
	
		
	public static void main(String args[]){ 
			
		Server timeOfDayServer = new Server(); 	
		
		
	}

}	