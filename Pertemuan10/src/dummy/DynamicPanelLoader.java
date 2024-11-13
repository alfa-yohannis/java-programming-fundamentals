package dummy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicPanelLoader {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the main JFrame
            JFrame mainFrame = new JFrame("Dynamic Panel Loader");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(500, 400);

            // Create a menu bar and add it to the frame
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("Load Panel");
            JMenuItem loadPanel1 = new JMenuItem("Load Panel 1");
            JMenuItem loadPanel2 = new JMenuItem("Load Panel 2");

            menu.add(loadPanel1);
            menu.add(loadPanel2);
            menuBar.add(menu);
            mainFrame.setJMenuBar(menuBar);

            // Main panel where the dynamic content will be displayed
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainFrame.add(mainPanel);

            // Create instances of the panels
            Panel1 panel1 = new Panel1();
            Panel2 panel2 = new Panel2();

            // Action listeners for menu items
            loadPanel1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadPanel(mainPanel, panel1);
                }
            });

            loadPanel2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadPanel(mainPanel, panel2);
                }
            });

            // Show the main frame
            mainFrame.setVisible(true);
        });
    }

    // Method to load a panel into the mainPanel
    private static void loadPanel(JPanel mainPanel, JPanel panelToLoad) {
        mainPanel.removeAll();
        mainPanel.add(panelToLoad, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
