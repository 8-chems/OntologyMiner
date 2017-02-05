package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.hp.hpl.jena.util.FileUtils;




public class Explorer extends JPanel{
	Object[][] data = {{"empty"}};
	String title[] = {"Results"};
	
	JPanel f=this;
	File[] owlf;
	
	File[] reqf;
	ArrayList<String>  prefal =new ArrayList<String>();
	JTextArea reqtf=new JTextArea("");
	JTextField pathtf=new JTextField(),preftf=new JTextField();
	String[] ss={"OWL_DL_MEM","OWL_DL_MEM_RDFS_INF","OWL_DL_MEM_RULE_INF",
			"OWL_DL_MEM_TRANS_INF","OWL_LITE_MEM",
			"OWL_LITE_MEM_RDFS_INF","OWL_LITE_MEM_RULES_INF","OWL_LITE_MEM_TRANS_INF",
			"OWL_MEM","OWL_MEM_MICRO_RULE_INF","OWL_MEM_MINI_RULE_INF",
			"OWL_MEM_RDFS_INF","OWL_MEM_RULE_INF","OWL_MEM_TRANS_INF",
			"RDFS_MEM","RDFS_MEM_RDFS_INF","RDFS_MEM_TRANS_INF","PelletReasonerFactory"};
	
	JRadioButton r1=new JRadioButton("Select"),r2=new JRadioButton("Construct"),
			     r3=new JRadioButton("Describe"),r4=new JRadioButton("Ask");
   
public	ButtonGroup r=new ButtonGroup(),rl=new ButtonGroup();
	
    
	JComboBox onttf =new JComboBox(ss);
	
	JButton reqjbch=new JButton("Laod"),reqjbex=new JButton("Perform"),owljb=new JButton("Laod Owl file")
	        ,objprop=new JButton("Associations"),cl=new JButton("Classes"),pr=new JButton("Propereties"),ins=new JButton("Instances")
	         ,enreg=new JButton("Save"),lire=new JButton("Read"),trans=new JButton("Transitive associtions"),
	         syme=new JButton("Symmetric associations"),he=new JButton("Ontology"),names=new JButton("Name spaces");
	
	JButton[] preftr={new JButton("Add"),new JButton("Remove"),new JButton("<<"),new JButton(">>")};
	
	ArrayList<JButton> lbt=new ArrayList<JButton>();
	
	JPanel req=new JPanel(),jtp=new JPanel(new BorderLayout()),
		   jtpp=new JPanel(),reqpref=new JPanel(),reqch=new JPanel(new BorderLayout()),
		   reqchf=new JPanel(),reqt=new JPanel(),contc=new JPanel(),
		   //////////////////////////////////////////////////////////////
		   head=new JPanel(),global=new JPanel(),left=new JPanel(),soutofreq=new JPanel(),bofreq=new JPanel(),
		   reqh=new JPanel(),prefpJPanel=new JPanel(),globalh=new JPanel(),globalr=new JPanel(),
		   globalsJPanel=new JPanel();
	JFileChooser jfc1=new JFileChooser(),jfc2= new JFileChooser(),jfc3= new JFileChooser();
    
	JLabel reql=new JLabel("Write your SparQL Query"),pathl=new JLabel("Path :",JLabel.RIGHT),
    	   ontl=new JLabel("Inference engine :"),prefl=new JLabel("Prefix :");
           Label resultat=new Label("Results ",Label.CENTER);
   

    DefaultTableModel tableModel=new DefaultTableModel();
    
    JTable res=new JTable(tableModel);
    
    String reqs="";int indice=0;
    
    list lisn=new list();
    list2 lisn2=new list2();
    
    QueryAndResult re;
    JTreeRemoveNode contg;
    ArrayList<JButton>  lb=new ArrayList<JButton>();
    ArrayList<String>  lq=new ArrayList<String>();
    
