package Core.Structures;

import Core.Utility.Vector3D;

public class Entity {

    private Vector3D position;

    // Main constructor
    public Entity(Vector3D pos) {
        this.position = pos;
    }

    // Returns the position of the object
    public Vector3D getPosition() {
        return this.position;
    }

    // Returns the hit of the object from a raycast
    // TODO: Placeholder, also put a raycast object in the arguments
    public Vector3D getHit() {
        return null;
    }
}
