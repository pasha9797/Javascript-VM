package ru.vsu.model;

import ru.vsu.model.abstracts.Element;
import ru.vsu.model.abstracts.Namespace;

public class Assign extends Element{

    public Object proceed() throws Exception {
        getParentNameSpace().var_table.put(children.get(0).toString(), children.get(1).proceed());
        return true;
    }

    @Override
    public String toString() {
        try {
            return children.get(0).toString() + "=" + children.get(1).proceed().toString();
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
