package Core.Utility;

import java.awt.Color;

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
    public Color getColor(int depth) {

        RayHit hit;
        double minDistance;
        Color returnColor;
        Entity nearestHitEntity;

        if (depth > 0) {

            minDistance = -1;
            nearestHitEntity = null;
            
            for (Entity entity : this.environment.getEntities()) {

                hit = entity.getHit(this);

                if (hit != null && (hit.getPosition().getDistance(this.origin) < minDistance || minDistance < 0)) {
                    minDistance = hit.getPosition().getDistance(this.origin);
                    nearestHitEntity = entity;
                }
            }

            if (minDistance >= 0) {
                // TODO: Create reflection ray

                returnColor = new Color((int) (nearestHitEntity.getColor().getRed() * 0.5), (int) (nearestHitEntity.getColor().getRed() * 0.5), (int) (nearestHitEntity.getColor().getRed() * 0.5));

                return returnColor;
            } else {
                return Environment.getAmbientColor(this.direction);
            }

        } else {
            return new Color(0, 0, 0);
        }
    }
}
