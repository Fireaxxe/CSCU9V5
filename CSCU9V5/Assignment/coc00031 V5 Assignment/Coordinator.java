import java.net.*;

/**
 * 
 * @author 2520796
 *
 */
public class Coordinator {

	public static void main(String args[]) {

		C_buffer buffer = new C_buffer();
		int port = 7000;

		/**
		 * Gets coordinator's (server) address and name
		 */
		try {
			InetAddress coordinator_address = InetAddress.getLocalHost(); // get address
			String coordinator_name = coordinator_address.getHostName(); // get name
			System.out.println("Coordinator address is " + coordinator_address);
			System.out.println("Coordinator host name is " + coordinator_name + "\n\n");
		} catch (Exception e) {
			System.err.println(e);
			System.err.println("Error in coordinator");
		}
		/**
		 * Define port
		 */
		if (args.length == 1)
			port = Integer.parseInt(args[0]);

		// Create and run a C_receiver and a C_mutex object sharing a C_buffer object
		C_receiver receiver_c = new C_receiver(buffer, port);
		Thread receiverThread = new Thread(receiver_c); // instantiate receiver thread

		C_mutex mutex_c = new C_mutex(buffer, 7001);
		Thread mutex_thread = new Thread(mutex_c);
		/*
		 * The Mutex acts like a semaphore but with less threads. It receives threads,
		 * takes in requests and put them in a queue to executed.
		 */
		receiverThread.start();
		mutex_thread.start();
	}
}
