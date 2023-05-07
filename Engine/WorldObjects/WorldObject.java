package Engine.WorldObjects;

import Engine.Utility.Vector3D;

public class WorldObject {

    private Vector3D position;

    public WorldObject(Vector3D pos) {
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
