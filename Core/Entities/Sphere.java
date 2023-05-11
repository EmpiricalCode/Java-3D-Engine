package Core.Entities;

import java.awt.Color;

import Core.Structures.Entity;
import Core.Utility.*;

public class Sphere extends Entity {
    
    private double radius;

    // Main constructor
    public Sphere(Vector3D position, ColorRGB color, double radius) {

        super(position, color);

        this.radius = radius;
    }

    // Returns the radius of the sphere
    public double getRadius() {
        return this.radius;
    }

    // Returns the hit of the sphere from a raycast
    public RayHit getHit (Ray ray) {
        
        Vector3D hitPosition;
        double hitDistance;
        double hitDistancePrime;
        double perpendicularDistance;

        Vector3D distanceVector = Vector3D.subtract(this.getPosition(), ray.getOrigin());
        double dotProduct = distanceVector.dot(ray.getDirection());

        if (dotProduct >= 0) {

            perpendicularDistance = Math.sqrt(Math.pow(distanceVector.getMagnitude(), 2) - Math.pow(dotProduct, 2));

            if (perpendicularDistance <= this.radius) {

                hitDistancePrime = Math.sqrt(Math.pow(this.radius, 2) - Math.pow(perpendicularDistance, 2));
                hitDistance = Math.sqrt(Math.pow(distanceVector.getMagnitude(), 2) - Math.pow(perpendicularDistance, 2)) - hitDistancePrime;
                hitPosition = Vector3D.add(ray.getOrigin(), Vector3D.multiply(ray.getDirection(), hitDistance));

                if (Vector3D.subtract(hitPosition, ray.getOrigin()).getMagnitude() >= 0.01) {
                    return new RayHit(hitPosition, this.getNormal(ray, hitPosition));
                } 
            }
            
            return null;
            
        } else {
            return null;
        }
    }

    // Returns a normal vector of the sphere given a position
    public Vector3D getNormal(Ray ray, Vector3D hitPosition) {

        Vector3D normal = Vector3D.subtract(hitPosition, this.getPosition());
        normal.clamp(1);

        return normal;
    }
}
