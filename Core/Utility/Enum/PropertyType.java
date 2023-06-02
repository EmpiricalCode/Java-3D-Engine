package Core.Utility.Enum;

public enum PropertyType {

    // Regular properties
    POSITION("Position"),
    OBJECT_TYPE("Object"),
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
