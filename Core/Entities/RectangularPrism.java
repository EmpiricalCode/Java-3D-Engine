///////////////////////
//
// Filename: RectangularPrism.java
// Author: Daniel Long
// Course: ICS4U1
// Description: Handles behaviors and properties for the sphere entity, subclass to the Entity class.
//
///////////////////////

package Core.Entities;

import Core.Entities.SubEntities.Triangle;
import Core.Structures.Entity;
import Core.Utility.*;
import Core.Utility.Enum.EntityType;
import Core.Utility.Enum.PropertyType;
import Core.Utility.Enum.ReflectionType;

public class RectangularPrism extends Entity {
    
    public static final PropertyType[] PROPERTIES = {PropertyType.POSITION, PropertyType.DIMENSIONS};
    public static final PropertyType[] MATERIAL_PROPERTIES = {PropertyType.REFLECTION_TYPE, PropertyType.COLOR, PropertyType.FUZZINESS};

    private Vector3D[] points;
    private Triangle[] mesh;

    // TODO: Make all constructor comments more detailed
    // Main constructor
    public RectangularPrism(Vector3D position, ColorRGB color, double fuzziness, ReflectionType reflectionType, double width, double depth, double height) {

        super(position, color, reflectionType, fuzziness, EntityType.RECTANGULAR_PRISM, width, depth, height);

        this.points = new Vector3D[8];
        this.points[0] = new Vector3D(position.getX() - width / 2, position.getY() - depth / 2, position.getZ() - height / 2);
        this.points[1] = new Vector3D(position.getX() + width / 2, position.getY() - depth / 2, position.getZ() - height / 2);
        this.points[2] = new Vector3D(position.getX() - width / 2, position.getY() + depth / 2, position.getZ() - height / 2);
        this.points[3] = new Vector3D(position.getX() + width / 2, position.getY() + depth / 2, position.getZ() - height / 2);
        this.points[4] = new Vector3D(position.getX() - width / 2, position.getY() - depth / 2, position.getZ() + height / 2);
        this.points[5] = new Vector3D(position.getX() + width / 2, position.getY() - depth / 2, position.getZ() + height / 2);
        this.points[6] = new Vector3D(position.getX() - width / 2, position.getY() + depth / 2, position.getZ() + height / 2);
        this.points[7] = new Vector3D(position.getX() + width / 2, position.getY() + depth / 2, position.getZ() + height / 2);

        // Setting up triangle mesh
        this.mesh = new Triangle[12];
        this.mesh[0] = new Triangle(this.points[0], this.points[1], this.points[4], color, fuzziness, reflectionType);
        this.mesh[1] = new Triangle(this.points[4], this.points[1], this.points[5], color, fuzziness, reflectionType);
        this.mesh[2] = new Triangle(this.points[1], this.points[5], this.points[7], color, fuzziness, reflectionType);
        this.mesh[3] = new Triangle(this.points[3], this.points[1], points[7], color, fuzziness, reflectionType);
        this.mesh[4] = new Triangle(this.points[3], this.points[6], points[7], color, fuzziness, reflectionType);
        this.mesh[5] = new Triangle(this.points[3], this.points[2], points[6], color, fuzziness, reflectionType);
        this.mesh[6] = new Triangle(this.points[2], this.points[0], points[6], color, fuzziness, reflectionType);
        this.mesh[7] = new Triangle(this.points[0], this.points[4], points[6], color, fuzziness, reflectionType);
        this.mesh[8] = new Triangle(this.points[0], this.points[1], points[2], color, fuzziness, reflectionType);
        this.mesh[9] = new Triangle(this.points[2], this.points[1], points[3], color, fuzziness, reflectionType);
        this.mesh[10] = new Triangle(this.points[5], this.points[6], points[4], color, fuzziness, reflectionType);
        this.mesh[11] = new Triangle(this.points[6], this.points[5], points[7], color, fuzziness, reflectionType);
    }

    // Returns the hit of the rectangular prism from a raycast
    public RayHit getHit (Ray ray) {

        double dist = 0;
        double minDist = -1;
        RayHit hit;
        RayHit nearestHit = null;

        for (Triangle triangle : this.mesh) {

            if (triangle != null) {
                hit = triangle.getHit(ray);

                if (hit != null ) {

                    dist = ray.getOrigin().getDistance(hit.getPosition());

                    if (dist < minDist || minDist < 0) {
                        minDist = dist;
                        nearestHit = hit;
                    }
                }
            }
        }

        // if (nearestHit != null) {
        // System.out.println(nearestHit.getNormal());

        // }    

        return nearestHit;
    }

    // Returns the properties of a rectangular prismhere
    public PropertyType[] getProperties() {
        return RectangularPrism.PROPERTIES;
    }

    // Returns the material proeprties of a rectangular prisms
    public PropertyType[] getMaterialProperties() {
        return RectangularPrism.MATERIAL_PROPERTIES;
    }

    // The getNormal method is really only internally, and since the rectangualr prism itself doesn't handle
    // ray collisions and relies on the triangles it is comprised of, it does not need to return a normal
    protected Vector3D getNormal(Ray ray, Vector3D hitPosition) {

        return null;
    }
}