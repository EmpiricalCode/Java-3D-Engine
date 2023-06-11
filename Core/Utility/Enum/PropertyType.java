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
    POSITION("Position", PropertySetType.TEXT_FIELD),
    RADIUS("Radius", PropertySetType.TEXT_FIELD),
    WIDTH("Width", PropertySetType.TEXT_FIELD),
    DEPTH("Depth", PropertySetType.TEXT_FIELD),
    HEIGHT("Height", PropertySetType.TEXT_FIELD),

    // Material properties
    REFLECTION_TYPE("Reflection Type", PropertySetType.DROP_DOWN_MENU),
    COLOR("Color", PropertySetType.TEXT_FIELD), 
    FUZZINESS("Fuzziness", PropertySetType.TEXT_FIELD),

    // Render settings properties
    QUALITY("Quality", PropertySetType.TEXT_FIELD),
    PIXEL_SAMPLES("Pixel Samples", PropertySetType.TEXT_FIELD),
    RAY_DEPTH("Ray Depth", PropertySetType.TEXT_FIELD),
    ANTI_ALIASING("Anti Aliasing", PropertySetType.DROP_DOWN_MENU),
    GAMMA("Gamma", PropertySetType.TEXT_FIELD),
    CAMERA_POSITION("Camera Position", PropertySetType.TEXT_FIELD),
    CAMERA_LOOK_AT("Camera Look At", PropertySetType.TEXT_FIELD);


    private String name;
    private PropertySetType setType;

    // Creates a new PropertyType
    PropertyType(String name, PropertySetType setType) {
        this.name = name;
        this.setType = setType;
    }

    // Returns the name of the property
    public String getName() {
        return this.name;
    }

    // Returns the set type of the property
    public PropertySetType getSetType() {
        return this.setType;
    }
}
