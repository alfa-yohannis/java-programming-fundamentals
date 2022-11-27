package edu.pradita.modelview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import edu.pradita.MainApplication;
import edu.pradita.model.ATM;
import edu.pradita.view.ATMState;
import edu.pradita.view.MainScreen;

public class EnterButtonListener implements ActionListener {

	MainScreen mainScreen;
	public EnterButtonListener(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (MainApplication.getAtmState().equals(ATMState.ENTER_ACCOUNT_NUMBER)) {
			mainScreen.getLblTop().setText("<html><center>Type in 4 digits<br/>of your PIN Number:</center></html>");
			mainScreen.getLblMiddle().setText("____");
			mainScreen.getLblBottom().setText("Press ENTER to proceed.");
			MainApplication.setAtmState(ATMState.ENTER_PIN);
		} else if (MainApplication.getAtmState().equals(ATMState.ENTER_PIN)) {
			MainApplication.setAtmState(ATMState.ENTER_PIN);
			mainScreen.getBtnLeft1().setText("<html><center>Check<br/>Balance</center></html>");
			mainScreen.getBtnLeft2().setText("<html><center>Withdraw</center></html>");
			mainScreen.getBtnLeft3().setText("<html><center>Exit</center></html>");
			mainScreen.getBtnRight1().setText("<html><center>Deposit</center></html>");
			mainScreen.getBtnRight2().setText("<html><center>Transfer</center></html>");
			mainScreen.getBtnRight3().setText("<html><center>Data<br/>Transaksi</center></html>");
			mainScreen.getLblTop().setText("Main Menu");
			mainScreen.getLblMiddle().setText("");
			mainScreen.getLblBottom().setText("");
		} else if (MainApplication.getAtmState().equals(ATMState.MAIN_MENU)) {
			
		}  
	}
}
