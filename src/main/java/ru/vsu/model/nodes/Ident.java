package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;

public class Ident extends ExecNode {
    public Object execute() throws Exception {
        return getParentNameSpaceWithVar(value.toString()).getVarValue(value.toString());
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
