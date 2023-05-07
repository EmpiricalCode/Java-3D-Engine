package Engine;

import java.util.ArrayList;

import Engine.WorldObject.WorldObject;

public class World {
    
    private ArrayList<WorldObject> worldObjects;

    public World() {
        
    }

    // Adds a world object
    public void addObject(WorldObject object) {
        worldObjects.add(object);
    }
}
