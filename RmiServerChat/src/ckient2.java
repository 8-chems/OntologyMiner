

import java.net.InetAddress;
import java.rmi.Naming;
import java.util.Scanner;

public class ckient2 {

	public ckient2() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 try {
	    	  String url = "rmi://" + InetAddress.getLocalHost().getHostAddress()
	    			   + "/ChatRMI";
	         ReceaverObjectIntrf irmi=(ReceaverObjectIntrf) Naming.lookup(url);
	         ReturedObjectIntrf roi=( ReturedObjectIntrf)irmi.getCible();
	         roi.Connect("chamsi","0000");
	         roi.CreateConv("2");
	         roi.SetMessage("2", "hello");
	         System.out.println(roi.getMembersList());
	         
	         
		 }catch(Exception e){e.printStackTrace();}

	}

}
