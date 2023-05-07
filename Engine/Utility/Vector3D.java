package Engine.Utility;

public class Vector3D {

    private int x;
    private int y;
    private int z;
    
    // Main constructor
    public Vector3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Returns the x coordinate
    public int getX() {
        return this.x;
    }

    // Returns the y coordinate
    public int getY() {
        return this.y;
    }

    // Returns the z coordinate
    public int getZ() {
        return this.z;
    }
}