import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;


import java.util.ArrayList;
import java.util.Arrays;

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
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.util.FileUtils;




public class gui extends JFrame{
	Object[][] data = {{"Vide"}};
	String title[] = {"Les Resultats"};
	
	JFrame f=this;
	File owlf;
	File[] reqf;
	ArrayList<String>  prefal =new ArrayList<String>();
	JTextArea reqtf=new JTextArea("");
	JTextField pathtf=new JTextField(),preftf=new JTextField();
	String[] ss={"OWL_DL_MEM","OWL_DL_MEM_RDFS_INF","OWL_DL_MEM_RULE_INF",
			"OWL_DL_MEM_TRANS_INF","OWL_LITE_MEM",
			"OWL_LITE_MEM_RDFS_INF","OWL_LITE_MEM_RULES_INF","OWL_LITE_MEM_TRANS_INF",
			"OWL_MEM","OWL_MEM_MICRO_RULE_INF","OWL_MEM_MINI_RULE_INF",
			"OWL_MEM_RDFS_INF","OWL_MEM_RULE_INF","OWL_MEM_TRANS_INF",
			"RDFS_MEM","RDFS_MEM_RDFS_INF","RDFS_MEM_TRANS_INF"};
	
	JRadioButton r1=new JRadioButton("Select"),r2=new JRadioButton("Construct"),
			     r3=new JRadioButton("Discript"),r4=new JRadioButton("Ask");
   
public	ButtonGroup r=new ButtonGroup(),rl=new ButtonGroup();
	
    
	JComboBox onttf =new JComboBox(ss);
	
	JButton reqjbch=new JButton("Charger"),reqjbex=new JButton("Exucuter"),owljb=new JButton("Charger Ficher owl")
	        ,objprop=new JButton("Associations"),cl=new JButton("CLasses"),pr=new JButton("Propereté"),ins=new JButton("Instance")
	         ,enreg=new JButton("Enregistrer"),lire=new JButton("Lire"),trans=new JButton("les Associations Transitives"),
	         syme=new JButton("Les Associations Symetriques"),he=new JButton("Ontology"),names=new JButton("L'espaces ds Noms");
	
	JButton[] preftr={new JButton("Ajtouter"),new JButton("Supprimer"),new JButton("<<"),new JButton(">>")};
	
	ArrayList<JButton> lbt=new ArrayList<JButton>();
	
	JPanel req=new JPanel(),jtp=new JPanel(new BorderLayout()),
		   jtpp=new JPanel(),reqpref=new JPanel(),reqch=new JPanel(new BorderLayout()),
		   reqchf=new JPanel(),reqt=new JPanel(),contc=new JPanel();
	JFileChooser jfc1=new JFileChooser(),jfc2= new JFileChooser(),jfc3= new JFileChooser();
    
	JLabel reql=new JLabel("Ecrit Votre Requete SparQl"),pathl=new JLabel("Repertoire :"),
    	   ontl=new JLabel("Motor D'Inference :"),prefl=new JLabel("Les Prefixes :");
           Label resultat=new Label("Les Resultats ",Label.CENTER);
   

    DefaultTableModel tableModel=new DefaultTableModel();
    
    JTable res=new JTable(tableModel);
    
    String reqs="";int indice=0;
    
    list lisn=new list();
    list2 lisn2=new list2();
    
