import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ReturedObjectIntrf extends Remote{

	  public String getMembersList() throws RemoteException;
	  public void SetMessage(String id2,String mess) throws RemoteException;
	  public String getMessage(String s) throws RemoteException;
	  public boolean Connect(String id,String mp) throws RemoteException;
	  public void DConect()throws RemoteException;
	  public boolean CreateConv(String id) throws RemoteException;
	  public String GetConv() throws RemoteException;
	  public void DeleteConv(String id2) throws RemoteException;
}
