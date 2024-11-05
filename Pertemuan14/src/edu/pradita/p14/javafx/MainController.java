package edu.pradita.p14.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Desktop;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;

public class MainController {

	@FXML
	private MenuItem menuItemPrint;
	@FXML
	private MenuItem menuItemClose;
	@FXML
	private MenuItem menuItemOpenItem;
	@FXML
	private MenuItem menuItemAbout;
	@FXML
	private MenuItem menuItemOpenSalesOrder;
	@FXML
	private BorderPane centerPane;
	public static Connection CONNECTION;

	public void initialize() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost:3306/pradita", "alfa", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void openItem() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
			Parent itemFormRoot = loader.load();
			centerPane.setCenter(itemFormRoot);
//            Stage itemFormStage = new Stage();
//            itemFormStage.setTitle("Item");
//            itemFormStage.setScene(new Scene(itemFormRoot));
//            itemFormStage.show();
		} catch (IOException e) {
			e.printStackTrace();
			showAlert("Error", "Could not open Item Form.");
		}
	}

	@FXML
	private void openSalesOrder() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SalesOrder.fxml"));
			Parent salesOrderRoot = loader.load();
			centerPane.setCenter(salesOrderRoot);
		} catch (IOException e) {
			e.printStackTrace();
			showAlert("Error", "Could not open Sales Order Form.");
		}
	}

	@FXML
	private void showAbout() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("Order Form Application");
		alert.setContentText("Developed with JavaFX and BIRT for report generation.");
		alert.showAndWait();
	}

	@FXML
	private void printReport() {
		new Thread(() -> {
			try {
				EngineConfig config = new EngineConfig();
				Platform.startup(config);
				IReportEngineFactory factory = (IReportEngineFactory) Platform
						.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
				IReportEngine engine = factory.createReportEngine(config);

				IReportRunnable design = engine.openReportDesign("reports/test.rptdesign");
				IRunAndRenderTask task = engine.createRunAndRenderTask(design);
				task.setParameterValue("order_code", SalesOrderController.getCurrentOrderCode());
				PDFRenderOption options = new PDFRenderOption();
				options.setOutputFileName("reports/test.pdf");
				options.setOutputFormat("pdf");

				task.setRenderOption(options);
				task.run();
				task.close();
				engine.destroy();

				if (Desktop.isDesktopSupported()) {
					File myFile = new File("reports/test.pdf");
					Desktop.getDesktop().open(myFile);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Platform.shutdown();
			}
		}).start();

	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
