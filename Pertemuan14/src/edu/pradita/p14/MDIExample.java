package edu.pradita.p14;
import javax.swing.*;

public class MDIExample extends JFrame {
    public MDIExample() {
        // Set up the main JFrame
        setTitle("MDI Example with Swing");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a JDesktopPane to act as the MDI container
        JDesktopPane desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        // Add a few internal frames
        for (int i = 1; i <= 3; i++) {
            JInternalFrame internalFrame = new JInternalFrame("Document " + i, true, true, true, true);
            internalFrame.setSize(300, 200);
            internalFrame.setVisible(true);

            // Add the internal frame to the desktop pane
            desktopPane.add(internalFrame);

            // Position the internal frames in a cascading layout
            internalFrame.setLocation(i * 30, i * 30);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MDIExample mdi = new MDIExample();
            mdi.setVisible(true);
        });
    }
}
