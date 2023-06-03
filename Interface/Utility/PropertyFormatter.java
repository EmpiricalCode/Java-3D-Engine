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

    public static String formatTripleDouble(String fieldText) {

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

    public static String formatTripleInt(String fieldText) {

        String formattedTripleDouble = formatTripleDouble(fieldText);
        String decomposed[];
        String ret = "";

        if (formattedTripleDouble != null) {

            decomposed = formattedTripleDouble.split(", ");
            
            for (String element : decomposed) {
                ret += ", " + (int) Double.parseDouble(element);
            }

            return ret.substring(2);
        }

        return null;
    }

    public static String formatDouble(String fieldText) {
        
        if (isNumeric(fieldText)) {
            return String.valueOf(Double.parseDouble(fieldText));
        }

        return null;
    }

    public static String formatInt(String fieldText) {
        
        if (isNumeric(fieldText)) {
            return String.valueOf((int) Double.parseDouble(fieldText));
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
