package jsCompil.model.nodes.math;

import jsCompil.model.abstracts.Namespace;
import jsCompil.model.nodes.SomeType;
import jsCompil.utils.VarNameGenerator;
import jsCompil.model.abstracts.ExecNode;

public class Incr extends ExecNode {
    public SomeType execute() throws Exception {
        Namespace ns = getParentNameSpaceWithVar(children.get(0).toString());
        SomeType op = ns.getVarValue(children.get(0).toString());
        ns.setVarValue(children.get(0).toString(),op.add(new SomeType(1)));
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

        return String.format(s + "%d: push 1\n%d: add\n%d: pop %s\n", Pointer++, Pointer++, Pointer++, varName);
    }

    public String toString() {
        return children.get(0).toString() + "++";
    }
}
