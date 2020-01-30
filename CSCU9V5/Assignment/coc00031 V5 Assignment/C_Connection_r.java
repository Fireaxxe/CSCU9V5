import java.net.*;
import java.io.*;

/**
 * 
 * @author 2520796 
 * This class reacts to a node request. It's job it to
 * receive and records each node requests in the buffer
 */
public class C_Connection_r extends Thread {

	// class variables
	C_buffer buffer;
	Socket s_socket;
	InputStream input_Stream;
	BufferedReader buffered_Reader;
	int port;

	/**
	 * 
	 * @param s -> socket
	 * @param b -> buffer
	 */
	public C_Connection_r(Socket s, C_buffer b) {
		this.s_socket = s;
		this.buffer = b;
	}

	public void run() {
		final int NODE = 0;
		final int PORT = 1;

		/**
		 * The "request" string array is holding the IP and the port number of each node
		 * that is requesting a token
		 */
		String[] request = new String[2];
		System.out.println("\n");
		System.out.println("C:Connection - Examine request from socket " + s_socket + ".");
		try {

			// >>> read the request, i.e. node IP and port from the socket s
			// >>> save it in a request object and save the object in the buffer (see
			// C_buffer's methods).
			input_Stream = s_socket.getInputStream();
			buffered_Reader = new BufferedReader(new InputStreamReader(input_Stream));

			/**
			 * The "out_request" string array receives the string from the node and split it
			 * into HOST and PORT
			 */
			String[] out_request = buffered_Reader.readLine().trim().split(" ");
			request[NODE] = out_request[0];
			request[PORT] = out_request[1];
			buffer.saveRequest(request);
			s_socket.close();
			System.out.println("\n");
			System.out.println("C:Connection - Successfully received and recorded the request from " + request[NODE]
					+ " : " + request[PORT] + " - (Socket Closed).");
			System.out.println("\n\n\n");
			System.out.println("	END CONNECTION\n\n\n");

		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	} // end of run() method
} // end of class
