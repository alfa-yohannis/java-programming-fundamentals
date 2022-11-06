package edu.pradita.p11;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A frame that is filled with two components.
 */
public class SimpleFrame extends JFrame {

	private static final int FRAME_WIDTH = 320;
	private static final int FRAME_HEIGHT = 240;

	// constructor
	public SimpleFrame() {
		
		JPanel panel = new JPanel();
		this.add(panel);
		
		JTextField text = new JTextField("Alice", 10);
		panel.add(text);
		
		JButton button = new JButton("Click me!");
		panel.add(button);
		
		JLabel label = new JLabel("Hello World!");
		panel.add(label);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				label.setText("Hello " + text.getText() + "!");
			}
		});

		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
}
