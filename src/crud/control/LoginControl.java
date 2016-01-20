package crud.control;

import java.util.ArrayList;

import crud.entity.Login;
import crud.model.LoginModel;

/**
 * @author Wellington
 */
public class LoginControl {
	public ArrayList<Login> searchLogin(){
		return LoginModel.searchLogin();
	}
	
	public void initLogin(){
		LoginModel.init();
	}
}