package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;

public class Ident extends ExecNode {
    public Object execute() throws Exception {
        Namespace ns = getParentNameSpaceWithVar(value.toString());
        if(ns != null)
            return ns.getVarValue(value.toString());
        else
            throw new Exception("Variable '" + value.toString() + "' is not set");
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
