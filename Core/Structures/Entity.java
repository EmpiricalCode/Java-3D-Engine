///////////////////////
//
// Filename: Entity.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A superclass that groups together various renderable objects and handles some of their shared properties.
//
///////////////////////

package Core.Structures;

import Core.Utility.*;
import Core.Utility.Enum.EntityType;
import Core.Utility.Enum.PropertyType;
import Core.Utility.Enum.ReflectionType;

public abstract class Entity {

    private Vector3D position;
    private ColorRGB color;
    private ReflectionType reflectionType;
    private double fuzziness;
    private EntityType entityType;

    // Main constructor
    public Entity(Vector3D position, ColorRGB color, ReflectionType reflectionType, double fuzziness, EntityType entityType) {

        this.position = position;
        this.fuzziness = fuzziness;
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

    // Returns the fuzziness of the object
    public double getFuzziness() {
        return this.fuzziness;
    }

    // Returns the reflection type of the object
    public ReflectionType getReflectiontype() {
        return this.reflectionType;
    }

    // Sets the position of an object
    public void setPosition(Vector3D position) {
        this.position = position;
    }
    
    // Sets the color of the object
    public void setColor(ColorRGB color) {
        this.color = color;
    }

    // Sets the fuzziness of the object
    public void setFuzziness(double fuzziness) {
        this.fuzziness = fuzziness;
    }

    // Sets the reflection type of the object
    public void setReflectiontype(ReflectionType reflectionType) {
        this.reflectionType = reflectionType;
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
