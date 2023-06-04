///////////////////////
//
// Filename: RenderSettingsPanel.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the creation and user interactions of the render settings panel. This panel allows users to modify scene/lighting/etc settings.
//
///////////////////////

package Interface.CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.*;

import Interface.Windows.MainWindow;

public class RenderSettingsPanel extends JPanel {

    JLabel title;
    JPanel titleArea;
    JPanel renderSettingsArea;
    
    public RenderSettingsPanel(int width, int height) {
        super();

        this.setBorder(new MatteBorder(0, 1, 0, 0, MainWindow.BORDER_COLOR));
        this.setBackground(MainWindow.BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(0, 0, 0));

        this.titleArea = new JPanel();
        this.titleArea.setPreferredSize(new Dimension(width, 90));
        this.titleArea.setBackground(MainWindow.BACKGROUND_COLOR);
        this.titleArea.setBorder(new EmptyBorder(29, 40, 0, 0));
        this.titleArea.setLayout(new BoxLayout(titleArea, BoxLayout.Y_AXIS));

        this.title = new JLabel("Render Settings");
        this.title.setFont(MainWindow.TITLE_FONT);
        this.title.setForeground(Color.WHITE);

        this.renderSettingsArea = new JPanel();
        this.renderSettingsArea.setPreferredSize(new Dimension(width, MainWindow.HEIGHT));
        this.renderSettingsArea.setBorder(new MatteBorder(1, 0, 0, 1, MainWindow.BORDER_COLOR));
        this.renderSettingsArea.setBackground(MainWindow.BACKGROUND_COLOR);

        this.titleArea.add(this.title);
        this.add(this.titleArea);
        this.add(this.renderSettingsArea);
    }
}