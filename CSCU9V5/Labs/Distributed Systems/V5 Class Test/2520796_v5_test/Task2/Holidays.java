
import java.util.*;
/*
 * Constantinos Constantinou
 * 2520796
 */

public class Holidays {



    public static void  main(String[] args){
	
	 Bookings b = new Bookings();
	 //int size = 10;
     Hotel hotel1 = new Hotel(b,"Hotel 1");//create thread
     Agent agent1 = new Agent(b,"Agent 1");//create thread
     Hotel hotel2 = new Hotel(b,"Hotel 2");//create thread
     Agent agent2 = new Agent(b,"Agent 2");
   
     hotel1.start();//start the thread
     agent1.start();//start the thread
     hotel2.start();//start the thread
     agent2.start();//start the thread


    }

}
