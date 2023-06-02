package Core.Utility.Enum;

public enum PropertyType {

    POSITION("Position", PropertyAlterType.TRIPLE_DOUBLE),
    COLOR("Color", PropertyAlterType.TRIPLE_INTEGER), 
    REFLECTION_TYPE("Reflection Type", PropertyAlterType.DROP_DOWN_MENU),
    NAME("Name", PropertyAlterType.TEXT);

    private String name;
    private PropertyAlterType alterType;

    PropertyType(String name, PropertyAlterType alterType) {
        this.name = name;
        this.alterType = alterType;
    }

    public String getName() {
        return this.name;
    }
    
    public PropertyAlterType getAltertype() {
        return this.alterType;
    }
}
