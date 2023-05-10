package Core;

import java.awt.Color;
import java.util.ArrayList;

import Core.Structures.Entity;
import Core.Utility.Camera;
import Core.Utility.Vector3D;

public class Environment {
    
    // TODO: make a function that accumulates all RENDERABLE entities (ex. rectangular prism is really 12 triangles)
    private ArrayList<Entity> entities;

    private Camera camera;

    // Main constructor
    public Environment(Camera camera) {
        this.camera = camera;
        this.entities = new ArrayList<Entity>();
    }

    // Getter method for the camera
    public Camera getCamera() {
        return this.camera;
    }

    // Gets the environment's entities
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    // Adds a world object
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    // Returns the ambient color of the environment given a 
    // unit vector away from the origin
    public static Color getAmbientColor(Vector3D direction) {
        return Color.blue;
    }
}
