package ru.vsu.model.nodes.math;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;
import ru.vsu.model.nodes.SomeType;

public class Decr extends ExecNode{
    public SomeType execute() throws Exception {
        Namespace ns = getParentNameSpaceWithVar(children.get(0).toString());
        SomeType op = ns.getVarValue(children.get(0).toString());
        ns.setVarValue(children.get(0).toString(),op.sub(new SomeType(1)));
        return op;
    }

    public String GenerateCode() throws Exception {
        String s = children.get(0).GenerateCode();
        return String.format(s + "%d: push 1\n%d: sub\n%d: pop %s\n", Pointer++, Pointer++, Pointer++, children.get(0).toString());
    }

    public String toString() {
        return children.get(0).toString() + "--";
    }
}
