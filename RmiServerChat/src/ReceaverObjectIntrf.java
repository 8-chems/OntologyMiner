import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ReceaverObjectIntrf extends Remote{
     public  ReturedObjectIntrf  getCible() throws RemoteException;	
}
