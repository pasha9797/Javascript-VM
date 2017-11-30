package ru.vsu.model;

import ru.vsu.model.abstracts.Element;
import ru.vsu.model.abstracts.Namespace;

public class Decl extends Element{

    public Object proceed() throws Exception {
        Namespace ns=getParentNameSpace();
        if(!ns.var_table.containsKey(children.get(0).toString())){
            ns.var_table.put(children.get(0).toString(), null);
            return true;
        }
        else
            return false;
    }

    public String toString() {
        return "var " + children.get(0).toString();
    }
}
