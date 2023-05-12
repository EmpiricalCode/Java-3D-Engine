package Core.Structures;

import Core.Utility.*;

public abstract class Entity {

    private Vector3D position;
    private ColorRGB color;

    // Main constructor
    public Entity(Vector3D position, ColorRGB color) {
        this.position = position;
        this.color = color;
    }

    // Returns the position of the object
    public Vector3D getPosition() {
        return this.position;
    }

    // Returns the color of the object
    public ColorRGB getColor() {
        return this.color;
    }

    // Returns the hit of the object from a raycast
    abstract public RayHit getHit (Ray ray);

    // Returns a normal vector of the object given position
    abstract public Vector3D getNormal(Ray ray, Vector3D hitPosition);
}
