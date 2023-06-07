///////////////////////
//
// Filename: Triangle.java
// Author: Daniel Long
// Course: ICS4U1
// Description: Handles behaviors and properties for the triangle (sub)entity, subclass to the Entity class.
//
///////////////////////

package Core.Entities.SubEntities;
//TODO: Make imports * wherever possible
import Core.Structures.Entity;
import Core.Utility.*;
import Core.Utility.Enum.*;

public class Triangle extends Entity {

    private Vector3D point1;
    private Vector3D point2;
    private Vector3D point3;
    
    // Creates a new Triangle entity
    public Triangle(Vector3D point1, Vector3D point2, Vector3D point3, ColorRGB color, double fuzziness, ReflectionType reflectionType) {
        super(point1, color, reflectionType, fuzziness, EntityType.TRIANGLE);

        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    // Returns the hit of the triangle from a raycast
    public RayHit getHit(Ray ray) {
        
        RayHit hit;
        Vector3D normal = this.getNormal(ray, ray.getDirection());
        Vector3D hitPosition;
        
        Vector3D edge1 = Vector3D.subtract(point2, point1);
        Vector3D edge2 = Vector3D.subtract(point3, point2);
        Vector3D edge3 = Vector3D.subtract(point1, point3);

        Vector3D c1;
        Vector3D c2;
        Vector3D c3;

        double dot = -point1.dot(normal);
        double length = -(normal.dot(ray.getOrigin()) + dot) / normal.dot(ray.getDirection());

        if (length > 0 && normal.dot(ray.getDirection()) != 0) {
            hit = new RayHit(Vector3D.add(ray.getOrigin(), Vector3D.multiply(ray.getDirection(), length)), normal, ray.getDirection());
            hitPosition = hit.getPosition(); 

            c1 = Vector3D.subtract(hitPosition, point1);
            c2 = Vector3D.subtract(hitPosition, point2);
            c3 = Vector3D.subtract(hitPosition, point3);

            if ((normal.dot(Vector3D.cross(edge1, c1)) >= 0 && normal.dot(Vector3D.cross(edge2, c2)) >= 0 && normal.dot(Vector3D.cross(edge3, c3)) >= 0) || (normal.dot(Vector3D.cross(edge1, c1)) <= 0 && normal.dot(Vector3D.cross(edge2, c2)) <= 0 && normal.dot(Vector3D.cross(edge3, c3)) <= 0)) {

                if (Vector3D.subtract(hitPosition, ray.getOrigin()).getMagnitude() >= 0.01) {
                    return hit;
                }
            } 
        } 

        return null;
    }

    // This is a sub entity, so null is returned for these methods since their pertain to properties modifiable in the main window user interface
    public PropertyType[] getMaterialProperties() {
        return null;
    }

    public PropertyType[] getProperties() {
        return null;
    }

    // Gets the normal of a raycast and hit position
    protected Vector3D getNormal(Ray ray, Vector3D hitPosition) {

        Vector3D directionA = Vector3D.subtract(point2, point1);
        Vector3D directionB = Vector3D.subtract(point3, point1);
        Vector3D normal = Vector3D.cross(directionA, directionB);

        normal.clamp(1);

        if (ray.getOrigin().getDistance(Vector3D.add(hitPosition, normal)) > ray.getOrigin().getDistance(Vector3D.add(hitPosition, Vector3D.multiply(normal, -1)))) {
            normal.multiply(-1);
        }

        return normal;
    }
}
