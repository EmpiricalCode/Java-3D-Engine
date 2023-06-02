package Core.Entities;

import Core.Structures.Entity;
import Core.Utility.*;
import Core.Utility.Enum.EntityType;
import Core.Utility.Enum.PropertyType;
import Core.Utility.Enum.ReflectionType;

public class Sphere extends Entity {
    
    public static final PropertyType[] PROPERTIES = {PropertyType.OBJECT_TYPE, PropertyType.POSITION, PropertyType.COLOR};
    public static final PropertyType[] MATERIAL_PROPERTIES = {PropertyType.REFLECTION_TYPE};
    private double radius;

    // Main constructor
    public Sphere(Vector3D position, ColorRGB color, ReflectionType reflectionType, double radius) {

        super(position, color, reflectionType, EntityType.SPHERE);
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
                    return new RayHit(hitPosition, this.getNormal(ray, hitPosition), ray.getDirection());
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

    // Returns the properties of a sphere
    public PropertyType[] getProperties() {
        return Sphere.PROPERTIES;
    }

    // Returns the material proeprties of a sphere
    public PropertyType[] getMaterialProperties() {
        return Sphere.MATERIAL_PROPERTIES;
    }
}
