package ru.vsu.js.model.nodes;

import ru.vsu.js.model.abstracts.Namespace;
import ru.vsu.js.model.abstracts.ExecNode;
import ru.vsu.js.utils.VarNameGenerator;

public class Ident extends ExecNode {
    SomeType varValue;

    public SomeType execute() throws Exception {
        Namespace ns = getParentNameSpaceWithVar(value.toString());
        if (ns != null)
            return varValue = ns.getVarValue(value.toString());
        else
            throw new Exception("Variable '" + value.toString() + "' is not set");
    }

    public String generateCode() throws Exception {
        String varName = VarNameGenerator.generateName(
                value.toString(),
                getParentNameSpaceWithVar(value.toString())
        );
        return String.format("%d: pushFromVar %s\n", Pointer++, varName);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
