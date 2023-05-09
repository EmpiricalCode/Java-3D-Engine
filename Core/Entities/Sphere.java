package Core.Entities;

import Core.Structures.Entity;
import Core.Utility.Vector3D;

public class Sphere extends Entity {
    
    private double radius;

    // Main constructor
    public Sphere(Vector3D position, double radius) {

        super(position);

        this.radius = radius;
    }
}
