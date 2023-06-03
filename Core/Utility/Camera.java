///////////////////////
//
// Filename: Camera.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles properties of the environment's camera (for calculation use).
//
///////////////////////

package Core.Utility;

public class Camera {

    public static final double CAMERA_SCREEN_DISTANCE = 5;

    private Vector3D origin;
    private Vector3D direction;
    
    // Main constructor
    public Camera(Vector3D origin, Vector3D destination) {
        this.origin = origin;
        this.direction = Vector3D.subtract(destination, origin);

        // Clamping direction to a magnitude of 10
        this.direction.clamp(Camera.CAMERA_SCREEN_DISTANCE);
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