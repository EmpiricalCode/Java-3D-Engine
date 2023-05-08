package Core;

import java.util.ArrayList;

import Core.Structures.Entity;

public class Environment {
    
    private ArrayList<Entity> entities;

    public Environment() {
        
    }

    // Adds a world object
    public void addObject(Entity object) {
        entities.add(object);
    }
}
