package edu.pradita.p14b;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainForm extends JFrame {

    private static final long serialVersionUID = 119865768966605004L;
    
    private IForm currentForm;
	private JPanel mainPanel;

	public static Connection CONNECTION;
	
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    	
    	// initialize connection to database
    			Class.forName("com.mysql.cj.jdbc.Driver");
    			CONNECTION = DriverManager //
    			    .getConnection("jdbc:mysql://localhost:3306/pradita", "alfa", "1234");
    			
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainForm mainForm = new MainForm();
                    mainForm.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainForm() {
        initialize();
    }

    private void initialize() {
        this.setTitle("Main Form");
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					CONNECTION.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		this.setLocation(centerPoint.x - (int) this.getSize().getWidth() / 2,
		    centerPoint.y - (int) this.getSize().getHeight() / 2);
        
        mainPanel = new JPanel();
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        
		// FILE
		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic('F');
		menuBar.add(menuFile);
		
		JMenuItem menuItemPrint = new JMenuItem("Print");
		menuItemPrint.setMnemonic('P');
		menuItemPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					EngineConfig config = new EngineConfig();
					Platform.startup(config);
					final IReportEngineFactory FACTORY = (IReportEngineFactory) Platform
					    .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
					IReportEngine engine = FACTORY.createReportEngine(config);

					// Open the report design
					IReportRunnable design = null;
					design = engine.openReportDesign("reports/test.rptdesign");
					IRunAndRenderTask task = engine.createRunAndRenderTask(design);
			
					task.setParameterValue("order_code", currentForm.getDocumentCode());
					task.validateParameters();

					PDFRenderOption options = new PDFRenderOption();
					options.setOutputFileName("reports/document.pdf");
					options.setOutputFormat("pdf");

					task.setRenderOption(options);
					task.run();
					task.close();
					engine.destroy();

					if (Desktop.isDesktopSupported()) {
						File myFile = new File("reports/document.pdf");
						Desktop.getDesktop().open(myFile);
					}

				} catch (Exception EX) {
					EX.printStackTrace();
				} finally {
					Platform.shutdown();
				}
				
			}
		});
		menuFile.add(menuItemPrint);
		
		// MASTER DATA
        JMenu menuMasterData = new JMenu("Master Data");
		menuMasterData.setMnemonic('M');
		menuBar.add(menuMasterData);

		JMenuItem menuItemItem = new JMenuItem("Item");
		menuItemItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				loadItemForm();
			}
		});
		menuMasterData.add(menuItemItem);
		
		// TRANSACTION
        JMenu transactionMenu = new JMenu("Transaction");
        transactionMenu.setMnemonic('T');
        menuBar.add(transactionMenu);

        JMenuItem salesOrderMenuItem = new JMenuItem("Sales Order");
        salesOrderMenuItem.setMnemonic('S');
        transactionMenu.add(salesOrderMenuItem);

        salesOrderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadSalesOrderForm();
            }
        });
    }

    private void loadItemForm() {
        mainPanel.removeAll();
        try {
            ItemForm itemForm = new ItemForm(MainForm.this);
            MainForm.this.currentForm = itemForm;
            mainPanel.add(itemForm);
            this.revalidate();
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadSalesOrderForm() {
        mainPanel.removeAll();
        try {
            SalesOrderForm salesOrderForm = new SalesOrderForm(MainForm.this);
            MainForm.this.currentForm = salesOrderForm;
            mainPanel.add(salesOrderForm);
            this.revalidate();
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
