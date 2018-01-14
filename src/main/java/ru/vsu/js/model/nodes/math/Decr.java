package ru.vsu.js.model.nodes.math;

import ru.vsu.js.model.abstracts.Namespace;
import ru.vsu.js.model.abstracts.ExecNode;
import ru.vsu.js.model.nodes.SomeType;
import ru.vsu.js.utils.VarNameGenerator;

public class Decr extends ExecNode{
    public SomeType execute() throws Exception {
        Namespace ns = getParentNameSpaceWithVar(children.get(0).toString());
        SomeType op = ns.getVarValue(children.get(0).toString());
        ns.setVarValue(children.get(0).toString(),op.sub(new SomeType(1)));
        return op;
    }

    public String generateCode() throws Exception {
        String s = children.get(0).generateCode();
        s+=children.get(0).generateCode();//so stack will have old value after all

        Namespace ns = getParentNameSpaceWithVar(children.get(0).toString());

        String varName = VarNameGenerator.generateName(
                children.get(0).toString(),
                ns
        );

        return String.format(s + "%d: push 1\n%d: sub\n%d: pop %s\n", Pointer++, Pointer++, Pointer++, varName);
    }

    public String toString() {
        return children.get(0).toString() + "--";
    }
}
