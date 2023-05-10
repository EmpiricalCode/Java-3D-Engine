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

    // Returns the radius of the sphere
    public double getRadius() {
        return this.radius;
    }

    // Returns the hit of the object from a raycast
    public RayHit getHit (Ray ray) {
        
        Vector3D hitLocation;
        RayHit hit;
        double hitDistance;
        double hitDistancePrime;
        double perpendicularDistance;

        Vector3D distanceVector = Vector3D.subtract(this.getPosition(), ray.getOrigin());
        double dotProduct = distanceVector.dot(ray.getDirection());

        if (dotProduct >= 0) {

            perpendicularDistance = Math.sqrt(Math.pow(distanceVector.getMagnitude(), 2) - Math.pow(dotProduct, 2));

            if (perpendicularDistance <= this.radius) {

                hitDistancePrime = Math.sqrt(Math.pow(this.radius, 2) - Math.pow(perpendicularDistance, 2));

                System.out.println(hitDistancePrime);

                return new RayHit(distanceVector, distanceVector);
            }
            
            return null;
            
        } else {
            return null;
        }
    }
}
