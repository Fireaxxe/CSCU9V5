import java.util.*;

/*
 * Constantinos Constantinou
 * 2520796
 */
public class Agent extends Thread {
	private Random random;
	private Bookings service;
	public final String me;

	public Agent(Bookings b, String string) {
		service = b;
		random = new Random();
		this.me = string;
	}

	public synchronized void run() {
		// implementation of synchronised run method in order to start running the thread from holidays class
		for (int i = 120; i > 0; i--) {
			// i-th booking is recorded as the time of the booking arrival
			//
			Date b = new Date();
			service.addBooking(b);
			
			try {
				Thread.sleep(random.nextInt(50)); // This thread must sleep less time than the Hotel thread in order for the buffer to never be empty
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}

		}
	}
}
