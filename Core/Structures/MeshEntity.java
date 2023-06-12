package Core.Structures;

import Core.Entities.SubEntities.Triangle;
import Core.Utility.ColorRGB;
import Core.Utility.Vector3D;
import Core.Utility.Enum.EntityType;
import Core.Utility.Enum.ReflectionType;

public abstract class MeshEntity extends Entity {

    private Triangle[] mesh;
    
    public MeshEntity(EntityType entityType, Vector3D position, ColorRGB color, double fuzziness, ReflectionType reflectionType, double width, double depth, double height) {
        super(entityType, position, color, fuzziness, reflectionType, width, depth, height);

        this.setupMesh();
    }

    // Gets the mesh
    public Triangle[] getMesh() {
        return this.mesh;
    }

    // Sets the mesh
    public void setMesh(Triangle[] mesh) {
        this.mesh = mesh;
    }

    // Sets up the mesh for the entity
    public abstract void setupMesh();
    
    // The meshed entity must update its mesh when these properties are changed
    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        this.setupMesh();
    }

    @Override
    public void setDepth(double depth) {
        super.setDepth(depth);
        this.setupMesh();
    }

    @Override
    public void setHeight(double width) {
        super.setHeight(width);
        this.setupMesh();
    }

    @Override
    public void setPosition(Vector3D position) {
        super.setPosition(position);
        this.setupMesh();
    }
}
