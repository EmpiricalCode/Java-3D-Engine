package Interface.CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.*;

import Interface.Windows.MainWindow;

public class MaterialsPanel extends JPanel {

    private JPanel titleArea;
    private JLabel materialsTitle;
    private JLabel materialsSubtitle;
    private JPanel materialsArea;
    
    public MaterialsPanel(int width, int height) {
        super();

        this.setBorder(new MatteBorder(1, 0, 0, 0, MainWindow.BORDER_COLOR));
        this.setBackground(MainWindow.BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(0, 0, 0));

        titleArea = new JPanel();
        titleArea.setPreferredSize(new Dimension(width, 120));
        titleArea.setBackground(MainWindow.BACKGROUND_COLOR);
        titleArea.setBorder(new EmptyBorder(35, 40, 0, 0));
        titleArea.setLayout(new BoxLayout(titleArea, BoxLayout.Y_AXIS));

        materialsTitle = new JLabel("Materials");
        materialsTitle.setFont(MainWindow.TITLE_FONT);
        materialsTitle.setForeground(Color.WHITE);

        materialsSubtitle = new JLabel("Rectangular Prism");
        materialsSubtitle.setFont(MainWindow.SUBTITLE_FONT);
        materialsSubtitle.setForeground(MainWindow.SUBTITLE_COLOR);

        materialsArea = new JPanel();
        materialsArea.setPreferredSize(new Dimension(width, MainWindow.HEIGHT));
        materialsArea.setBorder(new MatteBorder(new Insets(1, 0, 0, 0), MainWindow.BORDER_COLOR));
        materialsArea.setLayout(new FlowLayout(0, 0, 0));
        materialsArea.setBackground(MainWindow.BACKGROUND_COLOR);

        titleArea.add(materialsTitle);
        titleArea.add(materialsSubtitle);
        this.add(titleArea);
        this.add(materialsArea);
    }
}
