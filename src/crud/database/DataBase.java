package crud.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import crud.config.Config;

/**
 * @author Wellington
 */
public class DataBase {
	public static final String dbName = "database";
	public static final double version = 1.0;
	private static Connection connect = null;
	
	public static boolean open(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException ex) {
			System.out.println("[Error]: "+ex.getMessage());
			System.exit(1);
			return false;
		}
		
		try{
			Config c = new Config();
			if(c.existsDir(c.getDatabaseDir()) == false){
				c.createDir(c.getDatabaseDir());
			}
			connect = DriverManager.getConnection("jdbc:sqlite:"+c.getDatabaseFile());
			return true;
		}
		catch(SQLException ex){
			System.out.println("[Error]: "+ex.getMessage());
			System.exit(1);
			return false;
		}
	}
	
	public static Connection conn(){
		if(connect!=null){
			return connect;
		}
		else{
			return null;
		}
	}
	
	public static boolean close(){
		try{
			connect.close();
			connect = null;
			return true;
		}
		catch(SQLException ex){
			System.out.println("[Error]: "+ex.getMessage());
			System.exit(1);
			return false;
		}
	}
}
