///////////////////////
//
// Filename: Camera.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the properties of the environment's camera (for calculation use).
//
///////////////////////

package Core.Utility;

public class Camera {

    // The camera consists of an origin, and a virtual "screen"
    // This screen is CAMERA_SCREEN_DISTANCE away from the origin, and is CAMERA_SCREEN_VERTICAL_SIZE by CAMERA_SCREEN_HORIZONTAL_SIZE large.
    public static final double CAMERA_SCREEN_DISTANCE = 5;
    public static final double CAMERA_SCREEN_VERTICAL_SIZE = 10;
    public static final double CAMERA_SCREEN_HORIZONTAL_SIZE = 10;

    private Vector3D origin;
    private Vector3D direction;
    
    // Creates a new Camera object
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

    // Getter method for direction
    public Vector3D getDirection() {
        return this.direction;
    }
}