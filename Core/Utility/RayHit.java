///////////////////////
//
// Filename: RayHit.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that stores information about a ray's collision.
//
///////////////////////

package Core.Utility;

public class RayHit {

    private Vector3D position;
    private Vector3D normal;
    private Vector3D incidentDirection;

    // Main constructor
    public RayHit(Vector3D position, Vector3D normal, Vector3D incidentDirection) {
        this.position = position;
        this.normal = normal;
        this.incidentDirection = incidentDirection;
    }

    // Getter method for position
    public Vector3D getPosition() {
        return this.position;
    }

    // Getter method for normal
    public Vector3D getNormal() {
        return this.normal;
    }

    // Getter method for incident direction
    public Vector3D getIncidentDirection() {
        return this.incidentDirection;
    }
}