    JFrame frame;
 public Explorer(final JFrame frame){
	super();
	this.frame=frame;
	this.setVisible(true);
	

	this.setLayout(new BorderLayout());
	this.add(head,BorderLayout.NORTH);
	this.add(global);
	
	global.setLayout(new BorderLayout());
	global.add(req,BorderLayout.WEST);
	
	req.setLayout(new BorderLayout());
	req.add(soutofreq,BorderLayout.SOUTH);
	soutofreq.setLayout(new BoxLayout(soutofreq,BoxLayout.PAGE_AXIS));
	soutofreq.add(reqt);
	soutofreq.add(bofreq);
	reqh.setLayout(new BorderLayout());
	reqh.add(reql,BorderLayout.NORTH);
	
	globalh.setLayout(new BoxLayout(globalh, BoxLayout.PAGE_AXIS));
	globalh.add(prefpJPanel);
	globalh.add(reqch);
	globalh.setPreferredSize(new Dimension(0,120));
	reqch.setPreferredSize(new Dimension(0,90));
	global.add(globalr);
	globalr.setLayout(new BorderLayout());
	globalr.add(globalh,BorderLayout.NORTH);
	reqh.add(jtpp);
	req.add(reqh);
	
	globalsJPanel.setLayout(new BorderLayout());
	globalsJPanel.add(resultat,BorderLayout.NORTH);
	
	globalsJPanel.add(jtp);
	globalsJPanel.add(reqpref,BorderLayout.EAST);
	globalr.add(globalsJPanel);
	
	contg=new JTreeRemoveNode(re) ;
	
	
	
	globalsJPanel.add(contg,BorderLayout.WEST);
	
	
	jtpp.setLayout(new BorderLayout());
	
	
	jtpp.add(new JScrollPane(reqtf),BorderLayout.CENTER);
	
	
	r.add(r1);r.add(r2);r.add(r3);r.add(r4);r1.setSelected(true);

	req.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black)));
	
	bofreq.add(enreg);bofreq.add(reqjbch);bofreq.add(reqjbex);bofreq.add(enreg);
	req.setPreferredSize(new Dimension(300, 410));
		
	reqt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Type of Query"));
	reqt.add(r1);reqt.add(r2);reqt.add(r3);reqt.add(r4);
	head.setLayout(new BoxLayout(head, BoxLayout.X_AXIS));
	owljb.setPreferredSize(new Dimension(200,30));head.add(owljb);
	pathl.setPreferredSize(new Dimension(143,30));head.add(pathl);
	pathtf.setPreferredSize(new Dimension(120,30));head.add(pathtf);
	ontl.setPreferredSize(new Dimension(120,30));head.add(ontl);
	onttf.setPreferredSize(new Dimension(200,30));head.add(onttf);
	lire.setPreferredSize(new Dimension(120,30));head.add(lire);
		
	prefl.setBackground(Color.red);
	prefpJPanel.setLayout(new BoxLayout(prefpJPanel, BoxLayout.X_AXIS));
	this.prefpJPanel.add(prefl);
	prefl.setAlignmentX(0);
	this.prefpJPanel.add(preftf);
	preftf.setPreferredSize(new Dimension(400,30));
	preftf.setAlignmentX(prefpJPanel.LEFT_ALIGNMENT);
	for(int i=0;i<4;i++){
		lbt.add(preftr[i]);
		preftr[i].addActionListener(lisn2);
	    this.prefpJPanel.add(preftr[i]);
	}
		
	resultat.setBackground(Color.gray);
		
	reqchf.setLayout(new BoxLayout(reqchf,BoxLayout.LINE_AXIS));
	
	reqch.add(new JScrollPane(reqchf),BorderLayout.CENTER);
	reqch.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Queries"));
    	
	jtp.add(new JScrollPane(res), BorderLayout.CENTER);

	reqpref.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Prepared Queries"));
	reqpref.setLayout(new BoxLayout(reqpref,BoxLayout.PAGE_AXIS));
	cl.setAlignmentX(reqpref.CENTER_ALIGNMENT-cl.getWidth()/2);
	reqpref.add(cl);
	pr.setAlignmentX(reqpref.CENTER_ALIGNMENT-cl.getWidth()/2);
	reqpref.add(pr);
	ins.setAlignmentX(reqpref.CENTER_ALIGNMENT-cl.getWidth()/2);
	reqpref.add(ins);
	objprop.setAlignmentX(reqpref.CENTER_ALIGNMENT-cl.getWidth()/2);
	reqpref.add(objprop);
	syme.setAlignmentX(reqpref.CENTER_ALIGNMENT-cl.getWidth()/2);
	reqpref.add(syme);
	trans.setAlignmentX(reqpref.CENTER_ALIGNMENT-cl.getWidth()/2);
	reqpref.add(trans);
	he.setAlignmentX(reqpref.CENTER_ALIGNMENT-cl.getWidth()/2);
	reqpref.add(he);
	names.setAlignmentX(reqpref.CENTER_ALIGNMENT-cl.getWidth()/2);
	reqpref.add(names);
	
   

	jfc3.setFileFilter(
			new javax.swing.filechooser.FileFilter(){
			public boolean accept(File f){
			String fname = f.getName().toLowerCase();
			return fname.endsWith(".sp") || f.isDirectory();
			}
			public String getDescription(){
				return "Sparql files";
				}});

	
	jfc1.setFileFilter(
	new javax.swing.filechooser.FileFilter(){
	public boolean accept(File f){
	String fname = f.getName().toLowerCase();
	return fname.endsWith(".owl") || f.isDirectory();
	}
	public String getDescription(){
	return "OWL files";
	}});
	
	
	jfc2.setMultiSelectionEnabled(true);
	jfc2.setFileFilter(
	new javax.swing.filechooser.FileFilter()
	{
	public boolean accept(File f){
	String fname = f.getName().toLowerCase();
	return fname.endsWith(".sp") || f.isDirectory();
	}
	public String getDescription(){
	return "sparql files";
	}
	});
	
