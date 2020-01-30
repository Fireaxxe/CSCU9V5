import java.io.IOException;
import java.net.*;

/**
 * 
 * @author 2520796
 *
 */
public class C_receiver extends Thread {

	private C_buffer buffer; // buffer declaration
	private int port; // port declaration
	private ServerSocket s_socket; // internal socket used for handling the requests on the node network
	private Socket socketFromNode;

	/**
	 * 
	 * @param b -> buffer
	 * @param p -> port number
	 */
	public C_receiver(C_buffer b, int p) {
		this.buffer = b;
		this.port = p;
	}

	/*
	 * The network interface provides a channel for interaction between the nodes
	 * and the database. There is connection to the same port on the network for
	 * multiple clients, but only one can connect at a time. The server socket is
	 * waiting for requests to enter the network, processing the request and
	 * granting access by establishing a connection at a time with one of them,
	 * performing the relevant actions and then "returning the connection" to the
	 * requesting node by closing it.
	 */
	public void run() {

		// >>> create the socket the server (coordinator) will listen to
		try {
			s_socket = new ServerSocket(port); // socket creation connected to a specified port
			System.out.println("C:Receiver waiting for token request ...");
		} catch (IOException e) {
			System.out.println("Exception when creating a connection " + e);
		}

		while (true) {
			try {

				// >>> get a new connection
				// >>> create a separate thread to service the request, a C_Connection_r thread.
				socketFromNode = s_socket.accept(); // create socket node based on host address
				System.out.println("\n\n\n");
				System.out.println("	NEW CONNECTION\n\n\n");
				System.out.println("C:Receiver - Connection established in node with port number: " + port + ".");
				// Start the new connection
				System.out.println("C:Receiver - Coordinator has received a request from: " + socketFromNode + ".");
				C_Connection_r connection_c = new C_Connection_r(socketFromNode, buffer);
				Thread node_Connection = new Thread(connection_c);
				node_Connection.start();
			} catch (Exception e) {
				System.out.println("Exception when creating a connection " + e);
			}
		}
	}// end run
}
