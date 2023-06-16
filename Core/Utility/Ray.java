///////////////////////
//
// Filename: Ray.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the various properties and behaviors of rays shot into an environment (for calculation use).
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
    
    // Creates a new ray
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
        ColorRGB outgoingColor;

        // If depth is 0, finish tracing
        if (depth > 0) {
            
            // Getting all entities and their respectives hits with this ray
            for (Entity entity : this.environment.getEntities()) {

                hit = entity.getHit(this);

                // Saving min distance, nearest hit, and nearest hit entity if its hit location is currently closest
                if (hit != null && (hit.getPosition().getDistance(this.origin) < minDistance || minDistance < 0)) {
                    minDistance = hit.getPosition().getDistance(this.origin);
                    nearestHit = hit;
                    nearestHitEntity = entity;
                }
            }

            // Checking if a hit is made
            if (minDistance >= 0) {
                
                returnColor = new ColorRGB(0, 0, 0);
              
                // Getting the reflection direction based on refelction type
                if (nearestHitEntity.getReflectiontype() == ReflectionType.DIFFUSE) {
                    reflectionDirection = Ray.getDiffuseReflection(nearestHit);
                } else {
                    reflectionDirection = Ray.getSpecularReflection(nearestHit, nearestHitEntity);
                }
                
                // Calculating the return color as an accumulation of recursive getColor function calls
                outgoingColor = (new Ray(nearestHit.getPosition(), reflectionDirection, environment)).getColor(depth-1);
                returnColor = ColorRGB.add(returnColor, ColorRGB.multiply(new ColorRGB(outgoingColor.getR() * nearestHitEntity.getColor().getR() / 255, outgoingColor.getG() * nearestHitEntity.getColor().getG() / 255, outgoingColor.getB() * nearestHitEntity.getColor().getB() / 255), 0.5));
                
                return returnColor;

            // If a ray is shot off into the environment, return an ambient color
            } else {
                return Environment.getAmbientColor(this.direction);
            }

        } else {
            return new ColorRGB(0, 0, 0);
        }
    }

    // Returns a diffuse reflection direction 
    // (random reflection)
    public static Vector3D getDiffuseReflection(RayHit hit) {
        
        // Origin of diffuse reflection
        Vector3D diffuseOrigin = Vector3D.add(hit.getPosition(), hit.getNormal());
        // Random unit sphere
        Vector3D diffuseOffset = new Vector3D(Math.random()*2-1, Math.random()*2-1, Math.random()*2-1);

        // Adding the random unit sphere direction to the reflection normal to get a random reflected direction
        Vector3D diffuseReflectionDirection = Vector3D.subtract(Vector3D.add(diffuseOrigin, diffuseOffset), hit.getPosition());
        diffuseReflectionDirection.clamp(1);

        return diffuseReflectionDirection;
    }

    // Returns a specular reflection direction
    // (proper reflection)
    public static Vector3D getSpecularReflection(RayHit hit, Entity entity) {

        // Slightly randomizing reflection based on fuzziness
        Vector3D offsetVector = new Vector3D(Math.random() * entity.getFuzziness() / 4, Math.random() * entity.getFuzziness() / 4, Math.random() * entity.getFuzziness() / 4);
        
        // Calculates the reflection vector based on incident direction
        return Vector3D.add(Vector3D.subtract(hit.getIncidentDirection(), Vector3D.multiply(hit.getNormal(), 2 * hit.getIncidentDirection().dot(hit.getNormal()))), offsetVector);
    }
}
