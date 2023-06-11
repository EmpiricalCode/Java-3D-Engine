///////////////////////
//
// Filename: TriangularPrism.java
// Author: Daniel Long
// Course: ICS4U1
// Description: Handles behaviors and properties for the triangular prism entity, subclass to the Entity class.
//
///////////////////////

package Core.Entities;

import Core.Entities.SubEntities.Triangle;
import Core.Structures.Entity;
import Core.Utility.*;
import Core.Utility.Enum.EntityType;
import Core.Utility.Enum.PropertyType;
import Core.Utility.Enum.ReflectionType;

public class TriangularPrism extends Entity {
    
    public static final PropertyType[] PROPERTIES = {PropertyType.POSITION, PropertyType.WIDTH, PropertyType.DEPTH, PropertyType.HEIGHT};
    public static final PropertyType[] MATERIAL_PROPERTIES = {PropertyType.REFLECTION_TYPE, PropertyType.COLOR, PropertyType.FUZZINESS};

    private Vector3D[] points;
    private Triangle[] mesh;

    // TODO: Make all constructor comments more detailed
    // Main constructor
    public TriangularPrism(Vector3D position, ColorRGB color, double fuzziness, ReflectionType reflectionType, double width, double depth, double height) {

        super(position, color, reflectionType, fuzziness, EntityType.TRIANGULAR_PRISM, width, depth, height);

        this.points = new Vector3D[6];
        this.points[0] = new Vector3D(position.getX() - width / 2, position.getY() - depth / 2, position.getZ() - height / 2);
        this.points[1] = new Vector3D(position.getX() + width / 2, position.getY() - depth / 2, position.getZ() - height / 2);
        this.points[2] = new Vector3D(position.getX() - width / 2, position.getY() + depth / 2, position.getZ() - height / 2);
        this.points[3] = new Vector3D(position.getX() + width / 2, position.getY() + depth / 2, position.getZ() - height / 2);
        this.points[4] = new Vector3D(position.getX(), position.getY() - depth / 2, position.getZ() + height / 2);
        this.points[5] = new Vector3D(position.getX(), position.getY() + depth / 2, position.getZ() + height / 2);

        // Setting up triangle mesh
        this.mesh = new Triangle[8];
        this.mesh[0] = new Triangle(points[1], points[0], points[2], color, fuzziness, reflectionType);
        this.mesh[1] = new Triangle(points[1], points[2], points[3], color, fuzziness, reflectionType);
        this.mesh[2] = new Triangle(points[0], points[2], points[4], color, fuzziness, reflectionType);
        this.mesh[3] = new Triangle(points[2], points[4], points[5], color, fuzziness, reflectionType);
        this.mesh[4] = new Triangle(points[1], points[3], points[4], color, fuzziness, reflectionType);
        this.mesh[5] = new Triangle(points[4], points[3], points[5], color, fuzziness, reflectionType);
        this.mesh[6] = new Triangle(points[0], points[1], points[4], color, fuzziness, reflectionType);
        this.mesh[7] = new Triangle(points[3], points[2], points[5], color, fuzziness, reflectionType);
    }

    // Returns the hit of the rectangular prism from a raycast
    public RayHit getHit (Ray ray) {

        double dist = 0;
        double minDist = -1;
        RayHit hit;
        RayHit nearestHit = null;

        for (Triangle triangle : this.mesh) {

            hit = triangle.getHit(ray);

            if (hit != null ) {

                dist = ray.getOrigin().getDistance(hit.getPosition());

                if (dist < minDist || minDist < 0) {
                    minDist = dist;
                    nearestHit = hit;
                }
            }
        }

        return nearestHit;
    }

    // Returns the properties of a rectangular prismhere
    public PropertyType[] getProperties() {
        return TriangularPrism.PROPERTIES;
    }

    // Returns the material proeprties of a rectangular prisms
    public PropertyType[] getMaterialProperties() {
        return TriangularPrism.MATERIAL_PROPERTIES;
    }

    // The getNormal method is really only internally, and since the rectangualr prism itself doesn't handle
    // ray collisions and relies on the triangles it is comprised of, it does not need to return a normal
    protected Vector3D getNormal(Ray ray, Vector3D hitPosition) {

        return null;
    }
}