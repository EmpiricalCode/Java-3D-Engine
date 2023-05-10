package Core.Structures;

import java.awt.Color;

import Core.Utility.*;

public abstract class Entity {

    private Vector3D position;
    private Color color;

    // Main constructor
    public Entity(Vector3D position, Color color) {
        this.position = position;
        this.color = color;
    }

    // Returns the position of the object
    public Vector3D getPosition() {
        return this.position;
    }

    // Returns the color of the object
    public Color getColor() {
        return this.color;
    }

    // Returns the hit of the object from a raycast
    abstract public RayHit getHit (Ray ray);
}
