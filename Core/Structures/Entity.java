package Core.Structures;

import Core.Utility.*;
import Core.Utility.Enum.EntityType;
import Core.Utility.Enum.PropertyType;
import Core.Utility.Enum.ReflectionType;

public abstract class Entity {

    private Vector3D position;
    private ColorRGB color;
    private ReflectionType reflectionType;
    private EntityType entityType;

    // Main constructor
    public Entity(Vector3D position, ColorRGB color, ReflectionType reflectionType, EntityType entityType) {

        this.position = position;
        this.entityType = entityType;

        // This is necessary because the lighting model has an edge case issue with objects that strictly reflect one channel of color
        this.color = new ColorRGB(Math.max(color.getR(), 20), Math.max(color.getG(), 20), Math.max(color.getB(), 20));
        this.reflectionType = reflectionType;
    }

    // Returns the entity type of the object
    public EntityType getEntityType() {
        return this.entityType;
    }

    // Returns the position of the object
    public Vector3D getPosition() {
        return this.position;
    }

    // Returns the color of the object
    public ColorRGB getColor() {
        return this.color;
    }

    // Returns the reflection type of the object
    public ReflectionType getReflectiontype() {
        return this.reflectionType;
    }

    // Returns the hit of the object from a raycast
    abstract public RayHit getHit (Ray ray);

    // Returns a normal vector of the object given position
    abstract public Vector3D getNormal(Ray ray, Vector3D hitPosition);

    // Returns an entity's properties
    abstract public PropertyType[] getProperties();

    // Returns an entity's material properties
    abstract public PropertyType[] getMaterialProperties();
}
