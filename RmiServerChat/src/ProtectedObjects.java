import java.util.ArrayList;


public class ProtectedObjects {
	//variable de class
    ArrayList<Conversation>  lc=new ArrayList<Conversation>();
    ArrayList<String> lm=new ArrayList<String>();
public ProtectedObjects(){
	
}
public synchronized void  addMemb(String s){
	lm.add(s);
}

	public synchronized void CreateConv(String id1,String id2){
		 int i= this.getConverByIds(id1, id2);
		 System.out.println("i="+i);
		if(i==-1){
			System.out.println("id1= "+id1+"  id2= "+id2);
			Conversation conv=new Conversation(id1,id2);
			lc.add(conv);
		}
	}
	
	public synchronized void setMessage(String id1,String id2,String mess){
		System.out.println("set mmes id1="+id1+"id2= "+id2);
		int i= this.getConverByIds(id1, id2);
	    if(i==-1){this.CreateConv(id1, id2);i= this.getConverByIds(id1, id2);}
		this.lc.get(i).lm.add(new Message(id1,mess));
	   
	}
	
	public void DeleteConv(String id1,String id2){
		int i= this.getConverByIds(id1, id2);
		if(i!=-1){
			lc.remove(i);
		}
	}
	
	public synchronized void deleteMe(String s){
		for(int i=0;i<lm.size();i++){
			if(lm.get(i).equals(s)){lm.remove(i);break;}
		}
	}
	
	public synchronized String getMessage(String id1,String id2){
		 int i= this.getConverByIds(id1, id2);
		 String s="";
		 if(i!=-1){
		 Conversation conv=this.lc.get(i);
		 int o=conv.lm.size()-1;
		 System.out.println("lm. size "+ conv.lm.size());
		 while(o!=-1&&!conv.lm.get(o).id.equals(id1)){
			 s+=conv.lm.get(o).mess;
			 conv.lm.remove(o);
			 o--;
		}
		 }
	return s;
	}
	
	
	
	public int getConverByIds(String id1,String id2){
		int k=-1;
		System.out.println("+id1="+id1+"+id="+id2);
		for(int i=0;i<lc.size();i++){
			if((lc.get(i).id1.equals(id1)&&lc.get(i).id2.equals(id2))||
			   (lc.get(i).id1.equals(id2)&&lc.get(i).id2.equals(id1))){
				k=i;break;	
			}
		}
		return k;
	}
}


