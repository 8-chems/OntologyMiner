import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import net.ucanaccess.jdbc.JackcessOpenerInterface;
public class DBConnection {
Connection cnn;
PreparedStatement  prs1,prs2;
ResultSet rs;

public DBConnection() {
		try {
			cnn=this.connect("src/Users.accdb");
			prs1=cnn.prepareStatement("SELECT PassWord FROM Users WHERE (Name = ?) AND (Connecter= ?)");
			prs2=cnn.prepareStatement("UPDATE Users SET Connecter= ?  WHERE (Name = ? ) AND (PassWord = ?)");
		    
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
	}
boolean verify(String id,String mp){
	boolean b=false;
	try {
		prs1.setString(1, id);
		prs1.setBoolean(2, false);
	    rs=prs1.executeQuery();
	    if(rs.next())
		{   String motDePasse = rs.getString("PassWord");
			if(motDePasse.equals(mp)){b=true;}
			prs2.setBoolean(1, true);
			prs2.setString(2, id);
			prs2.setString(3, mp);
			prs2.executeUpdate();
			cnn.close();
		}
	} catch (SQLException e) {e.printStackTrace();}
	
return b;	
}
public boolean Deconnecter(String id,String mp){
	boolean s=false;
	try {
		prs2.setBoolean(1, false);
		prs2.setString(2, id);
		prs2.setString(3, mp);
		prs2.executeUpdate();
		cnn.close();
	} catch (SQLException e) {e.printStackTrace();}
	
return s;
	
	
}
public Connection connect(String filename) throws ClassNotFoundException, SQLException
{
	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	String database = "jdbc:ucanaccess://";
	database += filename.trim();
	Connection con = DriverManager.getConnection(database);
	return con;
}
	
	

}
