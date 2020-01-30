import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class start 
{

	public static void main(String[] args) throws UnknownHostException 
	{
		//int thisPort = Integer.parseInt(args[0]);
		//int nextPort = Integer.parseInt(args[1]);
		InetAddress host_addr = InetAddress.getLocalHost() ;
		String host_name = host_addr.getHostName() ;
		System.out.println(host_addr + " " + host_name);
		int port = Integer.parseInt(args[0]);
		
		
		//Node node = new Node(4454, host_addr, 4455);
		//Node node2 = new Node((4455), host_addr, (4456));
		//Node node3 = new Node((4456), host_addr, (4454));
		
		try {
			Socket n = new Socket(host_addr, port);
		} catch (IOException e) {
			System.out.println("problem making connection");
		} 
	}

}
