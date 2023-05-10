package Core.Utility;

public class RayHit {

    private Vector3D position;
    private Vector3D normal;

    // Main constructor
    public RayHit(Vector3D position, Vector3D normal) {
        this.position = position;
        this.normal = normal;
    }

    // Getter method for position
    public Vector3D getPosition() {
        return this.position;
    }

    // Getter method for normal
    public Vector3D getNormal() {
        return this.normal;
    }
}