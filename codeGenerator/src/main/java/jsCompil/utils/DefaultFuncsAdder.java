package jsCompil.utils;

import jsCompil.model.abstracts.Namespace;
import jsCompil.model.defaultFuncs.Alert;
import jsCompil.model.defaultFuncs.Abs;
import jsCompil.model.defaultFuncs.Prompt;

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
