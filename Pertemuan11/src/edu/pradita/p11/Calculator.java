package edu.pradita.p11;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator extends JFrame {

	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 300;

	private static final int FONT_SIZE = 32;

	private Integer accumulator = null;
	private Integer value = 0;
	private String nextOperation = null;
	private boolean clearDisplay = false;

	public Calculator() {

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.add(mainPanel);

		JLabel display = new JLabel();
		display.setHorizontalAlignment(JLabel.RIGHT);
		display.setFont(new Font(display.getFont().getName(), Font.BOLD, 32));
		display.setText(String.valueOf(value));
		mainPanel.add(display, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 3));
		mainPanel.add(buttonPanel, BorderLayout.CENTER);

		// Loop adding numeric button
		for (int i = 1; i <= 10; i++) {

			String valueText = (i < 10) ? valueText = String.valueOf(i) : "0";
			JButton button = new JButton();
			button.setText(valueText);
			buttonPanel.add(button);

			ActionListener numericButtonListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (display.getText().equals("0")) {
						display.setText("" + button.getText());
					} else {
						if (clearDisplay) {
							display.setText("");
							clearDisplay = false;
						}
						display.setText(display.getText() + button.getText());
					}
					value = Integer.valueOf(display.getText());
				}
			};

			button.addActionListener(numericButtonListener);
		}

		// adding add button
		JButton button = new JButton();
		button.setText("+");
		buttonPanel.add(button);

		ActionListener addButtonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextOperation = "+";
				if (accumulator == null) {
					accumulator = Integer.valueOf(display.getText());
				} else {
					calculate();
					display.setText(String.valueOf(accumulator));
				}
				value = 0;

				clearDisplay = true;
			}
		};
		button.addActionListener(addButtonListener);

		// adding equal button
		JButton equalButton = new JButton();
		equalButton.setText("=");
		buttonPanel.add(equalButton);

		ActionListener equalButtonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (accumulator != null && nextOperation != null) {
					calculate();
					display.setText(String.valueOf(accumulator));
				}
				clearDisplay = true;
			}
		};
		equalButton.addActionListener(equalButtonListener);

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	public void calculate() {
		if (nextOperation == "+") {
			accumulator = accumulator + value;
		}
	}
}
