package dummy;
import javax.swing.*;
import java.awt.*;

public class Panel2 extends JPanel {
    public Panel2() {
        setBackground(Color.LIGHT_GRAY);
        add(new JLabel("This is Panel 2"));
        add(new JButton("Button in Panel 2"));
    }
}
