package ru.vsu.model.nodes.math;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;

public class Decr extends ExecNode{
    public Object execute() throws Exception {
        Namespace ns =getParentNameSpaceWithVar(children.get(0).toString());
        Object op = ns.getVarValue(children.get(0).toString());
        if(op instanceof Number && ((Number) op).floatValue() == ((Number) op).intValue()){
            float newOp = ((Number)op).floatValue()-1;
            ns.setVarValue(children.get(0).toString(), newOp);
            return op;
        }
        else
            throw new Exception("Can not increment non-integer");
    }

    public String toString() {
        return children.get(0).toString() + "--";
    }
}
