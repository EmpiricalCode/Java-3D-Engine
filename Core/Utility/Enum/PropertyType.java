package Core.Utility.Enum;

public enum PropertyType {

    POSITION("Position"),
    COLOR("Color"), 
    OBJECT_TYPE("Object"),
    REFLECTION_TYPE("Reflection Type");

    private String name;

    PropertyType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
