
import java.util.*;
/*
 * Constantinos Constantinou
 * 2520796
 */

public class Hotel extends Thread{

    private Bookings service;
    private Random random = new Random();
    
    public Hotel (Bookings b) {
	service = b;
	random = new Random();
	
    }
    
	@Override
	public synchronized void run() {
		// implementation of synchronised run method in order to start running the thread from holidays class
		int i;
		for(i=0;i<=120;i++){
		    service.removeBooking();
		 
		    try{
			Thread.sleep(random.nextInt(100)); // This thread must sleep more time than the Agent thread in order for the buffer to never be empty
		    } catch (InterruptedException e){System.out.println(e.getMessage());}
		}
	}
}
