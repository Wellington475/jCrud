package crud.front;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import crud.entity.Login;
import crud.control.LoginControl;

/**
 * @author Wellington
 */
public class Main {
	private JFrame jFrame = new JFrame();
	private JPanel jPanel = new JPanel();
	private JButton btn_clear = new JButton();
	private JTextField txt_login = new JTextField();
	private JPasswordField txt_pass = new JPasswordField();
	private JButton btn_login= new JButton();
	private JLabel jLabel1 = new JLabel();
	private JLabel jLabel2 = new JLabel();
	
	@SuppressWarnings("unused")
	public static void main(String[] agrs){
		LoginControl lc = new LoginControl();
		lc.initLogin();
		
		Main m = new Main();
	}
	
	public Main(){
		initComponents();
	}
	
	public void initComponents(){
		jFrame.setTitle("Sistema");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jLabel1.setText("Login");
		txt_login.setColumns(13);
		jPanel.add(jLabel1);
		jPanel.add(txt_login);
		
		jLabel2.setText("Senha");
		txt_pass.setColumns(13);
		jPanel.add(jLabel2);
		jPanel.add(txt_pass);
		
		
		btn_clear.setText("Limpar");
		btn_clear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAction(e);
			}
		});
		
		btn_login.setText("Logar");
		btn_login.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				loginAction(e);
			}
		});
		
		jPanel.add(btn_clear);
		jPanel.add(btn_login);
		
		jFrame.add(jPanel);
		jFrame.pack();
		jFrame.setSize(200, 200);
		jFrame.setVisible(true);
		
	}
	
	private void clearAction(ActionEvent evt){
		try{
			txt_login.setText("");
			txt_pass.setText("");
		}
		catch(Exception ex){
			System.out.println("[Error]: "+ex.getMessage());
			System.exit(1);
		}
	}
	
	@SuppressWarnings("deprecation")
	private void loginAction(ActionEvent evt){
		String login = txt_login.getText();
		String pass = txt_pass.getText();
		
		try{
			LoginControl lc = new LoginControl();
			ArrayList<Login> ls = lc.searchLogin();
			
			for(Login l: ls){
				if(l.getLogin().equalsIgnoreCase(login) && l.getPassword().equalsIgnoreCase(pass)){
					JOptionPane.showMessageDialog(null, "Olá "+l.getName()+", você está logado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
				} 
			}
		}
		catch(Exception ex){
			System.out.println("[Error]: "+ex.getMessage());
			System.exit(1);
		}
	}
}
