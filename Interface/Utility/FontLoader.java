///////////////////////
//
// Filename: FontLoader.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the loading of custom fonts.
//
///////////////////////

package Interface.Utility;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FontLoader {
    
    public static Font loadFont(String name, int size) { 

        String homeDir = System.getProperty("user.dir");

        try (InputStream is = new BufferedInputStream(
            Files.newInputStream(Paths.get(homeDir + "/Assets/Fonts/" + name + ".ttf")))) {
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, size);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
