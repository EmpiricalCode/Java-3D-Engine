package Core.Utility;

public class Camera {

    private Vector3D origin;
    private Vector3D direction;
    
    // Main constructor
    public Camera(Vector3D origin, Vector3D destination) {
        this.origin = origin;
        this.direction = Vector3D.subtract(destination, origin);

        // Clamping direction to a magnitude of 10
        this.direction.clamp(10);
    }

    // Getter method for origin
    public Vector3D getOrigin() {
        return this.origin;
    }

    // Getter method for destination
    public Vector3D getDirection() {
        return this.direction;
    }
}