package ru.vsu.model.nodes.array;

import ru.vsu.model.abstracts.ExecNode;

import java.util.List;

public class Array_Call extends ExecNode{
    public Object execute() throws Exception {
        Object arr = executeChild(0);
        Object id = children.get(1).execute();
        if(!(arr instanceof List)){
            throw new Exception(children.get(0).toString() + " is not array");
        }
        if(!(id instanceof Number) || ((Number) id).intValue()!=((Number) id).floatValue()){
            throw new Exception(children.get(1).toString() + " is not integer (array index)");
        }

        return ((List) arr).get(((Number) id).intValue());
    }

    public String toString() {
        return children.get(0).toString() + "[" + children.get(1).toString() + "]";
    }
}