this.repaint();
		
	///////////////////////////////interaction button/////////////////////////////////////////////////////
	
	lire.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			re=null;
	try{	
		re=new QueryAndResult(onttf.getSelectedIndex());
		re.setpaths(owlf); 
    	
		
		if(contg!=null){globalsJPanel.remove(contg);contg=null;}
	    contg =new JTreeRemoveNode(re);
	    globalsJPanel.add(contg,BorderLayout.WEST);
	    SwingUtilities.updateComponentTreeUI(frame);
	}catch(Exception e){new JOptionPane().showMessageDialog(f, "Detail:\n *file type isn't Owl \n *no File.","Error",JOptionPane.ERROR_MESSAGE);};	
	
	}});
	
	
	
	

	cl.addActionListener(new ActionListener(){
       @Override
		public void actionPerformed(ActionEvent arg0) {
		re.getclasses(res,0);	
		}});
	pr.addActionListener(new ActionListener(){
	       @Override
			public void actionPerformed(ActionEvent arg0) {
			re.getclasses(res,1);	
			}});
	ins.addActionListener(new ActionListener(){
	       @Override
			public void actionPerformed(ActionEvent arg0) {
			re.getclasses(res,2);	
			}});
	he.addActionListener(new ActionListener(){
	       @Override
			public void actionPerformed(ActionEvent arg0) {
			re.getclasses(res,6);	
			}});
	
	objprop.addActionListener(new ActionListener(){
	       @Override
			public void actionPerformed(ActionEvent arg0) {
			re.getclasses(res,3);	
			}});
	syme.addActionListener(new ActionListener(){
	       @Override
			public void actionPerformed(ActionEvent arg0) {
			re.getclasses(res,4);	
			}});
	names.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			re.getclasses(res, 7);
			
		}
		
	});
	trans.addActionListener(new ActionListener(){
	       @Override
			public void actionPerformed(ActionEvent arg0) {
			re.getclasses(res,5);	
			}});
	reqjbch.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
	jfc2.setCurrentDirectory(new File("e:/"));		
	int r = jfc2.showOpenDialog(f);
	
	if(r==0){reqf = jfc2.getSelectedFiles();
   
	try{for(int i=0;i<reqf.length;i++){
	 lq.add(FileUtils.readWholeFileAsUTF8(new FileInputStream(reqf[i])));
	 lb.add(new JButton(reqf[i].getName().split("\\.")[0]));
	 lb.get(lb.size()-1).addActionListener(lisn);
	 reqchf.add(lb.get(lb.size()-1));
	 f.validate();f.repaint();
    
}

    }catch(Exception e){}
	}
    reqtf.setText(reqs);		
		}});
	

	
	enreg.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
		//jfc3.setSelectedFile(new File(reqtf.getText().substring(0, 10)+".txt"));
			jfc3.setCurrentDirectory(new File("e:/les requete"));	
		int rr=jfc3.showSaveDialog(f);
		
		 if (rr == JFileChooser.APPROVE_OPTION) {
		 File file = jfc3.getSelectedFile();
		int ii=0;
		if(r1.isSelected()) ii=1;
		  else if(r2.isSelected())ii=2;
		         else if(r3.isSelected()) ii=3;
		            else ii=4;
		BufferedWriter writer = null;
			try
			{
			    writer = new BufferedWriter( new FileWriter( file.getPath()+".sp"));
			    PrintStream out = null;
			        out = new PrintStream(new FileOutputStream(file.getPath()+".sp"));
			  
			        out.print(ii+reqtf.getText());
			  if (out != null) out.close();
			  writer.close();
		} catch (IOException e) {e.printStackTrace();}
		
		 }
		}});
	
	owljb.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
	jfc1.setCurrentDirectory(new File("e:/"));
	int r = jfc1.showOpenDialog(f);
	
