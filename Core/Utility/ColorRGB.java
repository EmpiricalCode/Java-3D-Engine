///////////////////////
//
// Filename: ColorRGB.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles RGB color values, providing custom methods to make certain operations more convenient.
//
///////////////////////

package Core.Utility;

public class ColorRGB {

    private int r;
    private int g;
    private int b;
    
    // Main constructor
    public ColorRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    // Returns the red value
    public int getR() {
        return this.r;
    }

    // Returns the green value
    public int getG() {
        return this.g;
    }

    // Returns the blue value
    public int getB() {
        return this.b;
    }

    // Subtracts two colors
    public static ColorRGB subtract(ColorRGB a, ColorRGB b) {
        return new ColorRGB(a.getR() - b.getR(), a.getG() - b.getG(), a.getB() - b.getB());
    }

    // Adds two colors
    public static ColorRGB add(ColorRGB a, ColorRGB b) {
        return new ColorRGB(a.getR() + b.getR(), a.getG() + b.getG(), a.getB() + b.getB());
    }

    // Multiplies each of the color's values by a scalar
    public static ColorRGB multiply(ColorRGB a, double scalar) {
        return new ColorRGB((int) (a.getR() * scalar), (int) (a.getG() * scalar), (int) (a.getB() * scalar));
    }

    // Returns the color as a string
    public String toString() {
        return this.r + ", " + this.g + ", " + this.b;
    }
}