import java.net.*;
import java.io.*;
import java.util.*;

/**
 * 
 * @author 2520796
 *
 */
public class Node {

	private Random ra;
	private Socket s;
	private PrintWriter pout = null;
	private ServerSocket n_ss;
	private Socket n_token;
	private String c_host = "127.0.0.1";
	private int c_request_port = 7000;
	private String n_host_name;
	private int n_port;
	private int sleepTime;

	/**
	 * @param nam -> name of host
	 * @param por -> port number
	 * @param sec -> sleep time
	 */
	public Node(String nam, int por, int sec) {

		ra = new Random();
		this.n_host_name = nam;
		this.n_port = por;
		this.sleepTime = sec;

		System.out.println("Node " + n_host_name + ":" + n_port + " of DME is active ....");

		/**
		 * The node sends the Host name and Port number through a socket to the
		 * coordinator(server) and opens a server socket which it will wait to receive a
		 * token.
		 */
		while (true) {

			int randomNumber = ra.nextInt(sleepTime);
			System.out.println("Random Sleep Time: " + randomNumber);
			// >>> sleep a random number of seconds linked to the initialisation sec value
			try {
				Thread.sleep(randomNumber);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				// >>>
				// **** Send the coordinator a token request.
				// send your IP address and port number
				n_ss = new ServerSocket(n_port);
				s = new Socket(c_host, c_request_port);
				OutputStream outstream = s.getOutputStream();
				pout = new PrintWriter(outstream);
				String toSend = n_host_name + " " + n_port;
				pout.write(toSend);
				pout.flush();
				pout.close();
				System.out.println("Token request made to the port: " + s.getPort() + ". \n");

				// >>>
				// **** Wait for the token (synchronisation)
				try {
					System.out.println("Waiting to receive a token...");
					n_token = n_ss.accept();
					System.out.println("Token received successfully!" + "\n");
					n_ss.close();
					n_token.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

				// >>>
				// Sleep half a second, say
				// This is the critical session
				try {
					System.out.println("Enter critical session.");
					Thread.sleep(1500);
					System.out.println("Exit critical session" + ". \n");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// >>>
				// **** Return the token
				try {
					// Makes new connection to indicate return of token (Port 7000 and 7001 is used)
					n_token = new Socket(c_host, 7001);
					System.out.println("Token returned to coordinator.");
					Thread.sleep(3000);
					n_token.close();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				System.out.println(e);
				System.exit(1);
			}
		}
	}

	public static void main(String args[]) {

		String node_host_name = "";
		int node_port;
		if ((args.length < 1) || (args.length > 2)) {
			System.out.print("Usage: Node [port number] [millisecs]");
			System.exit(1);
		}

		/**
		 * This gets the IP and the Port number of the node
		 */
		try {
			InetAddress node_Address = InetAddress.getLocalHost();
			node_host_name = node_Address.getHostName();
			System.out.println("Node hostname is " + node_host_name + ".");
		} catch (UnknownHostException e) {
			System.out.println(e);
			System.exit(1);
		}
		node_port = Integer.parseInt(args[0]);
		System.out.println("Node port is " + node_port + ".");
		Node n = new Node(node_host_name, node_port, Integer.parseInt(args[1]));
	}
}
