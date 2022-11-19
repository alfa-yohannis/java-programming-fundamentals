package edu.pradita.modelview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import edu.pradita.MainApplication;
import edu.pradita.model.ATM;
import edu.pradita.view.ATMState;
import edu.pradita.view.MainScreen;

public class NumericButtonListener implements ActionListener {

	MainScreen mainScreen;
	public NumericButtonListener(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String number = button.getText();
		if (MainApplication.getAtmState().equals(ATMState.ENTER_ACCOUNT_NUMBER)) {
			ATM.setAccountNumber(ATM.getAccountNumber() + number);
			mainScreen.getLblMiddle().setText(ATM.getAccountNumber());
		} else if (MainApplication.getAtmState().equals(ATMState.ENTER_PIN)) {
			ATM.setPin(ATM.getPin() + number);
			String stars = "";
			for (int i = 0; i < ATM.getPin().length();i++) {
				stars += "*";
			}
			mainScreen.getLblMiddle().setText(stars);
		} 
		
	}
}
