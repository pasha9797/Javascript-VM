package ru.vsu.model.nodes.array;

import ru.vsu.model.abstracts.ExecNode;

import java.util.List;

public class Array_Call extends ExecNode{
    public Object execute() throws Exception {
        List arr = getList();
        int id = getID();

        return arr.get(id);
    }

    public List getList() throws Exception{
        Object arr = executeChild(1);
        if(!(arr instanceof List)){
            throw new Exception(children.get(1).toString() + " is not array");
        }

        return(List) arr;
    }

    public int getID() throws  Exception{
        Object id = children.get(0).execute();
        if(!(id instanceof Number) || ((Number) id).intValue()!=((Number) id).floatValue()){
            throw new Exception(id.toString() + " is not integer (array index)");
        }

        return ((Number) id).intValue();
    }

    public String toString() {
        return children.get(1).toString() + "[" + children.get(0).toString() + "]";
    }
}
