package Core;

import java.util.ArrayList;

import Core.Structures.Entity;

public class Environment {
    
    private ArrayList<Entity> entities;

    public Environment() {
        
    }

    // Gets the environment's entities
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    // Adds a world object
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
