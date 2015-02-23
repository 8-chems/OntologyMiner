import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class ReturnedObjectImp extends UnicastRemoteObject 
                     implements ReturedObjectIntrf {
    private static final long serialVersionUID = 1L;
    String id;
    ProtectedObjects po;
    String mp,nom;
    
    public ReturnedObjectImp(String id,ProtectedObjects po) throws RemoteException{
             this.id=id;  
             this.po=po;
    }

	public String getMembersList() throws RemoteException {
		String f="";
		System.out.println("la taille de lm = "+ po.lm.size());
		for(int i=0;i<po.lm.size();i++){
		if(!po.lm.get(i).split(";")[0].equals(this.nom))f+=po.lm.get(i)+":";
		}
		return f ;
	}

	public void SetMessage(String id2,String mess) throws RemoteException {
		po.setMessage(id, id2, mess);
	}

	public String getMessage(String id2) throws RemoteException {
		String s=po.getMessage(id, id2);
		return s;
	}

	public  boolean Connect(String idd, String mp) throws RemoteException {
		DBConnection bb=new DBConnection();
		boolean b= bb.verify(idd, mp);
          if(b){
        	  po.addMemb(idd+";"+this.id);
              this.mp=mp;
              this.nom=idd;
          }
		   return b;
	     }

	public void DConect() throws RemoteException {
		try {
			DBConnection bb=new DBConnection();
			bb.Deconnecter(this.nom,this.mp);
			po.deleteMe(this.nom+";"+this.id);
			this.finalize();
		} catch (Throwable e) {e.printStackTrace();
		}
	}
 public boolean CreateConv(String i) throws RemoteException {
	    System.out.println("id= "+id);
		po.CreateConv(this.id, i);    
		return true;
	}
public String getNameById(String s){
String ss="";
	for(int i=0;i<po.lm.size();i++){
	
	if(s.equals(po.lm.get(i).split(";")[1])) {ss=po.lm.get(i);
	break;}
	}	
	return  ss; 
}

public String GetConv() throws RemoteException {
	String s="";
	for(int i=0;i<po.lc.size();i++){
	  if(po.lc.get(i).id1.equals(id)) s=s+this.getNameById(po.lc.get(i).id2)+":";
	  else if(po.lc.get(i).id2.equals(id)) s=s+this.getNameById(po.lc.get(i).id1)+":";
	}
	return s;
}
public void DeleteConv(String id2) throws RemoteException {
  po.DeleteConv(id, id2);	
}
}
