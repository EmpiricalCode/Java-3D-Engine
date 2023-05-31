import Interface.Windows.MainWindow;

public class Main {

    public static void main(String[] args) {

        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");

        // Creating the main window
        new MainWindow();
    }
}