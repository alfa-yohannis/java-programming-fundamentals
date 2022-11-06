package edu.pradita.p11;

import javax.swing.JFrame;

public class MainForm {

	

	public static void main(String[] args) {

//		JFrame frame = new SimpleFrame();
		JFrame frame = new Calculator();

		frame.setLocationRelativeTo(null); // put the frame at the centre
		frame.setTitle("Universitas Pradita - Pertemuan 11");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
