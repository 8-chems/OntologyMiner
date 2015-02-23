
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JApplet;

import net.ucanaccess.console.Main;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;




public class client1 {
    client1  ff;
	public client1() {
		ff=this;
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
	         roi.Connect("hamdi","1111");
	         System.out.println(roi.getMessage("1"));
	         System.out.println(roi.GetConv());
	         
		 }catch(Exception e){e.printStackTrace();}
/*DBConnection bb=new DBConnection();
bb.Deconnecter("hamdi", "1111");
System.out.println(bb.verify("hamdi","1111"));
System.out.println(bb.verify("hamdi","1111"));*/

		
	}
	
	

}
