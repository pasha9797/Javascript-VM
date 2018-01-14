package ru.vsu.js.utils;

import ru.vsu.js.model.abstracts.Namespace;
import ru.vsu.js.model.defaultFuncs.Abs;
import ru.vsu.js.model.defaultFuncs.Alert;
import ru.vsu.js.model.defaultFuncs.Prompt;

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
