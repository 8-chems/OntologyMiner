import java.util.ArrayList;

public class Conversation{
    	public String id1,id2;
         ArrayList<Message>  lm;
         public Conversation(String id1,String id2){
    		 this.id1=id1;
    		 this.id2=id2;
    		 lm=new ArrayList<Message>();
    	 }
     }