import java.net.*;

/**
 * 
 * @author 2520796
 *
 */
public class C_mutex extends Thread {

	C_buffer buffer;
	int port;
	String n_host;
	int n_port;

	/**
	 * 
	 * @param b -> buffer
	 * @param p -> port
	 */
	public C_mutex(C_buffer b, int p) {

		this.buffer = b;
		this.port = p;
	}

	// Calls c_mutex
	public void go() {

		try {
			// >>> Listening from the server socket on port 7001
			// from where the TOKEN will be later on returned.
			// This places the server creation outside the while loop.
			ServerSocket ss_back = new ServerSocket(port);

			while (true) {
				// >>> Print some info on the current buffer content for debugging purposes.
				// >>> please look at the available methods in C_buffer
				/**
				 * If the buffer is not empty then:
				 */
				if (buffer.size() != 0) {

					// >>> Getting the first (FIFO) node that is waiting for a TOKEN from the buffer
					// Type conversions may be needed.
					n_host = buffer.get().toString();
					n_port = Integer.parseInt((buffer.get().toString()));

					System.out.printf("C:mutex Giving token to " + n_host + ":"+ n_port + ".");

					// >>> **** Granting the token
					try {
						Socket s_connected = new Socket(n_host, n_port);
						// send message to s_connected that token has been given
						System.out.printf("C:mutex TOKEN given to " + n_host + ":"+ n_port + ". \n");

					} catch (Exception e) {
						System.out.println(e);
						System.out.println("CRASH Mutex connecting to the node for granting the TOKEN." + e);
					}

					// >>> **** Getting the token back
					try {
						// THIS IS BLOCKING !
						/**
						 * Waiting for a reply from s_connected
						 * If connection dies then we can assume the token will return
						 */
						ss_back.accept();
						System.out.println("C:mutex TOKEN given back to coordinator.");
					} catch (Exception e) {
						System.out.println(e);
						System.out.println("CRASH Mutex waiting for the TOKEN back" + e);
					}
				} // endif
			} // endwhile
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void run() {
		go();
	}
}