    requeteandres re;
    JTreeRemoveNode contg;
    ArrayList<JButton>  lb=new ArrayList<JButton>();
    ArrayList<String>  lq=new ArrayList<String>();
    
    
 public gui(){
	super();
    this.setSize(1100, 540);
	this.setVisible(true);
	this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("Application D'extraction des données Owl");
	
	this.getContentPane().setLayout(null);
	
	
	contg=new JTreeRemoveNode(re) ;
	
	reql.setSize(300,15);
	reql.setLocation(50,8);
	
	
	jtpp.setLayout(new BorderLayout());
	
	jtpp.setSize(290,300);
	jtpp.setLocation(5,25);
	jtpp.add(new JScrollPane(reqtf),BorderLayout.CENTER);
	
	reqjbch.setSize(90,30);
	reqjbch.setLocation(5,380);
	
	reqjbex.setSize(90,30);
	reqjbex.setLocation(100,380);
	
	enreg.setSize(100,30);
	enreg.setLocation(195,380);

	
	r.add(r1);r.add(r2);r.add(r3);r.add(r4);r1.setSelected(true);

	req.setSize(300, 410);
	req.setLocation(0,60);
	req.setLayout(null);
	req.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black)));
	
	req.add(reql);req.add(jtpp);req.add(reqjbch);req.add(reqjbex);req.add(enreg);
	req.setSize(300, 410);
	
	reqt.setSize(290, 50);
	reqt.setLocation(5,325);
	reqt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Type de Requete"));
	reqt.add(r1);reqt.add(r2);reqt.add(r3);reqt.add(r4);
	req.add(reqt);
	
	owljb.setSize(150,30);
	owljb.setLocation(100, 20);
	
	pathl.setSize(150,20);
	pathl.setLocation(270, 20);
	
	pathtf.setSize(200,30);
	pathtf.setLocation(350, 20);
	
	ontl.setSize(160,30);
	ontl.setLocation(560, 20);
	
	lire.setSize(80,30);
	lire.setLocation(900, 20);
	
	onttf.setSize(200,30);
	onttf.setLocation(690, 20);
	
	prefl.setSize(100,30);
	prefl.setLocation(330, 60);
	
	prefl.setBackground(Color.red);
	
	preftf.setSize(300,30);
	preftf.setLocation(410, 60);
	for(int i=0;i<4;i++){
		lbt.add(preftr[i]);
		preftr[i].addActionListener(lisn2);
	preftr[i].setSize(95,30);
	preftr[i].setLocation(710+92*i, 60);
	this.getContentPane().add(preftr[i]);
	}
	
	resultat.setSize(750, 20);
	resultat.setBackground(Color.gray);
	resultat.setLocation(340, 170);
	
	reqch.setSize(750,80);
	reqch.setLocation(340, 85);
	reqchf.setLayout(new BoxLayout(reqchf,BoxLayout.LINE_AXIS));
	
	reqch.add(new JScrollPane(reqchf),BorderLayout.CENTER);
	reqch.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Les Requetes"));

	jtp.setSize(460, 270);
	jtp.setLocation(425, 200);
	jtp.add(new JScrollPane(res), BorderLayout.CENTER);
	
	reqpref.setSize(190, 270);
	reqpref.setLocation(900, 200);
	reqpref.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Requete Preparé"));
	
	reqpref.add(cl);reqpref.add(pr);reqpref.add(ins);reqpref.add(objprop);reqpref.add(syme);reqpref.add(trans);reqpref.add(he);reqpref.add(names);
	
   
	
	this.getContentPane().add(owljb);this.getContentPane().add(pathl);this.getContentPane().add(pathtf);
	this.getContentPane().add(ontl);this.getContentPane().add(onttf);this.getContentPane().add(prefl);
	this.getContentPane().add(preftf);this.getContentPane().add(reqpref);this.getContentPane().add(lire);
	this.getContentPane().add(req);this.getContentPane().add(jtp);this.getContentPane().add(resultat);
	this.getContentPane().add(reqch);this.getContentPane().add(contg);
	
	

	
    

	//
	jfc3.setFileFilter(
			new javax.swing.filechooser.FileFilter(){
			public boolean accept(File f){
			String fname = f.getName().toLowerCase();
			return fname.endsWith(".sp") || f.isDirectory();
			}
			public String getDescription(){
				return "Fichiers Sparql";
				}});

	
	jfc1.setFileFilter(
	new javax.swing.filechooser.FileFilter(){
	public boolean accept(File f){
	String fname = f.getName().toLowerCase();
	return fname.endsWith(".owl") || f.isDirectory();
	}
	public String getDescription(){
	return "Fichiers OWL";
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
	return "Fichiers Sparql";
	}
	});
	
