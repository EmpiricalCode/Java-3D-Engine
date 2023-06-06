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
    DIMENSIONS("Dimensions"),
    COLOR("Color"), 

    // Material properties
    REFLECTION_TYPE("Reflection Type"),
    FUZZINESS("Fuzziness");

    private String name;

    // Creates a new PropertyType
    PropertyType(String name) {
        this.name = name;
    }

    // Returns the name of the property
    public String getName() {
        return this.name;
    }
}
