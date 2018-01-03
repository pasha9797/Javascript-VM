package ru.vsu.utils;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;

public class VarNameGenerator {
    public static String generateName(String name, ExecNode pivot) {
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
