package ru.vsu.model.nodes.array;

import ru.vsu.model.abstracts.ExecNode;

import java.util.ArrayList;
import java.util.List;

public class Array_Decl extends ExecNode{
    public Object execute() throws Exception {
        List<Object> arr = new ArrayList<Object>();
        for(ExecNode ex : children){
            arr.add(ex.execute());
        }
        return arr;
    }

    public String toString() {
        return children.toString();
    }
}
