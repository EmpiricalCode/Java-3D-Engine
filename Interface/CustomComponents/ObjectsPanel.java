package Interface.CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class ObjectsPanel extends JPanel {

    private JPanel addObjectsArea;
    private JPanel objectsArea;
    
    public ObjectsPanel(int width, int height) {
        super();

        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(0, 0, 0));

        addObjectsArea = new JPanel();
        addObjectsArea.setPreferredSize(new Dimension(width, 100));
        addObjectsArea.setBackground(Color.red);

        objectsArea = new JPanel();
        objectsArea.setPreferredSize(new Dimension(width, 100));
        objectsArea.setBackground(Color.green);

        this.add(addObjectsArea);
        this.add(objectsArea);
    }
}
