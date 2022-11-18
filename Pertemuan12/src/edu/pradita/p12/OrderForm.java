package edu.pradita.p12;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.ListSelectionModel;

public class OrderForm {

	private JFrame frmOrderForm;
	private JTable table;
	private JTextField txtTotal;
	private JTextField txtCode;
	private JTextField txtDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderForm window = new OrderForm();
					window.frmOrderForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OrderForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOrderForm = new JFrame();
		frmOrderForm.setTitle("Order Form");
		frmOrderForm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmOrderForm.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmOrderForm.setBounds(100, 100, 723, 421);
		frmOrderForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOrderForm.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		frmOrderForm.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"No.", "Item Code", "Name", "Price", "Quantity", "Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Double.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JPanel southPanel = new JPanel();
		frmOrderForm.getContentPane().add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		southPanel.add(panel);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Dialog", Font.BOLD, 20));
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		panel.add(txtTotal);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		southPanel.add(panel_1);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnConfirm);
		
		JPanel northPanel = new JPanel();
		frmOrderForm.getContentPane().add(northPanel, BorderLayout.NORTH);
		GridBagLayout gbl_northPanel = new GridBagLayout();
		gbl_northPanel.columnWidths = new int[]{709, 0};
		gbl_northPanel.rowHeights = new int[] {50, 78, 0};
		gbl_northPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_northPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		northPanel.setLayout(gbl_northPanel);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_2.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		northPanel.add(panel_2, gbc_panel_2);
		
		JButton btnNew = new JButton("New");
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(btnNew);
		
		JButton btnFind = new JButton("Find");
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(btnFind);
		
		JLabel lblNewLabel = new JLabel("Code");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblNewLabel);
		
		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCode.setColumns(10);
		panel_2.add(txtCode);
		
		Component rigidArea = Box.createRigidArea(new Dimension(100, 20));
		panel_2.add(rigidArea);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDate.setColumns(10);
		panel_2.add(txtDate);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEADING);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.anchor = GridBagConstraints.SOUTH;
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		northPanel.add(panel_4, gbc_panel_4);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(btnAddItem);
		
		JButton btnDeleteItem = new JButton("Remove Item");
		btnDeleteItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(btnDeleteItem);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(100, 20));
		panel_4.add(rigidArea_1);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setVerticalAlignment(SwingConstants.TOP);
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(lblNote);
		
		JTextArea txtNote = new JTextArea();
		txtNote.setRows(3);
		txtNote.setColumns(30);
		panel_4.add(txtNote);
		table.getColumnModel().getColumn(0).setPreferredWidth(27);
		table.getColumnModel().getColumn(1).setPreferredWidth(64);
		table.getColumnModel().getColumn(2).setPreferredWidth(195);
		table.getColumnModel().getColumn(3).setPreferredWidth(68);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(108);
	}
}
