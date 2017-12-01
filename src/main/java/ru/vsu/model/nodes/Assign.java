package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;

public class Assign extends ExecNode {

    public Object execute() throws Exception {
        getParentNameSpace().setVarValue(children.get(0).toString(), executeChild(1));
        return getParentNameSpaceWithVar(children.get(0).toString()).getVarValue(children.get(0).toString());
    }

    @Override
    public String toString() {
        try {
            return "(" + children.get(0).toString() + ":=" + children.get(1).toString() + ")";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