if(r==0){	
	owlf = jfc1.getSelectedFiles();
	String s="";
for(int i=0;i<owlf.length;i++){	
	s=owlf[i].getPath()+":";
}
pathtf.setText(s);
}	
		}});
	
	reqjbex.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
   if(re!=null){
   re.setreq(reqtf.getText());
        if(r1.isSelected()){ requete(1);System.out.println("r1");}
        else if(r2.isSelected()){requete(2);System.out.println("r2");}
        else if(r3.isSelected()){requete(3);System.out.println("r3");}
        else if(r4.isSelected()){requete(4);System.out.println("r4");}        
}
}});
	
}
 public class list implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		reqs=lq.get(lb.indexOf(arg0.getSource()));
	    reqtf.setText(reqs.substring(1));
		re.setreq(reqs.substring(1));
	    requete(Integer.parseInt(reqs.substring(0,1)));
	}
}

 public void requete(int i){
	 boolean b=true;
	try{
	 switch (i){
	 case 1:{b=re.exuteq(res);r1.setSelected(true);System.out.println("erequete 1");break;}
	 case 2:{b=re.exconst();r2.setSelected(true);
	         System.out.println("erequete 2");
	         contg.setsel();
	         contg.addnode();
	         break;
	 }
	 case 3:{b=re.exdes();
	 r3.setSelected(true);
	 System.out.println("erequete 3");contg.setsel();
	 contg.addnode();
	 break;}
	 case 4:{boolean t =re.exask();r4.setSelected(true);
	         System.out.println("erequete 4");
	         String s="La Reponce de Votre Requete est : "+t;
	        
	        	 new JOptionPane().showMessageDialog(f, s, "ASk Query anwser is :",JOptionPane.INFORMATION_MESSAGE);
	          
	         
	         break;}
	 }
	 
	 if(!b){new JOptionPane().showMessageDialog(f,"empty result or can't run.","Information",JOptionPane.INFORMATION_MESSAGE);}
	}catch(Exception e){new JOptionPane().showMessageDialog(f,"Erreur:!qyery type.","Error",JOptionPane.ERROR_MESSAGE);e.printStackTrace();}
 }
 
 ////////////////////////////////inner class//////////////////////////////
 public class list2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int i= lbt.indexOf(arg0.getSource());
			contg.addnode();
			switch(i){
			case 0:{prefal.add(preftf.getText());preftf.setText("");indice++;break;}
			case 1:{if(prefal.size()>0){prefal.remove(indice);if(indice>0)indice--;if(prefal.size()!=0)preftf.setText(prefal.get(indice));else preftf.setText(""); }break;}
			case 3:{if(indice<prefal.size()-1){indice++;preftf.setText(prefal.get(indice));contg.addnode();}break;}
			case 2:{if(indice>0){indice--;preftf.setText(prefal.get(indice));}break;}
			}
			
		}
 
 
}
 }