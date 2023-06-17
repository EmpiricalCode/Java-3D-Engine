///////////////////////
//
// Filename: Sphere.java
// Author: Daniel Long
// Course: ICS4U1
// Description: Handles behaviors and properties for the sphere entity, subclass to the Entity class.
//
///////////////////////

package Core.Entities;

import Core.Structures.Entity;
import Core.Utility.*;
import Core.Utility.Enum.EntityType;
import Core.Utility.Enum.PropertyType;
import Core.Utility.Enum.ReflectionType;

public class Sphere extends Entity {
    
    public static final PropertyType[] PROPERTIES = {PropertyType.POSITION, PropertyType.RADIUS};
    public static final PropertyType[] MATERIAL_PROPERTIES = {PropertyType.REFLECTION_TYPE, PropertyType.COLOR, PropertyType.FUZZINESS};
    
    private double radius;

    // Creates a Sphere entity
    public Sphere(Vector3D position, ColorRGB color, double fuzziness, ReflectionType reflectionType, double radius) {

        // Sphere does not actually use the width, depth, height properties internally
        super(EntityType.SPHERE, position, color, fuzziness, reflectionType, radius * 2, radius * 2, radius * 2);
        this.radius = radius;
    }

    // Returns the radius of the sphere
    public double getRadius() {
        return this.radius;
    }

    // Sets the radius of the sphere
    public void setRadius(double radius) {
        this.radius = radius;
    }

    // Returns the hit of the sphere from a raycast
    public RayHit getHit (Ray ray) {
        
        Vector3D hitPosition;
        double hitDistance;
        double hitDistancePrime;
        double perpendicularDistance;

        Vector3D distanceVector = Vector3D.subtract(this.getPosition(), ray.getOrigin());
        double dotProduct = distanceVector.dot(ray.getDirection());

        // Making sure the ray is pointing towards the sphere, and not in the opposite direction (which would still register a hit, since a ray is infinitely long)
        if (dotProduct >= 0) {

            perpendicularDistance = Math.sqrt(Math.pow(distanceVector.getMagnitude(), 2) - Math.pow(dotProduct, 2));

            // Checking if the ray actually hit the sphere by checking the ray's perpendicular distance to the center of the sphere
            if (perpendicularDistance <= this.radius) {

                // Calculating the position where the ray intersects with the sphere
                hitDistancePrime = Math.sqrt(Math.pow(this.radius, 2) - Math.pow(perpendicularDistance, 2));
                hitDistance = Math.sqrt(Math.pow(distanceVector.getMagnitude(), 2) - Math.pow(perpendicularDistance, 2)) - hitDistancePrime;
                hitPosition = Vector3D.add(ray.getOrigin(), Vector3D.multiply(ray.getDirection(), hitDistance));

                // Avoiding "shadow acne" - reflection collides with the entity again over a very small distance due to floating-point errors
                if (Vector3D.subtract(hitPosition, ray.getOrigin()).getMagnitude() >= 0.001) {
                    return new RayHit(hitPosition, this.getNormal(ray, hitPosition), ray.getDirection());
                } 
            }
        } 

        return null;
    }

    // Returns the properties of a sphere
    public PropertyType[] getProperties() {
        return Sphere.PROPERTIES;
    }

    // Returns the material proeprties of a sphere
    public PropertyType[] getMaterialProperties() {
        return Sphere.MATERIAL_PROPERTIES;
    }

    // Returns a normal vector of the sphere given a position
    protected Vector3D getNormal(Ray ray, Vector3D hitPosition) {

        Vector3D normal = Vector3D.subtract(hitPosition, this.getPosition());
        normal.clamp(1);

        return normal;
    }
}
