package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;
import ru.vsu.model.nodes.function.Func_Decl;

public class Return extends ExecNode {
    public Object execute() throws Exception {
        Object returnValue = executeChild(0);
        Func_Decl func = getParentFunction();
        if(func != null) {
            Namespace ns = getParentNameSpace();
            ns.setReturnedValue(returnValue);
        }
        else{
            throw new Exception("Return statement not inside function");
        }
        return this;
    }

    public String toString() {
        return "return "+children.get(0).toString();
    }
}
