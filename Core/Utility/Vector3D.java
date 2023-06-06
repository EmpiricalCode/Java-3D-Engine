///////////////////////
//
// Filename: Vector3D.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that packages together XYZ coordinates and allows for certain operations to be carried out on them.
//
///////////////////////

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

        double magnitude = this.getMagnitude();

        this.x *= distance / magnitude;
        this.y *= distance / magnitude;
        this.z *= distance / magnitude;
    }

    // Subtracts an external vector from this vector
    public void subtract(Vector3D vector) {
        this.x -= vector.getX();
        this.y -= vector.getY();
        this.z -= vector.getZ();
    }

    // Adds an external vector to this vector
    public void add(Vector3D vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        this.z += vector.getZ();
    }

    // Multiplies each coordinate of the vector by a scalar value
    public void multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
    }

    // Finds the dot product with another vector
    public double dot(Vector3D vector) {
        return this.x * vector.getX() + this.y * vector.getY() + this.z * vector.getZ();
    }
 
    // Gets the magnitude of the vector
    public double getMagnitude() {
        return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));
    }

    // Gets the distance between this vector and another vector
    public double getDistance(Vector3D vector) {
        return Vector3D.subtract(vector, this).getMagnitude();
    }

    // TODO: Remember to remove toString
    // For debugging reasons
    public String toString() {
        return Math.round(this.x * 100) / 100.0 + ", " + Math.round(this.y * 100) / 100.0 + ", " + Math.round(this.z * 100) / 100.0;
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

    // Multiplies each coordinate of a vector by a scalar value
    public static Vector3D multiply(Vector3D vector, double scalar) {
        return new Vector3D(vector.getX() * scalar, vector.getY() * scalar, vector.getZ() * scalar);
    }
}