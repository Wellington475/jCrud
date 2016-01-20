package crud.model;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import crud.database.DataBase;
import crud.entity.Login;

/**
 * @author Wellington
 */
public class LoginModel {
	private static Connection conn;
	
	public static void init(){
		try{
			DataBase.open();
			conn = DataBase.conn();
			Statement state = conn.createStatement();
			state.executeUpdate("DROP TABLE IF EXISTS login;");
			state.executeUpdate("CREATE TABLE login(id integer primary key not null, login text not null, password text not null, name text not null);");
			state.executeUpdate("INSERT INTO login(login, password, name) VALUES ('root', 'root', 'Geroudo');");
			DataBase.close();
		}
		catch(SQLException ex){
			System.out.println("[Error]: "+ex.getMessage());
			System.exit(1);
		}
	}
	
	@SuppressWarnings("finally")
	public static ArrayList<Login> searchLogin(){
		ArrayList<Login> resultLogin = new ArrayList<Login>();
		try{
			DataBase.open();
			conn = DataBase.conn();
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM login;");
			
			while(rs.next()){
				Login l = new Login();
				l.setLogin(rs.getString("login"));
				l.setPassword(rs.getString("password"));
				l.setName(rs.getString("name"));
				resultLogin.add(l);
			}
		}
		catch(SQLException ex){
			System.out.println("[Error]: "+ex.getMessage());
			System.exit(1);
			return null;
		}
		finally{
			return resultLogin;
		}
	}
}
