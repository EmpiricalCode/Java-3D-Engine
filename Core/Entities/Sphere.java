package Core.Entities;

import java.awt.Color;

import Core.Structures.Entity;
import Core.Utility.*;

public class Sphere extends Entity {
    
    private double radius;

    // Main constructor
    public Sphere(Vector3D position, Color color, double radius) {

        super(position, color);

        this.radius = radius;
    }

    // Returns the hit of the object from a raycast
    public RayHit getHit (Ray ray) {
        return null;
    }
}
