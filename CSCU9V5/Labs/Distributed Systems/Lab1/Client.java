import java.net.*;
import java.io.*;

public class Client{	

	public Client() {	
		
		try { 
		
				Socket s = new Socket("127.0.0.1", 5155);			      

				InputStream in = s.getInputStream();			
				BufferedReader bin = new BufferedReader (new InputStreamReader(in));								  		    
				System.out.println(bin.readLine());				      
				s.close();
				
				Thread.sleep(5000);
			}		
			catch (java.io.IOException e) 
				{System.out.println(e); System.exit(1);} 
			catch (InterruptedException e) 
				{e.printStackTrace();}	
		
	}

	
	public static void main(String args[]) {	
		
		Client client = new Client();	
	}
	
}
			
