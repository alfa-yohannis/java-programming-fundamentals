package edu.pradita;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import edu.pradita.view.ATMState;
import edu.pradita.view.LoginState;
import edu.pradita.view.MainScreen;

public class MainApplication {

	private static List<JFrame> forms = new ArrayList<>();
	
	private static ATMState atmState = ATMState.ENTER_ACCOUNT_NUMBER;
	private static LoginState loginState = LoginState.LOGOUT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.setVisible(true);
					forms.add(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static List<JFrame> getForms() {
		return forms;
	}
	

	public static ATMState getAtmState() {
		return atmState;
	}

	public static void setAtmState(ATMState atmState) {
		MainApplication.atmState = atmState;
	}

	public static LoginState getLoginState() {
		return loginState;
	}

	public static void setLoginState(LoginState loginState) {
		MainApplication.loginState = loginState;
	}
	
	

}
