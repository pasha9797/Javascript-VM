package ru.vsu.utils;

import ru.vsu.model.abstracts.Namespace;
import ru.vsu.model.defaultFuncs.*;

public class DefaultFuncsAdder {
    public static void addDefaultFuncs(Namespace node) throws Exception{
        Alert alert = new Alert();
        Prompt prompt = new Prompt();
        Abs abs = new Abs();

        alert.setParent(node);
        prompt.setParent(node);
        abs.setParent(node);

        alert.execute();
        prompt.execute();
        abs.execute();
    }
}
