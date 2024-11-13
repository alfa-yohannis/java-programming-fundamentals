package edu.pradita.p14.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SelectForm extends JFrame {
 
	private static final long serialVersionUID = -6660109522677530174L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private OnSelectListener onSelectListener = new OnSelectListener() {
		@Override
		public void select(Object[] values) {
		}
	};

	/**
	 * Create the frame.
	 */
	public SelectForm(String query) {
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setTitle("Select Form");
		setFont(new Font("Tahoma", Font.PLAIN, 16));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectForm.this.setVisible(false);
				SelectForm.this.dispose();
			}
		});
		btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCancel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(btnCancel);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(
				new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "Code", "Description" }) {
					private static final long serialVersionUID = -2552372820101294082L;
					Class<?>[] columnTypes = new Class[] { String.class, String.class };

					public Class<?> getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] columnEditables = new boolean[] { false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		table.getColumnModel().getColumn(0).setPreferredWidth(71);
		table.getColumnModel().getColumn(1).setPreferredWidth(204);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(10, 0));

		JLabel lblFind = new JLabel("Find:");
		lblFind.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblFind, BorderLayout.WEST);

		textField = new JTextField();
		lblFind.setLabelFor(textField);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);
		panel_1.add(textField);

		JButton btnFind = new JButton("Find");
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(btnFind, BorderLayout.EAST);

		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				int colCount = dtm.getColumnCount();
				Object[] values = new Object[colCount];
				for (int col = 0; col < dtm.getColumnCount(); col++) {
					Object val = dtm.getValueAt(row, col);
					values[col] = val;
				}
				SelectForm.this.onSelectListener.select(values);
				SelectForm.this.setVisible(false);
				SelectForm.this.dispose();
			}
		});

		btnSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(btnSelect);

		try {
			PreparedStatement statement = MainForm.CONNECTION.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			ResultSetMetaData metadata = resultSet.getMetaData();
			int colCount = metadata.getColumnCount();

			String[] columnNames = new String[colCount];
			for (int i = 1; i <= colCount; i++) {
				String colName = metadata.getColumnName(i);
				columnNames[i - 1] = colName;
			}

			Class<?>[] columnTypes = new Class<?>[colCount];
			for (int i = 0; i < colCount; i++) {
				columnTypes[i] = String.class;
			}

			boolean[] columnEditables = new boolean[colCount];
			for (int i = 0; i < colCount; i++) {
				columnEditables[i] = false;
			}

			DefaultTableModel dtm = new DefaultTableModel( //
					new Object[][] {}, // data
					columnNames // columns
			) {	
				private static final long serialVersionUID = -8885608731029075810L;

				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}

				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};

			// add the rows
			while (resultSet.next()) {
				Object[] values = new Object[colCount];
				for (int i = 1; i <= colCount; i++) {
					values[i - 1] = resultSet.getObject(i);
				}
				dtm.addRow(values);
			}
			resultSet.close();
			statement.close();

			table.setModel(dtm);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public OnSelectListener getOnSelectListener() {
		return onSelectListener;
	}

	public void setOnSelectListener(OnSelectListener onSelectListener) {
		this.onSelectListener = onSelectListener;
	}

}
