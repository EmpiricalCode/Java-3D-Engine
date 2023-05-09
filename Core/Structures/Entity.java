package Core.Structures;

import Core.Utility.Ray;
import Core.Utility.Vector3D;

public class Entity {

    private Vector3D position;

    // Main constructor
    public Entity(Vector3D position) {
        this.position = position;
    }

    // Returns the position of the object
    public Vector3D getPosition() {
        return this.position;
    }

    // Returns the hit of the object from a raycast
    public Vector3D getHit (Ray ray) {
        return null;
    }
}
