package ru.vsu.js.model.nodes;

import ru.vsu.js.model.abstracts.ExecNode;
import ru.vsu.js.model.abstracts.Namespace;
import ru.vsu.js.model.nodes.function.Func_Decl;

public class Return extends ExecNode {
    SomeType returnValue;
    public SomeType execute() throws Exception {
        if(children.size()>0)
            returnValue = executeChild(0);
        else
            returnValue = null;

        Func_Decl func = getParentFunction();
        if(func != null) {
            Namespace ns = getParentNameSpace();
            ns.setReturnedValue(returnValue);
        }
        else{
            throw new Exception("Return statement not inside function");
        }
        return new SomeType(this);
    }

    public String generateCode() throws Exception {
        return String.format("%s%d: return\n", children.get(0).generateCode(), Pointer++);
    }

    public String toString() {
        return "return "+children.get(0).toString();
    }
}
