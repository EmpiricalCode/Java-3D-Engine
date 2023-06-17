///////////////////////
//
// Filename: RectangularPrism.java
// Author: Daniel Long
// Course: ICS4U1
// Description: Handles behaviors and properties for the rectangular prism entity, subclass to the MeshEntity class.
//
///////////////////////

package Core.Entities;

import Core.Entities.SubEntities.Triangle;
import Core.Structures.MeshEntity;
import Core.Utility.*;
import Core.Utility.Enum.EntityType;
import Core.Utility.Enum.PropertyType;
import Core.Utility.Enum.ReflectionType;

public class RectangularPrism extends MeshEntity {
    
    public static final PropertyType[] PROPERTIES = {PropertyType.POSITION, PropertyType.WIDTH, PropertyType.DEPTH, PropertyType.HEIGHT};
    public static final PropertyType[] MATERIAL_PROPERTIES = {PropertyType.REFLECTION_TYPE, PropertyType.COLOR, PropertyType.FUZZINESS};

    // Creates a Rectangular Prism entity
    public RectangularPrism(Vector3D position, ColorRGB color, double fuzziness, ReflectionType reflectionType, double width, double depth, double height) {
        super(EntityType.RECTANGULAR_PRISM, position, color, fuzziness, reflectionType, width, depth, height);
    }

    // Sets up mesh based on position, width, depth, and height
    public void setupMesh() {

        Vector3D position = this.getPosition();

        double width = this.getWidth();
        double depth = this.getDepth();
        double height = this.getHeight();

        ColorRGB color = this.getColor();
        double fuzziness = this.getFuzziness();
        ReflectionType reflectionType = this.getReflectiontype();
        
        Triangle[] mesh;
        Vector3D[] points = new Vector3D[8];

        // Setting up points
        points[0] = new Vector3D(position.getX() - width / 2, position.getY() - depth / 2, position.getZ() - height / 2);
        points[1] = new Vector3D(position.getX() + width / 2, position.getY() - depth / 2, position.getZ() - height / 2);
        points[2] = new Vector3D(position.getX() - width / 2, position.getY() + depth / 2, position.getZ() - height / 2);
        points[3] = new Vector3D(position.getX() + width / 2, position.getY() + depth / 2, position.getZ() - height / 2);
        points[4] = new Vector3D(position.getX() - width / 2, position.getY() - depth / 2, position.getZ() + height / 2);
        points[5] = new Vector3D(position.getX() + width / 2, position.getY() - depth / 2, position.getZ() + height / 2);
        points[6] = new Vector3D(position.getX() - width / 2, position.getY() + depth / 2, position.getZ() + height / 2);
        points[7] = new Vector3D(position.getX() + width / 2, position.getY() + depth / 2, position.getZ() + height / 2);

        // Setting up triangle mesh
        mesh = new Triangle[12];
        mesh[0] = new Triangle(points[0], points[1], points[4], color, fuzziness, reflectionType);
        mesh[1] = new Triangle(points[4], points[1], points[5], color, fuzziness, reflectionType);
        mesh[2] = new Triangle(points[1], points[5], points[7], color, fuzziness, reflectionType);
        mesh[3] = new Triangle(points[3], points[1], points[7], color, fuzziness, reflectionType);
        mesh[4] = new Triangle(points[3], points[6], points[7], color, fuzziness, reflectionType);
        mesh[5] = new Triangle(points[3], points[2], points[6], color, fuzziness, reflectionType);
        mesh[6] = new Triangle(points[2], points[0], points[6], color, fuzziness, reflectionType);
        mesh[7] = new Triangle(points[0], points[4], points[6], color, fuzziness, reflectionType);
        mesh[8] = new Triangle(points[0], points[1], points[2], color, fuzziness, reflectionType);
        mesh[9] = new Triangle(points[2], points[1], points[3], color, fuzziness, reflectionType);
        mesh[10] = new Triangle(points[5], points[6], points[4], color, fuzziness, reflectionType);
        mesh[11] = new Triangle(points[6], points[5], points[7], color, fuzziness, reflectionType);

        this.setMesh(mesh);
    }

    // Returns the hit of the rectangular prism from a raycast
    public RayHit getHit (Ray ray) {

        double dist = 0;
        double minDist = -1;
        RayHit hit;
        RayHit nearestHit = null;

        // Iterating through each triangle and getting their respective hit points
        // Whichever triangle has the closest hitpoint will be used
        for (Triangle triangle : this.getMesh()) {

            hit = triangle.getHit(ray);

            if (hit != null ) {

                dist = ray.getOrigin().getDistance(hit.getPosition());

                // If the distance is below minDist or minDist has not been set, update the nearest hit
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
        return RectangularPrism.PROPERTIES;
    }

    // Returns the material proeprties of a rectangular prisms
    public PropertyType[] getMaterialProperties() {
        return RectangularPrism.MATERIAL_PROPERTIES;
    }

    // The getNormal method is really only internally, and since the rectangular prism itself doesn't handle
    // ray collisions and relies on the triangles it is comprised of, it does not need to return a normal
    protected Vector3D getNormal(Ray ray, Vector3D hitPosition) {

        return null;
    }
}