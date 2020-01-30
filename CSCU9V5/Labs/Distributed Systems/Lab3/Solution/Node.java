import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Node 
{

	public static void main(String[] args) throws IOException
	{
		int socket = Integer.parseInt(args[0]);
		int next = Integer.parseInt(args[1]);
		ServerSocket	s;	
		connection	c;
		Socket		previous;
		//InetAddress host
		InetAddress host = InetAddress.getLocalHost() ;


			s = new ServerSocket(socket); //throws bind exception if server is ran twice as its trying to use a port that is already taken by another process

		while (true) 
		{
			System.out.println("Server is listening ....");		

			
			// OK, now listen for connections and create them	
	   	    try 
	   	    {
	   	    	previous = s.accept();
	   	    	System.out.println("SERVER:  connection accepted\n\n");
	   	    	int i = s.getLocalPort();	   	    	
	   	    	c = new connection(i);
	   	    	c.start();
	   	    	c.join();
	   	    	

	   	    	Socket n = new Socket(host, next); 
	   	    	
	   	    		   	    	
	   	    }
	   	    catch (java.io.IOException e) 
	   	    {
	   	    	System.out.println(e);
	   	    } catch (InterruptedException e) {
				e.printStackTrace();
			}
	   	}
	}
}
