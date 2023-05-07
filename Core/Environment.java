package Core;

import java.util.ArrayList;

import Core.Structures.Entity;

public class Environment {
    
    private ArrayList<Entity> worldObjects;

    public Environment() {
        
    }

    // Adds a world object
    public void addObject(Entity object) {
        worldObjects.add(object);
    }
}
