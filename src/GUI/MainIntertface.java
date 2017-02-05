package GUI;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MainIntertface extends JFrame{
	private static final long serialVersionUID = 1L;
	 
   public MainIntertface(){
	//	this.add

	this.setTitle("Ontology Miner");
	this.setSize(900,700);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	this.setJMenuBar(this.menuBarCreator());
	this.setFocusTraversalKeysEnabled(true);
	CardLayout card=new CardLayout();
	this.getContentPane().setLayout(card);
    this.add(new Explorer(null));
	
	
    
/*addComponentListener(new ComponentAdapter() {
	@Override
	public void componentResized(ComponentEvent e) {				
		EventQueue.invokeLater(new Runnable()
	    {
	        public void run()
	        {   d.validate();
	            d.repaint();
	        }
	    });
	}});*/

	}
	
	public   JMenuBar menuBarCreator() {
	    // create the menu parts
	    JMenuBar menuBar = new JMenuBar();
	    JMenu menuFile = new JMenu("File");
	    JMenu menuHelp = new JMenu("Help");
	    
	    JMenuItem menuFileExit = new JMenuItem(new AbstractAction("Exit") {
	        public void actionPerformed(ActionEvent e) {
	            // Button pressed logic goes here
	        }
	    });
	    
	    JMenuItem help = new JMenuItem(new AbstractAction("help") {
	        public void actionPerformed(ActionEvent e) {
	          
	        }
	    });
	    JMenuItem MenuFileOpen=new JMenuItem(new AbstractAction("Open") {
	        public void actionPerformed(ActionEvent e) {
	            // Button pressed logic goes here
	        }
	    });
	    JMenuItem MenuFileSave=new JMenuItem(new AbstractAction("Save") {
	        public void actionPerformed(ActionEvent e) {
	            // Button pressed logic goes here
	        }
	    });
	    JMenuItem menuHelpAbout = new JMenuItem(new AbstractAction("About") {
	        public void actionPerformed(ActionEvent e) {
	            // Button pressed logic goes here
	        }
	    });
	  

	    
	    menuFile.setMnemonic(KeyEvent.VK_F);
	    menuHelp.setMnemonic(KeyEvent.VK_H);

	    
	    menuBar.add(menuFile);
	    menuBar.add(menuHelp);
	    menuFile.add(MenuFileOpen);
	    menuFile.add(MenuFileSave);
	    menuFile.add(menuFileExit);
	    
	     menuHelp.add(help); 
	     menuHelp.add(menuHelpAbout);
	  
	    return menuBar;
	}

	
public static void main(String[] ar){

	try {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		 UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		 
		 new MainIntertface();
		 
		 //com.sun.java.swing.plaf.gtk.GTKLookAndFeel
		 //com.sun.java.swing.plaf.motif.MotifLookAndFeel
		 //com.sun.java.swing.plaf.windows.WindowsLookAndFeel
		 
		}catch (UnsupportedLookAndFeelException e) {}
         catch (ClassNotFoundException e) {}
         catch (InstantiationException e) {}
         catch (IllegalAccessException e) {}
	
}
}