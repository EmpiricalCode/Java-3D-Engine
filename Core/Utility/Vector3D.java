package Core.Utility;

public class Vector3D {

    private double x;
    private double y;
    private double z;
    
    // Main constructor
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Returns the x coordinate
    public double getX() {
        return this.x;
    }

    // Returns the y coordinate
    public double getY() {
        return this.y;
    }

    // Returns the z coordinate
    public double getZ() {
        return this.z;
    }
}