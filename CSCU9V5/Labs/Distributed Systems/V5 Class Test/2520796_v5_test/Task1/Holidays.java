
import java.util.*;
/*
 * Constantinos Constantinou
 * 2520796
 */

public class Holidays {



    public static void  main(String[] args){
	
	 Bookings b = new Bookings();
	 //int size = 10;
     Hotel hotel1 = new Hotel(b);//create thread
     Agent agent1 = new Agent(b);//create thread
   
     hotel1.start();//start the thread
     agent1.start();//start the thread

    }

}
