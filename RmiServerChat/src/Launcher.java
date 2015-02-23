import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalIconFactory;


public class Launcher extends JFrame{
    TrayIcon trayIcon;
    SystemTray tray;
    Server s=new Server();
    JButton jb1=new JButton("Demarrer"),jb2=new JButton("Arrete");
    Label jl1=new Label(),jl2=new Label("IP :");
    boolean b=false;
    TrayIcon icon;
    JTextField jtx1=new JTextField();
    Launcher(){
        super("Etat de serveur");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLocation(500, 250);
        this.setVisible(true);
        this.getContentPane().setLayout(null);
        this.addWindowStateListener(new WindowStateListener() {

			public void windowStateChanged(WindowEvent arg0) {
			  if(((JFrame)(arg0.getSource())).getState()==Frame.ICONIFIED) setVisible(false);	
			}});
        jb1.setBounds(20, 100, 120, 30);
        jb2.setBounds(150, 100, 120, 30);
        jl1.setBounds(130, 10, 40, 30);
        jl1.setBackground(Color.RED);
        jl2.setBounds(60, 55, 20, 20);
        jtx1.setBounds(80, 50, 140, 30);
        jtx1.setEditable(false);
        icon = new TrayIcon(getImage(), "Java application as a tray icon", 
	            createPopupMenu());
        
try {      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	        } 
	        catch (UnsupportedLookAndFeelException e) {}
	        catch (ClassNotFoundException e) {}
	        catch (InstantiationException e) {}
	        catch (IllegalAccessException e) {}
        icon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              setVisible(true);
            }
         });
        try {
			SystemTray.getSystemTray().add(icon);
		} catch (AWTException e) {e.printStackTrace();}
        
        jb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            if(!b){	
            	try {
					jtx1.setText(InetAddress.getLocalHost().getHostAddress());
					s.Lancer();
					b=true;
					jl1.setBackground(Color.GREEN);
					setVisible(false);
					icon.displayMessage("Information", "Le serveur est bien demarrer sur l'IP :"+jtx1.getText(), 
				            TrayIcon.MessageType.INFO);
				} catch (UnknownHostException e) {e.printStackTrace();}
               }}});
        
        jb2.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent arg0) {
		if(b){	
        	s.Stopper();
			b=false;
			jtx1.setText("127.0.0.1");
			jl1.setBackground(Color.RED);
		}}
          });
        this.getContentPane().add(jb1);this.getContentPane().add(jb2);
        this.getContentPane().add(jl1);this.getContentPane().add(jl2);
        this.getContentPane().add(jtx1);
    
   
        this.setVisible(true);
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
    
    
    }
    
    public Image getImage() throws HeadlessException {
	      Icon defaultIcon = MetalIconFactory.getTreeHardDriveIcon();
	      Image img = new BufferedImage(defaultIcon.getIconWidth(), 
	            defaultIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	      defaultIcon.paintIcon(new Panel(), img.getGraphics(), 0, 0);

	      return img;
	   }
   public PopupMenu createPopupMenu() throws HeadlessException {
	      PopupMenu menu = new PopupMenu();

	      MenuItem exit = new MenuItem("Exit");
	      MenuItem ouvrir = new MenuItem("Ouvrir");
	      exit.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            System.exit(0);
	         }
	      });
	      ouvrir.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		           setVisible(true);
		           setState(Frame.MAXIMIZED_BOTH);
		         }
		      });
	      menu.add(exit);
	      menu.add(ouvrir);

	      return menu;
	   }
    public static void main(String[] args){
        new Launcher();
    }
}