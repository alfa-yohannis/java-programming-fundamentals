package edu.pradita.p10;

import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class AddUserForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTable usersTable;
	private JTextField txtPassword;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUserForm frame = new AddUserForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddUserForm() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(37, 33, 96, 13);
		contentPane.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername.setBounds(143, 29, 171, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(36, 64, 75, 13);
		contentPane.add(lblPassword);

		JButton btnAddUpdate = new JButton("Add/Update");
		btnAddUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText().trim();
				String password = txtPassword.getText().trim();
				int rowCount = usersTable.getRowCount();
				DefaultTableModel table = (DefaultTableModel) usersTable.getModel();

				for (int i = 0; i <= table.getRowCount() - 1; i++) {
					if (username.equals(table.getValueAt(i, 0))) {
						table.setValueAt(password, i, 1);
						return;
					}
				}
				table.insertRow(rowCount, new Object[] { username, password });
			}
		});
		btnAddUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddUpdate.setBounds(143, 93, 171, 22);
		contentPane.add(btnAddUpdate);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 138, 355, 175);
		contentPane.add(scrollPane);

		Object[][] data = new Object[Data.getUsers().size()][2];
		for (int i = 0; i < Data.getUsers().size(); i++) {
			User user = Data.getUsers().get(i);
			data[i][0] = user.getUsername();
			data[i][1] = user.getPassword();
		}

		usersTable = new JTable();
		usersTable.setModel(new DefaultTableModel(data, new String[] { "Username", "Password" }) {
			Class[] columnTypes = new Class[] { String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(usersTable);
		usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usersTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String username = usersTable.getValueAt(usersTable.getSelectedRow(), 0).toString();
				String password = usersTable.getValueAt(usersTable.getSelectedRow(), 1).toString();
				txtUsername.setText(username);
				txtPassword.setText(password);
			}
		});

		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setColumns(10);
		txtPassword.setBounds(143, 58, 171, 19);
		contentPane.add(txtPassword);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<User> users = new ArrayList<>();
				for (int i = 0; i < usersTable.getModel().getRowCount(); i++) {
					String username = usersTable.getValueAt(i, 0).toString(); 
					String password = usersTable.getValueAt(i, 1).toString();
					users.add(new User(username, password));
				}
				Data.saveUsers(users);
				JDialog dialog = new JDialog(AddUserForm.this, "Message", ModalityType.APPLICATION_MODAL);
				JLabel label = new JLabel("Data has been saved!");
				dialog.setLayout(new FlowLayout());
				dialog.add(label);
				dialog.setLocationRelativeTo(AddUserForm.this);
				dialog.setSize(150, 100);
				dialog.setVisible(true);
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(313, 323, 85, 21);
		contentPane.add(btnSave);
		setSize(436, 401);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		setLocation(centerPoint.x - (int) this.getSize().getWidth() / 2,
				centerPoint.y - (int) this.getSize().getHeight() / 2);
	}
}
