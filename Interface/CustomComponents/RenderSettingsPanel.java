package Interface.CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.*;

import Interface.Utility.FontLoader;
import Interface.Windows.MainWindow;

public class RenderSettingsPanel extends JPanel {

    
    public RenderSettingsPanel(int width, int height) {
        super();

        this.setBorder(new MatteBorder(0, 1, 0, 0, MainWindow.BORDER_COLOR));
        this.setBackground(MainWindow.BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(0, 0, 0));

    }
}
