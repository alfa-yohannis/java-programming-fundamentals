package edu.pradita.p11;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyForm {


	private JFrame frmExampleForm;
	private JTextField textField;
	private JSpinner spinner;
	private int counter = 1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyForm window = new MyForm();
					window.frmExampleForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmExampleForm = new JFrame();
		frmExampleForm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println(frmExampleForm.getName() + "is focused");
			}
		});
		
		frmExampleForm.setTitle("Example Form");
		frmExampleForm.setBounds(100, 100, 450, 300);
		frmExampleForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExampleForm.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(20, 24, 95, 29);
		frmExampleForm.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(114, 24, 206, 26);
		frmExampleForm.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAge.setBounds(20, 76, 95, 29);
		frmExampleForm.getContentPane().add(lblAge);
		
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("Spinner - Name: " + textField.getText() + ", Age: " +
						spinner.getValue());
			}
		});
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spinner.setBounds(114, 76, 206, 29);
		frmExampleForm.getContentPane().add(spinner);
		
		JButton btnNewButton = new JButton("Save!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button - Name: " + textField.getText() + ", Age: " +
						spinner.getValue());
				
				JButton button = new JButton(String.valueOf(counter));
				button.setFont(new Font("Tahoma", Font.BOLD, 18));
				button.setBounds(10 + (counter * 100), 10, 100, 30);
				frmExampleForm.getContentPane().add(button);
				counter++;
				frmExampleForm.repaint();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(114, 136, 85, 29);
		frmExampleForm.getContentPane().add(btnNewButton);
	}

}
