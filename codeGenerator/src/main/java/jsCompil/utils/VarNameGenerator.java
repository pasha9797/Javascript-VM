package jsCompil.utils;

public class VarNameGenerator {
    public static String generateName(String name, Object pivot) {
        return String.format(
                "%s_%s",
                pivot.hashCode() % 10000,
                name
        );
    }

    public static String generateName(Object pivot) {
        return String.format(
                "%s",
                pivot.hashCode() % 10000
        );
    }
}
