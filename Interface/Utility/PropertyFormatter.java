///////////////////////
//
// Filename: PropertyFormatter.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that formats property values to be displayed on the screen.
//
///////////////////////

package Interface.Utility;

public class PropertyFormatter {

    // Formats the position property field text
    public static String formatPosition(String fieldText) {

        String[] decomposed = fieldText.split(",");
        String ret = "";

        if (decomposed.length != 3) {
            return null;
        }

        for (String element : decomposed) {
            element = element.replaceAll(" ", "");

            if (!PropertyFormatter.isNumeric(element)) {
                return null;
            }

            ret += ", " + Double.parseDouble(element);
        }

        return ret.substring(2);
    }

    // Formats the color property field text
    public static String formatColor(String fieldText) {

        String formattedTripleDouble = PropertyFormatter.formatPosition(fieldText);
        String decomposed[];
        String ret = "";

        if (formattedTripleDouble != null) {

            decomposed = formattedTripleDouble.split(", ");
            
            for (String element : decomposed) {
                ret += ", " + Math.min(255, Math.max(0, (int) Double.parseDouble(element)));
            }

            return ret.substring(2);
        }

        return null;
    }

    // Formats the fuzziness property field text
    public static String formatFuzziness(String fieldText) {
        if (isNumeric(fieldText)) {
            return String.valueOf(Math.min(1, Math.max(Double.parseDouble(fieldText), 0)));
        }

        return null;
    }

    // Formats the radius property field text
    public static String formatRadius(String fieldText) {
        if (isNumeric(fieldText) && Double.parseDouble(fieldText) > 0) {
            return String.valueOf(Double.parseDouble(fieldText));
        }

        return null;
    }

    private static boolean isNumeric(String strNum) {

        try {
            Double.parseDouble(strNum);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
