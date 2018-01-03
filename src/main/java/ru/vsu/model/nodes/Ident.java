package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;
import ru.vsu.utils.VarNameGenerator;

public class Ident extends ExecNode {
    SomeType varValue;

    public SomeType execute() throws Exception {
        Namespace ns = getParentNameSpaceWithVar(value.toString());
        if (ns != null)
            return varValue = ns.getVarValue(value.toString());
        else
            throw new Exception("Variable '" + value.toString() + "' is not set");
    }

    public String GenerateCode() throws Exception {
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
