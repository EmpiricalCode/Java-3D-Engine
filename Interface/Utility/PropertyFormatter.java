///////////////////////
//
// Filename: PropertyFormatter.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that formats property values inputted within a property field to be displayed on the screen.
//
///////////////////////

package Interface.Utility;

public class PropertyFormatter {
    
    // All these methods return null when a property is set in an invalid way

    // Formats the position property field text
    public static String formatPosition(String fieldText) {

        String[] decomposed = fieldText.split(",");
        String ret = "";

        if (decomposed.length != 3) {
            return null;
        }

        // Formatting the string to ", a, b, c"
        for (String element : decomposed) {
            element = element.replaceAll(" ", "");

            if (!PropertyFormatter.isNumeric(element)) {
                return null;
            }

            ret += ", " + Double.parseDouble(element);
        }

        // Removes first two characters
        return ret.substring(2);
    }

    // Formats the color property field text
    public static String formatColor(String fieldText) {

        // Reusing formatPosition method
        String formattedTripleDouble = PropertyFormatter.formatPosition(fieldText);
        String decomposed[];
        String ret = "";
        
        if (formattedTripleDouble != null) {

            decomposed = formattedTripleDouble.split(", ");

            // Casting doubles to ints
            for (String element : decomposed) {
                ret += ", " + Math.min(255, Math.max(0, (int) Double.parseDouble(element)));
            }

            return ret.substring(2);
        }

        return null;
    }

    // Formats the fuzziness property field text
    public static String formatFuzziness(String fieldText) {
        if (PropertyFormatter.isNumeric(fieldText)) {
            return String.valueOf(Math.min(1, Math.max(Double.parseDouble(fieldText), 0)));
        }

        return null;
    }

    // Formats the radius property field text
    public static String formatRadius(String fieldText) {
        if (PropertyFormatter.isNumeric(fieldText) && Double.parseDouble(fieldText) > 0) {
            return String.valueOf(Double.parseDouble(fieldText));
        }

        return null;
    }

    // Formats the pixel samples property field text
    public static String formatPixelSamples(String fieldText) {
        if (PropertyFormatter.isInteger(fieldText)) {
            return String.valueOf(Math.min(100, Math.max(Integer.parseInt(fieldText), 1)));
        }

        return null;
    }

    // Formats the quality property field text
    public static String formatQuality(String fieldText) {
        if (PropertyFormatter.isInteger(fieldText)) {
            return String.valueOf(Math.min(3, Math.max(Integer.parseInt(fieldText), 1)));
        }

        return null;
    }

    // Formats the gamma property field text
    public static String formatGamma(String fieldText) {
        if (PropertyFormatter.isNumeric(fieldText)) {
            return String.valueOf(Math.min(4, Math.max(Double.parseDouble(fieldText), 1)));
        }

        return null;
    }

    // Checks if a string is an integer
    private static boolean isInteger(String string) {

        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // Checks if a string is numeric
    private static boolean isNumeric(String string) {

        try {
            Double.parseDouble(string);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
