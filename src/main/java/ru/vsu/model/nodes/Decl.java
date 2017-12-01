package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;

public class Decl extends ExecNode {

    public Object execute() throws Exception {
        Namespace ns=getParentNameSpace();
        if(!ns.isVarSet(children.get(0).toString())){
            ns.setVarValue(children.get(0).toString(), 0);
            return true;
        }
        else
            return false;
    }

    public String toString() {
        return children.get(0).toString();
    }
}
