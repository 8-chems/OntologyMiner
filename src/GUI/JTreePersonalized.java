package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class JTreePersonalized extends JPanel{
	JTree tree;int indice=0;
	   JTreePersonalized jp=this; 
	   JScrollPane jsp;
	   DefaultMutableTreeNode root;
	   ArrayList<DefaultMutableTreeNode>  lm=new ArrayList<DefaultMutableTreeNode>();
	    public JTreePersonalized()  {
	       
	    	this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Ontolgy structure"));
      initializeUI();
	        
	    }

	    private void initializeUI() {
	    	this.setLocation(310, 192);
	    	this.setSize(110,280);

	        root = new DefaultMutableTreeNode("Thing");
	        lm.add(root);
            tree= new JTree(root);
	        tree.addTreeSelectionListener(new TreeSelectionListener(){
	   		
	        public void valueChanged(TreeSelectionEvent event) {
	        System.out.println(tree.getLastSelectedPathComponent());
	        DefaultMutableTreeNode node =(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
	   
	   	}});
	        
	       JScrollPane pane = new JScrollPane(tree);
	       // pane.setPreferredSize(new Dimension(200, 400));

	        
	        
       this.setLayout(new BorderLayout());
       jsp=new JScrollPane(tree);
	      this.add(jsp, BorderLayout.CENTER);
	     
	    }
	    
	    public void removeNode(ActionEvent e) {
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

            TreePath[] paths = tree.getSelectionPaths();
            for (TreePath path : paths) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                if (node.getParent() != null) {
                 lm.remove(node);
                  model.removeNodeFromParent(node);
                   
                tree.updateUI();jp.repaint();
                }
            }
     }
               
public void addnode(){
	indice++;
DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
DefaultMutableTreeNode nn=  new DefaultMutableTreeNode("Model :"+indice);
node.add(nn);
lm.add(nn);
tree.updateUI();
}

}
