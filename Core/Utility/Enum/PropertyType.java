///////////////////////
//
// Filename: PropertyType.java
// Author: Daniel Long
// Course: ICS4U1
// Description: An enum listing the various property types.
//
///////////////////////

package Core.Utility.Enum;

public enum PropertyType {

    // Regular properties
    POSITION("Position"),
    RADIUS("Radius"),
    COLOR("Color"), 

    // Material properties
    REFLECTION_TYPE("Reflection Type"),
    FUZZINESS("Fuzziness");

    private String name;

    PropertyType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
