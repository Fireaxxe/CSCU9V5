import java.net.*;

public class Server{
	
	
	private ServerSocket	s;	
	private Socket		client;	
	private Connection	c;
	
	public Server(){ 
	   
		// create the socket the server will listen to 
		try {
			s = new ServerSocket(5155);
		}		
		catch (java.io.IOException e) {
			System.out.println(" " + e); System.exit(1);
		}		

		System.out.println("Server is listening ....");		

		
		// OK, now listen for connections and create them	
   	    try {
   	    	client = s.accept();
   	    	System.out.println("SERVER:  connection accepted\n\n");

   	    	// create a separate thread to service the client
   	    	c = new Connection(client);
   	    	c.start(); 
   	    	
   	    	try{Thread.sleep(500);}
		    catch(Exception e) {System.out.print(e);}
   	    }	
   	    catch (java.io.IOException e) {System.out.println(e);}
	}
	
   
		
	public static void main(String args[]){ 
			
		Server timeOfDayServer = new Server(); 	
		
	}

}	