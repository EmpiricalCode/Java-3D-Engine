package Core.Entities;

import Core.Structures.Entity;
import Core.Utility.*;

public class Sphere extends Entity {
    
    private double radius;

    // Main constructor
    public Sphere(Vector3D position, double radius) {

        super(position);

        this.radius = radius;
    }

    // Returns the hit of the object from a raycast
    public Vector3D getHit (Ray ray) {
        return null;
    }
}
