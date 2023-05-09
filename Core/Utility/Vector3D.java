package Core.Utility;

public class Vector3D {

    // FOR REFERENCE:
    // x and y are planar coordinates (2D)
    // z represents depth/height
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

    // Clamps the vector down to a certain distance
    public void clamp(double distance) {

        double currentDistance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));

        this.x *= distance / currentDistance;
        this.y *= distance / currentDistance;
        this.z *= distance / currentDistance;
    }

    // For debugging reasons
    public String toString() {
        return this.x + " " + this.y + " " + this.z;
    }

    // Subtracts two vectors
    public static Vector3D subtract(Vector3D a, Vector3D b) {
        return new Vector3D(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }

    // Adds two vectors
    public static Vector3D add(Vector3D a, Vector3D b) {
        return new Vector3D(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

    // Finds the cross product between two vectors
    public static Vector3D cross(Vector3D a, Vector3D b) {
        double nx = a.getY() * b.getZ() - a.getZ() * b.getY();
        double ny = a.getZ() * b.getX() - a.getX() * b.getZ();
        double nz = a.getX() * b.getY() - a.getY() * b.getX();

        return new Vector3D(nx, ny, nz);
    }
}