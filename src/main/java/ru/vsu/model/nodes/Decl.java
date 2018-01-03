package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;
import ru.vsu.utils.VarNameGenerator;

public class Decl extends ExecNode {

    public SomeType execute() throws Exception {
        Namespace ns=getParentNameSpace();
        if(!ns.isVarSet(children.get(0).toString())){
            ns.setVarValue(children.get(0).toString(), new SomeType("undefined"));
            return new SomeType(true);
        }
        else
            return new SomeType(false);
    }

    public String GenerateCode() throws Exception {
        String varName = VarNameGenerator.generateName(
                children.get(0).toString(),
                getParentNameSpace()
        );
        return String.format("%d: decl %s\n", Pointer++, varName);
    }

    public String toString() {
        return children.get(0).toString();
    }
}
