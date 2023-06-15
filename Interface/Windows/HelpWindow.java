package Interface.Windows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import Interface.Structures.Window;
import Interface.Utility.FontLoader;

public class HelpWindow extends Window {

    public static String WINDOW_TITLE = "Help";
    public static String[] HELP_MESSAGE_TITLES = {
        "What is Quality?",
        "What is Pixel Samples?",
        "What is Ray Depth?",
        "What is Gamma?",
        "What is Camera Position?",
        "What is Camera Look At?",
        "What is Anti Aliasing?",
        "What is Diffuse and Specular reflection?"
    };

    public static String[] HELP_MESSAGES = {
        "A number between 1 and 3. the higher the number, the higher the render resolution.",
        "The number of rays shot per pixels. The higher the pixel samples, the less grainy diffuse objects are.",
        "The amount of times a ray bounces within the environment.",
        "The higher the gamma, the brighter the environment will appear. Gamma is capped between 1 and 3.",
        "The 3D position of the camera within the environment.",
        "The 3D position where the camera looks towards. Try creating an object that is at this position.",
        "If set to true, the render will be much smoother, but take longer.",
        "Diffuse reflection produces a rough object, specular reflection produces a reflective object."
    };

    private JLabel title;
    private JLabel description;
    private GridBagConstraints constraints;
    
    // Creates a new help window
    public HelpWindow() {
        super(HelpWindow.WINDOW_TITLE, new GridBagLayout(), 800, 600);

        this.getContentPane().setBackground(MainWindow.BACKGROUND_COLOR);
        this.setVisible(true);
        this.setResizable(false);

        // Creating grid bag constraints
        this.constraints = new GridBagConstraints();
        this.constraints.anchor = GridBagConstraints.WEST;
        this.constraints.gridwidth = GridBagConstraints.REMAINDER;

        // Adding help messages
        for (int i = 0; i < HELP_MESSAGE_TITLES.length; i++) {

            // Adding title
            this.title = new JLabel(HelpWindow.HELP_MESSAGE_TITLES[i]);
            this.title.setForeground(Color.WHITE);
            this.title.setFont(FontLoader.loadFont("montserrat_semibold", 17));

            // If the this is the first help message, add extra margin to the top to space the help content from the top of the JFrame
            if (i == 0) {
                this.title.setBorder(new EmptyBorder(30, 20, 0, 0));
            } else {
                this.title.setBorder(new EmptyBorder(15, 20, 0, 0));
            }

            // Adding description
            this.description = new JLabel(HelpWindow.HELP_MESSAGES[i]);
            this.description.setForeground(new Color(200, 200, 200));
            this.description.setBorder(new EmptyBorder(0, 20, 0, 0));
            this.description.setFont(FontLoader.loadFont("montserrat_medium", 14));

            // Adding title and description to the content pane
            this.add(this.title, this.constraints);
            this.add(this.description, this.constraints);
        }

        // These two lines of code are necessary to align the content to the top
        this.constraints.weighty = 1;
        add(new JLabel(), this.constraints);
    }
}
