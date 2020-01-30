import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class connection extends Thread {
	private int outputLine;
	//1573750127414_Node


	public connection(int previous) {
		outputLine = previous;
	}

	public void run() {

		try
		{		
			try{Thread.sleep(3000);}
			catch (InterruptedException e) {e.printStackTrace();}
			
			
			Date timestmp = new Date() ; 
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String timestamp = formatter.format(timestmp);
			//int this_port = outputLine.getPort();
			FileWriter fw_id = new FileWriter("record.txt",true); // Create PrintWriter -true = flush buffer on each println // println means adds a newline (as distinct from print)
			PrintWriter pw_id = new PrintWriter(fw_id, true) ; 
			pw_id.println ("Port number " +outputLine+ ", is " +timestamp);
			pw_id.close() ;
			fw_id.close() ;
			
		}
		catch(IOException e)
		{
			System.out.println("something wrong with file access" + e);
		}
	}
}
