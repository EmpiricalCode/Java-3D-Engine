package Core.Utility;

import Core.Environment;
import Core.Structures.Entity;

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
        Vector3D diffuseOrigin;
        Vector3D diffuseOffset;
        Vector3D diffuseReflectionDirection;

        ColorRGB returnColor;

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

                // TODO: Create reflection ray
                diffuseOrigin = Vector3D.add(nearestHit.getPosition(), nearestHit.getNormal());
                diffuseOffset = new Vector3D(Math.random()*2-1, Math.random()*2-1, Math.random()*2-1);

                // System.out.println(diffuseOffset);
                diffuseReflectionDirection = Vector3D.subtract(Vector3D.add(diffuseOrigin, diffuseOffset), nearestHit.getPosition());
                diffuseReflectionDirection.clamp(1);

                returnColor = ColorRGB.add(returnColor, ColorRGB.multiply((new Ray(nearestHit.getPosition(), diffuseReflectionDirection, environment)).getColor(depth-1), 0.5));

                
                return returnColor;
            } else {
                // System.out.println(Environment.getAmbientColor(this.direction));
                return Environment.getAmbientColor(this.direction);
            }

        } else {
            return new ColorRGB(0, 0, 0);
        }
    }
}
