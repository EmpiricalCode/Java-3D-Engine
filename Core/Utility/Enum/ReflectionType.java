package Core.Utility.Enum;

public enum ReflectionType {

    SPECULAR (0),
    DIFFUSE (1);

    // 0 = diffuse reflection
    // 1 = specular reflection
    private final int reflectionId;

    ReflectionType (int reflectionId) {
        this.reflectionId = reflectionId;
    }
}
