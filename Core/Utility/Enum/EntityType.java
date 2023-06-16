///////////////////////
//
// Filename: EntityType.java
// Author: Daniel Long
// Course: ICS4U1
// Description: An enum listing the different renderable entities.
//
///////////////////////

package Core.Utility.Enum;

public enum EntityType {
    
    SPHERE("Sphere"),
    RECTANGULAR_PRISM("Rectangular Prism"),
    TRIANGULAR_PRISM("Triangular Prism"),
    TRIANGLE("Triangle");

    private String name;

    // Creates a new EntityType
    EntityType(String name) {
        this.name = name;
    }

    // Returns the entity's name
    public String getName() {
        return this.name;
    }
}
