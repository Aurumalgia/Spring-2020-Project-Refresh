package view;

import javax.swing.*;
import java.awt.*;

public class ViewFrame extends JFrame {
    private MapPanel panel;
    private GUIPanel gui;

    public ViewFrame(MapPanel panel, GUIPanel gui){
        this.panel=panel;
        this.gui=gui;
        setLayout(new BorderLayout());
        add(this.panel, BorderLayout.CENTER);
        add(this.gui, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public MapPanel getPanel() {
        return panel;
    }

    public void setPanel(MapPanel panel) {
        this.panel = panel;
    }

    public GUIPanel getGui() {
        return gui;
    }

    public void setGui(GUIPanel gui) {
        this.gui = gui;
    }
}
