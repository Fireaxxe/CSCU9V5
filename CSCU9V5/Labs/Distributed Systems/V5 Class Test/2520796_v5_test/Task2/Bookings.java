
import java.util.ArrayList;
/*
 * Constantinos Constantinou
 * 2520796
 */
public class Bookings {

    ArrayList<Object> buffer;
    int size = 5;
    
    public Bookings() {
	buffer = new ArrayList<Object>();
    }
    
   public Object removeBooking (){
	
	Object item = null;
	
	
	 //wait if queue is empty
    if (buffer.isEmpty()) {
        synchronized (buffer) {
            System.out.println("Queue is empty " + Thread.currentThread().getName()
                                + " is waiting , size: " + buffer.size());

            try {
				buffer.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    else {
    	//Otherwise consume element and notify waiting producer
    	synchronized (buffer) {
    		System.out.println("Accepted! There were " + buffer.size() + " bookings in the buffer. ");
    		item = buffer.get(0);
            buffer.notifyAll();
            buffer.remove(0);
    }

    }
    return item;
}
	/*
	if(buffer.size()==0) {
		System.out.println("No bookings at the moment " + Thread.currentThread().getName() + " is waiting, size " + buffer.size());
	}else {
	    System.out.println("Accepted! There were " + buffer.size() + " bookings in the buffer. ");
	    item = buffer.get(0);
	    buffer.remove(0);
	}
	return item;
    }
    */


    public void addBooking (Object item) {
        //wait if queue is full
        while (buffer.size() == size) {
            synchronized (buffer) {
                System.out.println("Queue is full " + Thread.currentThread().getName()
                                    + " is waiting , size: " + buffer.size());

                try {
					buffer.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }

        //producing element and notify consumers
        synchronized (buffer) {
            buffer.add(item);
            System.out.println("New booking. There are  " + buffer.size() + " bookings. ");
            buffer.notifyAll();
        }
    	
    	
    	
	//buffer.add(item);
	//System.out.println("New booking. There are  " + buffer.size() + " bookings. ");
    }
    
    /*
    public void addBooking (Object item) {
	buffer.add(item);
	System.out.println("New booking. There are  " + buffer.size() + " bookings. ");
    }
     */    
    
}
