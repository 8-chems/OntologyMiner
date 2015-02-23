import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;


public class main {
	static  String NL = System.getProperty("line.separator") ;  
	static Logger myLogger = Logger.getLogger(gui.class.getName());  
	static Appender myAppender; 
	public static void main(String args[]){
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			 UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			 
			 new gui();
			 myLogger.setLevel(Level.ALL);
			  myAppender = new ConsoleAppender(new SimpleLayout()); 
			  myLogger.addAppender(myAppender);
			 
			 //com.sun.java.swing.plaf.gtk.GTKLookAndFeel
			 //com.sun.java.swing.plaf.motif.MotifLookAndFeel
			 //com.sun.java.swing.plaf.windows.WindowsLookAndFeel
			 
			}catch (UnsupportedLookAndFeelException e) {}
	         catch (ClassNotFoundException e) {}
	         catch (InstantiationException e) {}
	         catch (IllegalAccessException e) {}
			

}

}
