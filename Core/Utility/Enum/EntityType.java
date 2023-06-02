package Core.Utility.Enum;

public enum EntityType {
    SPHERE("Sphere"),
    RECTANGULAR_PRISM("Rectangular Prism");

    private String name;

    EntityType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
