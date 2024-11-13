package dummy;
import javax.swing.*;
import java.awt.*;

public class Panel1 extends JPanel {
    public Panel1() {
        setBackground(Color.CYAN);
        add(new JLabel("This is Panel 1"));
        add(new JButton("Button in Panel 1"));
    }
}
