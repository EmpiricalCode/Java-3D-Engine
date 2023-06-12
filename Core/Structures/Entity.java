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

    public static int MIN_COLOR_VALUE = 40;

    private Vector3D position;
    private ColorRGB color;
    private ReflectionType reflectionType;
    private double fuzziness;
    private EntityType entityType;
    private double width;
    private double depth;
    private double height;

    // Main constructor
    public Entity(EntityType entityType, Vector3D position, ColorRGB color, double fuzziness, ReflectionType reflectionType, double width, double depth, double height) {

        this.position = position;
        this.fuzziness = fuzziness;
        this.entityType = entityType;

        // Although not all entities use the width, depth, and height properties, 
        // it would be better to have them shared between all entities 
        // so that these properties can be accessed directly from an Entity object without needing to downcast
        this.width = width;
        this.depth = depth;
        this.height = height;

        // This is necessary because the lighting model has an edge case issue with objects that strictly reflect one channel of color
        this.color = new ColorRGB(Math.max(color.getR(), Entity.MIN_COLOR_VALUE), Math.max(color.getG(), Entity.MIN_COLOR_VALUE), Math.max(color.getB(), Entity.MIN_COLOR_VALUE));
        this.reflectionType = reflectionType;
    }

    // Returns the width of the entity
    public double getWidth() {
        return this.width;
    }

    // Returns the height of the entity
    public double getHeight() {
        return this.height;
    }

    // Returns the depth of the entity
    public double getDepth() {
        return this.depth;
    }

    // Sets the width of the entity
    public void setWidth(double width) {
        this.width = width;
    }

    // Sets the height of the entity
    public void setHeight(double height) {
        this.height = height;
    }

    // Sets the depth of the entity
    public void setDepth(double depth) {
        this.depth = depth;
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

        // This is necessary because the lighting model has an edge case issue with objects that strictly reflect one channel of color
        this.color = new ColorRGB(Math.max(color.getR(), Entity.MIN_COLOR_VALUE), Math.max(color.getG(), Entity.MIN_COLOR_VALUE), Math.max(color.getB(), Entity.MIN_COLOR_VALUE));;
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

    // Returns an entity's properties
    abstract public PropertyType[] getProperties();

    // Returns an entity's material properties
    abstract public PropertyType[] getMaterialProperties();

    // Returns a normal vector of the object given position
    abstract protected Vector3D getNormal(Ray ray, Vector3D hitPosition);
}
