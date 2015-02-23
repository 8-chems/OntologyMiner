import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ReceaverObjectImp extends UnicastRemoteObject
        implements ReceaverObjectIntrf{
ProtectedObjects po;
int i=0;
	public ReceaverObjectImp() throws RemoteException {
		po=new ProtectedObjects();
	}

	public ReturedObjectIntrf getCible() throws RemoteException {
	    i++; 
	    System.out.println("object returned ");
		return new ReturnedObjectImp(""+i,po);
	}

}
