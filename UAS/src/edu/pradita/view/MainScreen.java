package edu.pradita.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import edu.pradita.modelview.ClearButtonListener;
import edu.pradita.modelview.EnterButtonListener;
import edu.pradita.modelview.NumericButtonListener;

public class MainScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTop;
	private JLabel lblMiddle;
	private JLabel lblBottom;
	private JButton btnLeft1;
	private JButton btnLeft2;
	private JButton btnLeft3;
	private JButton btnRight1;
	private JButton btnRight2;
	private JButton btnRight3;

	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Cash Machine");
		this.setBounds(100, 100, 600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		this.setLocation(centerPoint.x - (int) this.getSize().getWidth() / 2,
				centerPoint.y - (int) this.getSize().getHeight() / 2);

		JPanel centerPanel = new JPanel();
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(3, 1, 0, 0));

		lblTop = new JLabel("Enter your account number:");
		lblTop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTop.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblTop);

		lblMiddle = new JLabel("________");
		lblMiddle.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiddle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centerPanel.add(lblMiddle);

		lblBottom = new JLabel("Press ENTER to confirm");
		lblBottom.setHorizontalAlignment(SwingConstants.CENTER);
		lblBottom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centerPanel.add(lblBottom);

		JPanel leftPanel = new JPanel();
		this.getContentPane().add(leftPanel, BorderLayout.WEST);
								GridBagLayout gbl_leftPanel = new GridBagLayout();
								gbl_leftPanel.columnWidths = new int[] {150};
								gbl_leftPanel.rowHeights = new int[] {105, 105, 105};
								gbl_leftPanel.columnWeights = new double[]{1.0};
								gbl_leftPanel.rowWeights = new double[]{1.0, 1.0, 1.0};
								leftPanel.setLayout(gbl_leftPanel);
						
								btnLeft1 = new JButton("");
								btnLeft1.setFont(new Font("SansSerif", Font.BOLD, 16));
								btnLeft1.setBackground(UIManager.getColor("Button.background"));
								GridBagConstraints gbc_btnLeft1 = new GridBagConstraints();
								gbc_btnLeft1.fill = GridBagConstraints.BOTH;
								gbc_btnLeft1.insets = new Insets(0, 0, 5, 0);
								gbc_btnLeft1.gridx = 0;
								gbc_btnLeft1.gridy = 0;
								leftPanel.add(btnLeft1, gbc_btnLeft1);
				
						btnLeft2 = new JButton("");
						btnLeft2.setFont(new Font("SansSerif", Font.BOLD, 16));
						btnLeft2.setBackground(UIManager.getColor("Button.background"));
						GridBagConstraints gbc_btnLeft2 = new GridBagConstraints();
						gbc_btnLeft2.fill = GridBagConstraints.BOTH;
						gbc_btnLeft2.insets = new Insets(0, 0, 5, 0);
						gbc_btnLeft2.gridx = 0;
						gbc_btnLeft2.gridy = 1;
						leftPanel.add(btnLeft2, gbc_btnLeft2);
				
						btnLeft3 = new JButton("");
						btnLeft3.setFont(new Font("SansSerif", Font.BOLD, 16));
						btnLeft3.setBackground(UIManager.getColor("Button.background"));
						GridBagConstraints gbc_btnLeft3 = new GridBagConstraints();
						gbc_btnLeft3.fill = GridBagConstraints.BOTH;
						gbc_btnLeft3.gridx = 0;
						gbc_btnLeft3.gridy = 2;
						leftPanel.add(btnLeft3, gbc_btnLeft3);

		JPanel bottomPanel = new JPanel();
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));

		Component rigidArea_2 = Box.createRigidArea(new Dimension(100, 20));
		bottomPanel.add(rigidArea_2, BorderLayout.WEST);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(100, 200));
		bottomPanel.add(rigidArea_3, BorderLayout.EAST);

		JPanel keypadPanel = new JPanel();
		bottomPanel.add(keypadPanel, BorderLayout.CENTER);
		keypadPanel.setLayout(new GridLayout(4, 4, 0, 0));

		NumericButtonListener numericButtonListener = new NumericButtonListener(this);
		ClearButtonListener clearButtonListener = new ClearButtonListener(this);
		EnterButtonListener enterButtonListener = new EnterButtonListener(this);

		JButton btn1 = new JButton("1");
		btn1.addActionListener(numericButtonListener);
		btn1.setFont(new Font("Tahoma", Font.BOLD, 20));
		keypadPanel.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.addActionListener(numericButtonListener);
		btn2.setFont(new Font("Tahoma", Font.BOLD, 20));
		keypadPanel.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.addActionListener(numericButtonListener);
		btn3.setFont(new Font("Tahoma", Font.BOLD, 20));
		keypadPanel.add(btn3);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		keypadPanel.add(btnDelete);

		JButton btn4 = new JButton("4");
		btn4.addActionListener(numericButtonListener);
		btn4.setFont(new Font("Tahoma", Font.BOLD, 20));
		keypadPanel.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.addActionListener(numericButtonListener);
		btn5.setFont(new Font("Tahoma", Font.BOLD, 20));
		keypadPanel.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.addActionListener(numericButtonListener);
		btn6.setFont(new Font("Tahoma", Font.BOLD, 20));
		keypadPanel.add(btn6);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(clearButtonListener);
		btnClear.setBackground(new Color(218, 165, 32));
		btnClear.setForeground(Color.BLACK);
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 16));
		keypadPanel.add(btnClear);

		JButton btn7 = new JButton("7");
		btn7.addActionListener(numericButtonListener);
		btn7.setFont(new Font("Tahoma", Font.BOLD, 20));
		keypadPanel.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.addActionListener(numericButtonListener);
		btn8.setFont(new Font("Tahoma", Font.BOLD, 20));
		keypadPanel.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.addActionListener(numericButtonListener);
		btn9.setFont(new Font("Tahoma", Font.BOLD, 20));
		keypadPanel.add(btn9);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setBackground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		keypadPanel.add(btnCancel);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		keypadPanel.add(rigidArea_1);

		JButton btn0 = new JButton("0");
		btn0.addActionListener(numericButtonListener);
		btn0.setFont(new Font("Tahoma", Font.BOLD, 20));
		keypadPanel.add(btn0);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		keypadPanel.add(rigidArea);

		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(enterButtonListener);
		btnEnter.setBackground(new Color(0, 128, 0));
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setFont(new Font("Tahoma", Font.BOLD, 16));
		keypadPanel.add(btnEnter);

		JPanel rightPanel = new JPanel();
		this.getContentPane().add(rightPanel, BorderLayout.EAST);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[] {150};
		gbl_rightPanel.rowHeights = new int[] {105, 105, 105};
		gbl_rightPanel.columnWeights = new double[]{1.0};
		gbl_rightPanel.rowWeights = new double[]{1.0, 1.0, 1.0};
		rightPanel.setLayout(gbl_rightPanel);
		
				btnRight1 = new JButton("");
				btnRight1.setFont(new Font("SansSerif", Font.BOLD, 16));
				btnRight1.setBackground(UIManager.getColor("Button.background"));
				GridBagConstraints gbc_btnRight1 = new GridBagConstraints();
				gbc_btnRight1.fill = GridBagConstraints.BOTH;
				gbc_btnRight1.insets = new Insets(0, 0, 5, 0);
				gbc_btnRight1.gridx = 0;
				gbc_btnRight1.gridy = 0;
				rightPanel.add(btnRight1, gbc_btnRight1);
				
						btnRight2 = new JButton("");
						btnRight2.setFont(new Font("SansSerif", Font.BOLD, 16));
						btnRight2.setBackground(UIManager.getColor("Button.background"));
						GridBagConstraints gbc_btnRight2 = new GridBagConstraints();
						gbc_btnRight2.fill = GridBagConstraints.BOTH;
						gbc_btnRight2.insets = new Insets(0, 0, 5, 0);
						gbc_btnRight2.gridx = 0;
						gbc_btnRight2.gridy = 1;
						rightPanel.add(btnRight2, gbc_btnRight2);
		
				btnRight3 = new JButton("");
				btnRight3.setFont(new Font("SansSerif", Font.BOLD, 16));
				btnRight3.setBackground(UIManager.getColor("Button.background"));
				GridBagConstraints gbc_btnRight3 = new GridBagConstraints();
				gbc_btnRight3.fill = GridBagConstraints.BOTH;
				gbc_btnRight3.gridx = 0;
				gbc_btnRight3.gridy = 2;
				rightPanel.add(btnRight3, gbc_btnRight3);

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getLblTop() {
		return lblTop;
	}

	public JLabel getLblMiddle() {
		return lblMiddle;
	}

	public JLabel getLblBottom() {
		return lblBottom;
	}

	public JButton getBtnLeft1() {
		return btnLeft1;
	}

	public JButton getBtnLeft2() {
		return btnLeft2;
	}

	public JButton getBtnLeft3() {
		return btnLeft3;
	}

	public JButton getBtnRight1() {
		return btnRight1;
	}

	public JButton getBtnRight2() {
		return btnRight2;
	}

	public JButton getBtnRight3() {
		return btnRight3;
	}
	
}
