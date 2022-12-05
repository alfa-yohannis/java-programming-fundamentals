package edu.pradita.p14;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class ItemForm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String query;

	/**
	 * Create the frame.
	 */
	public ItemForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setTitle("Item Form");
		setFont(new Font("Tahoma", Font.PLAIN, 16));
		setBounds(100, 100, 350, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 4));

		Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		this.setLocation(centerPoint.x - (int) this.getSize().getWidth() / 2,
		    centerPoint.y - (int) this.getSize().getHeight() / 2);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemForm.this.setVisible(false);
				ItemForm.this.dispose();
			}
		});
		btnClose.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnClose.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(btnClose);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
		    new String[] { "Code", "Name", "Price", "Quantity" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, Double.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] lastRowColumnEditables = new boolean[] { true, true, true, true };
			boolean[] columnEditables = new boolean[] { true, true, true, true };

			public boolean isCellEditable(int row, int column) {
				if (this.getRowCount() > 0 && row == this.getRowCount() - 1) {
					return lastRowColumnEditables[column];
				} else {
					return columnEditables[column];
				}
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(52);
		table.getColumnModel().getColumn(1).setPreferredWidth(157);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		scrollPane.setViewportView(table);

		table.addPropertyChangeListener(new PropertyChangeListener() {
			
			String oldCode = null;
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("tableCellEditor".equals(evt.getPropertyName()) && table.isEditing()) {
					int selectedRow = table.getSelectedRow();
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					oldCode = (String) dtm.getValueAt(selectedRow, 0);
				}
				
				if (!"tableCellEditor".equals(evt.getPropertyName()) || table.isEditing()) {
					return;
				}

				DefaultCellEditor cellEditor = (DefaultCellEditor) evt.getOldValue();
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				int selectedCol = table.getSelectedColumn();
				

				String code = (selectedCol == 0)? (String) cellEditor.getCellEditorValue() : oldCode;
					;
				if (code == null || code.trim().length() == 0) {
					return;
				}

				String name = (String) dtm.getValueAt(selectedRow, 1);
				
				
				Object priceStr = (selectedCol == 2) ? cellEditor.getCellEditorValue() : dtm.getValueAt(selectedRow, 2);
				if (priceStr == null) {
					return;
				} else if (priceStr instanceof Double) {
					priceStr = priceStr.toString();
				}

				Object qtyStr = (selectedCol == 3) ? cellEditor.getCellEditorValue() : dtm.getValueAt(selectedRow, 3);
				if (qtyStr == null) {
					return;
				} else if (qtyStr instanceof Double) {
					qtyStr = qtyStr.toString();
				}
				
				double price = 0;
				double quantity = 0;

				try {
					price = Double.parseDouble(priceStr.toString());
					quantity = Double.parseDouble(qtyStr.toString());
				} catch (Exception exe) {
					return;
				}

				try {
					String query = "UPDATE item SET code = ?, name = ?, price = ?, quantity = ? " 
				+ " WHERE code = ?";
					PreparedStatement statement = OrderForm.CONNECTION.prepareStatement(query);
					statement.setString(1, code);
					statement.setString(2, name);
					statement.setDouble(3, price);
					statement.setDouble(4, quantity);
					statement.setString(5, oldCode);
 					if (statement.executeUpdate() > 0) {
						System.out.println("Item " + code + " has been successfully updated.");
					}
					// If no update then try insert a new one.
					else {
						statement.close();
						query = "INSERT INTO item(code, name, price, quantity) VALUES(?, ?, ?, ?);";
						statement = OrderForm.CONNECTION.prepareStatement(query);
						statement.setString(1, code);
						statement.setString(2, name);
						statement.setDouble(3, price);
						statement.setDouble(4, quantity);
						if (statement.executeUpdate() > 0) {
							System.out.println("A new item " + code + " has been added.");
						}
						statement.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// ----
		});

		updateTable();

	}

	private void updateTable() {
		try {
			String query = "SELECT code, name, price, quantity FROM item";
			PreparedStatement statement = OrderForm.CONNECTION.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			while (dtm.getRowCount() > 0) {
				dtm.removeRow(0);
			}

			// add the rows
			while (resultSet.next()) {
				Object[] values = new Object[4];
				values[0] = resultSet.getString("code");
				values[1] = resultSet.getString("name");
				values[2] = resultSet.getDouble("price");
				values[3] = resultSet.getDouble("quantity");
				dtm.addRow(values);
			}
			Object[] values = new Object[4];
			dtm.addRow(values);

			resultSet.close();
			statement.close();

			table.setModel(dtm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
