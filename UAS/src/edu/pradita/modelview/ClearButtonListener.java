package edu.pradita.modelview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import edu.pradita.MainApplication;
import edu.pradita.model.ATM;
import edu.pradita.view.MainScreen;

public class ClearButtonListener implements ActionListener {

	MainScreen mainScreen;
	public ClearButtonListener(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	public void actionPerformed(ActionEvent e) {
		ATM.setAccountNumber("");
		mainScreen.getLblMiddle().setText("________");
	}
}
