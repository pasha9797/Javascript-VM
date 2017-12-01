package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;

public class Return extends ExecNode {
    public Object execute() throws Exception {
        Object returnValue = executeChild(0);
        getParentNameSpace().setReturnValue(returnValue);
        return this;
    }

    public String toString() {
        return "return "+children.get(0).toString();
    }
}
