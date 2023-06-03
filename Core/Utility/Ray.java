///////////////////////
//
// Filename: Ray.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the properties of rays shot in an environment (for calculation use).
//
///////////////////////

package Core.Utility;

import Core.Environment;
import Core.Structures.Entity;
import Core.Utility.Enum.*;

public class Ray {

    private Vector3D origin;
    private Vector3D direction;

    private Environment environment;
    
    // Main constructor
    public Ray(Vector3D origin, Vector3D direction, Environment environment) {
        this.origin = origin;
        this.direction = direction;
        this.environment = environment;

        // Clamping direction to a unit vector
        direction.clamp(1);
    }

    // Getter method for origin
    public Vector3D getOrigin() {
        return this.origin;
    }

    // Getter method for direction
    public Vector3D getDirection() {
        return this.direction;
    }

    // Returns the color accumulated by "tracing" this ray through the environment
    public ColorRGB getColor(int depth) {

        double minDistance = -1;
        RayHit hit;
        RayHit nearestHit = null;
        Entity nearestHitEntity = null;
        Vector3D reflectionDirection = null;

        ColorRGB returnColor;
        ColorRGB incidentColor;

        if (depth > 0) {
            
            for (Entity entity : this.environment.getEntities()) {

                hit = entity.getHit(this);

                if (hit != null && (hit.getPosition().getDistance(this.origin) < minDistance || minDistance < 0)) {
                    minDistance = hit.getPosition().getDistance(this.origin);
                    nearestHit = hit;
                    nearestHitEntity = entity;
                }
            }

            if (minDistance >= 0) {
                
                returnColor = new ColorRGB(0, 0, 0);
              
                if (nearestHitEntity.getReflectiontype() == ReflectionType.DIFFUSE) {
                    reflectionDirection = Ray.getDiffuseReflection(nearestHit);
                } else {
                    reflectionDirection = Ray.getSpecularReflection(nearestHit);
                }
                
                incidentColor = (new Ray(nearestHit.getPosition(), reflectionDirection, environment)).getColor(depth-1);
                returnColor = ColorRGB.add(returnColor, ColorRGB.multiply(new ColorRGB(incidentColor.getR() * nearestHitEntity.getColor().getR() / 255, incidentColor.getG() * nearestHitEntity.getColor().getG() / 255, incidentColor.getB() * nearestHitEntity.getColor().getB() / 255), 0.5));

                
                return returnColor;
            } else {
                return Environment.getAmbientColor(this.direction);
            }

        } else {
            return new ColorRGB(0, 0, 0);
        }
    }

    public static Vector3D getDiffuseReflection(RayHit hit) {
        Vector3D diffuseOrigin = Vector3D.add(hit.getPosition(), hit.getNormal());
        Vector3D diffuseOffset = new Vector3D(Math.random()*2-1, Math.random()*2-1, Math.random()*2-1);

        Vector3D diffuseReflectionDirection = Vector3D.subtract(Vector3D.add(diffuseOrigin, diffuseOffset), hit.getPosition());
        diffuseReflectionDirection.clamp(1);

        return diffuseReflectionDirection;
    }

    public static Vector3D getSpecularReflection(RayHit hit) {
        return Vector3D.subtract(hit.getIncidentDirection(), Vector3D.multiply(hit.getNormal(), 2 * hit.getIncidentDirection().dot(hit.getNormal())));
    }
}