/*	this.validate();*/this.repaint();
	
	
	
	
	///////////////////////////////interaction button/////////////////////////////////////////////////////
	
	lire.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			re=null;
	try{	
		re=new requeteandres(onttf.getSelectedIndex());
		re.setpath(owlf.getPath().toLowerCase());   
	if(contg!=null){f.remove(contg);contg=null;}
	contg =new JTreeRemoveNode(re);
	f.getContentPane().add(contg);	
	//f.validate();f.repaint();
	SwingUtilities.updateComponentTreeUI(f);
	}catch(Exception e){new JOptionPane().showMessageDialog(f, "Detail:\n *Fichier non OWL entrer \n *Pas De Fichier.","Error",JOptionPane.ERROR_MESSAGE);};	
	
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
	owlf = jfc1.getSelectedFile();
	pathtf.setText(owlf.getPath());


			
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
	 case 2:{b=re.exconst();r2.setSelected(true);System.out.println("erequete 2");contg.setsel();contg.addnode();break;}
	 case 3:{b=re.exdes();
	 r3.setSelected(true);
	 System.out.println("erequete 3");contg.setsel();
	 contg.addnode();break;}
	 case 4:{boolean t =re.exask();r4.setSelected(true);
	         System.out.println("erequete 4");
	         String s="La Reponce de Votre Requete est : "+t;
	        
	        	 new JOptionPane().showMessageDialog(f, s, "Reponce de Requete ASk",JOptionPane.INFORMATION_MESSAGE);
	          
	         
	         break;}
	 }
	 
	 if(!b){new JOptionPane().showMessageDialog(f,"Resultat Vide Ou execution IMpossible.","INformation",JOptionPane.INFORMATION_MESSAGE);}
	}catch(Exception e){new JOptionPane().showMessageDialog(f,"Errur:Type de requete.","Error",JOptionPane.ERROR_MESSAGE);e.printStackTrace();}
 }
 
 ////////////////////////////////inner class//////////////////////////////
 public static  class JTreeRemoveNode extends JPanel { 
	 
	   JTree tree;int indice=0;
	   requeteandres re;
	   JTreeRemoveNode jp=this; 
	   DefaultMutableTreeNode root;
	   ArrayList<DefaultMutableTreeNode>  lm=new ArrayList<DefaultMutableTreeNode>();
	    public JTreeRemoveNode(requeteandres re)  {
	        this.re=re;
	    	this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Les Models"));
            initializeUI();
	        
	    }

	    private void initializeUI() {
	    	this.setLocation(310, 192);
	    	this.setSize(110,280);

	        root = new DefaultMutableTreeNode("Racine");
	        lm.add(root);
	        
	        
	        
	          
	         

	       tree= new JTree(root);
	       tree.addTreeSelectionListener(new TreeSelectionListener(){
	   		public void valueChanged(TreeSelectionEvent event) {
	        System.out.println(tree.getLastSelectedPathComponent());
	        DefaultMutableTreeNode node =(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
	    if(re!=null) re.setmi(lm.indexOf(node));
	   	}});
	        
	       JScrollPane pane = new JScrollPane(tree);
	        pane.setPreferredSize(new Dimension(200, 400));

	        JButton removeButton = new JButton("Remove");
	        removeButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

	                TreePath[] paths = tree.getSelectionPaths();
	                for (TreePath path : paths) {
	                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
	                    if (node.getParent() != null) {
	                      re.remm(lm.indexOf(node));
	                      re.setmi(0);
	                      lm.remove(node);
	                      model.removeNodeFromParent(node);
	                       
	                    tree.updateUI();jp.repaint();
	                    }
	                }
	            }
	        });
          this.setLayout(new BorderLayout());
	      this.add(new JScrollPane(tree), BorderLayout.CENTER);
	      this.add(removeButton, BorderLayout.SOUTH);
	    }
public void setsel(){
	TreePath tpath= new TreePath(root); ;
  if(lm.size()==1)  tree.setSelectionPath(tpath);
}
	    
public void addnode(){
	indice++;
    DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
     
       //  model.removeNodeFromParent(node);
         DefaultMutableTreeNode nn=  new DefaultMutableTreeNode("Model :"+indice);
       
      node.add(nn);
      lm.add(nn);
      tree.updateUI();
   
	
	
}

	}
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
 
 
 
 
 
 
 
public class list2 implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int i= lbt.indexOf(arg0.getSource());
		switch(i){
		case 0:{prefal.add(preftf.getText());preftf.setText("");indice++;break;}
		case 1:{if(prefal.size()>0){prefal.remove(indice);if(indice>0)indice--;if(prefal.size()!=0)preftf.setText(prefal.get(indice));else preftf.setText(""); }break;}
		case 3:{if(indice<prefal.size()-1){indice++;preftf.setText(prefal.get(indice));}break;}
		case 2:{if(indice>0){indice--;preftf.setText(prefal.get(indice));}break;}
		}
		
	}
	
}
 
 
}