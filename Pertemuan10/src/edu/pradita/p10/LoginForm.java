package edu.pradita.p10;

import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm {

	private JFrame frmLoginScreen;
	private JPasswordField txtPassword;
	private static List<User> users = Data.loadUsers();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frmLoginScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginScreen = new JFrame();
		frmLoginScreen.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmLoginScreen.setTitle("Login Screen");
		frmLoginScreen.setSize(400, 300);
		frmLoginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginScreen.getContentPane().setLayout(null);
		Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		frmLoginScreen.setLocation(centerPoint.x - (int) frmLoginScreen.getSize().getWidth() / 2,
				centerPoint.y - (int) frmLoginScreen.getSize().getHeight() / 2);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(92, 88, 96, 19);
		frmLoginScreen.getContentPane().add(lblUsername);

		JTextField txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername.setBounds(197, 87, 107, 19);
		frmLoginScreen.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setBounds(197, 116, 107, 19);
		frmLoginScreen.getContentPane().add(txtPassword);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(92, 117, 96, 19);
		frmLoginScreen.getContentPane().add(lblPassword);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = txtUsername.getText();
				String password = String.valueOf(txtPassword.getPassword());

				for (User user : users) {
					if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
						AddUserForm addUserForm = new AddUserForm();
						addUserForm.setVisible(true);
						addUserForm.setTitle("Current User: " + user.getUsername());
						frmLoginScreen.setVisible(false);
						return;
					}
				}
				JDialog dialog = new JDialog(frmLoginScreen, "Message", ModalityType.APPLICATION_MODAL);
				JLabel label = new JLabel("Your data is not found!");
				dialog.setLayout(new FlowLayout());
				dialog.add(label);
				dialog.setLocationRelativeTo(frmLoginScreen);
				dialog.setSize(150, 100);
				dialog.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(197, 145, 85, 21);
		frmLoginScreen.getContentPane().add(btnNewButton);
	}
}
