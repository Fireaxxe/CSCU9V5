import java.net.*;
import java.io.*;

public class Client{	
	
	int counter = 0;
	private boolean flag =true;
	
	public Client(int server_port, String server_host) {	
		while (flag) {
		try { 
						    
			    System.out.println("Client: " + counter + "request made");
			    counter++;
			    
				Socket s = new Socket("127.0.0.1", 5155);			      
				
			    InputStream in = s.getInputStream();
			    DataInputStream ip = new DataInputStream(in);
			   
				//BufferedReader bin = new BufferedReader (new InputStreamReader(in));								  		    
				//System.out.println(bin.readLine());		
				
				double local_double = 0.0;
				local_double = ip.readDouble() ;
				
				System.out.println(local_double);
				s.close();
				
				Thread.sleep(5000);
				
		}
			catch (java.io.IOException e) 
				{System.out.println(e); System.exit(1);} 
			catch (InterruptedException e) 
				{e.printStackTrace();}	
		}
	
		try{ 
			InetAddress server_inet_address =  InetAddress.getLocalHost() ;
			String server_host_name = server_inet_address.getHostName();
			System.out.println ("Server hostname is "+server_host_name ) ;
			System.out.println ("Server port is xxxx") ;
		}
		catch 
		(java.net.UnknownHostException e)
		{ System.out.println(e);   System.exit(1); } }
	

	
	public static void main(String argv[]) {	
		
		if((argv.length < 1) || (argv.length > 2))
		{ System.out.println("Usage: [host] <port>") ;  System.exit (1) ;}
		String server_host = argv[0] ;
		int server_port = 5156 ;
		if(argv.length == 2)
			server_port = Integer.parseInt (argv[1]) ;
		Client client = new Client(server_port, server_host);} // end of main
	}
	

			
