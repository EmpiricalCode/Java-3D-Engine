package Core.Utility;

import java.awt.Color;

import Core.Environment;

public class Ray {

    private Vector3D origin;
    private Vector3D direction;

    private Environment environment;
    
    // Main constructor
    public Ray(Vector3D origin, Vector3D direction, Environment environment) {
        this.origin = origin;
        this.direction = direction;
        this.environment = environment;

        // Clamping direction to a unit vector
        direction.clamp(1);
    }

    // Getter method for origin
    public Vector3D getOrigin() {
        return this.origin;
    }

    // Getter method for direction
    public Vector3D getDirection() {
        return this.direction;
    }

    // Returns the color accumulated by "tracing" this ray through the environment
    public Color getColor() {
        return Environment.getAmbientColor(this.direction);
    }
}
