package ru.vsu.model.nodes.function;

import ru.vsu.model.nodes.Block;
import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;
import ru.vsu.model.nodes.SomeType;
import ru.vsu.utils.VarNameGenerator;

import java.util.List;

public class Func_Decl extends ExecNode {
    Block body;
    private boolean wasDeclared = false;

    public SomeType execute() throws Exception {
        if (!wasDeclared) {
            body = (Block) children.get(2);
            Namespace parent = getParentNameSpace();
            String name = children.get(0).toString();
            if (!parent.isVarSet(name)) {
                parent.setVarValue(name, new SomeType(this));
                wasDeclared = true;
                return new SomeType(this);
            } else throw new Exception("Function " + name + " has already been declared");
        } else
            return new SomeType(this);
    }

    public String GenerateCode() throws Exception {
        Namespace ns = (Block)children.get(2);

        String varName = VarNameGenerator.generateName(
                "retID",
                ns
        );

        String funcName = VarNameGenerator.generateName(
                children.get(0).toString(),
                getParentNameSpace()
        );

        String s = String.format("\n%d: functionStart %s\n", Pointer++, funcName);
        s += String.format("%d: decl %s\n%d: pop %s\n", Pointer++, varName, Pointer++, varName);

        for (int i = children.get(1).getChildren().size() - 1; i >= 0; i--) {
            varName = VarNameGenerator.generateName(
                    children.get(1).getChildren().get(i).toString(),
                    ns
            );
            s += String.format("%d: decl %s\n%d: pop %s\n", Pointer++, varName, Pointer++, varName);
        }
        s += children.get(2).GenerateCode();
        s += String.format("%d: functionEnd %s\n", Pointer++, funcName);
        s+=String.format("%d: pushFromVar %s\n\n", Pointer++, funcName);
        return s;
    }

    public SomeType call(List<SomeType> args) throws Exception {
        if (args.size() != children.get(1).getChildren().size())
            throw new Exception(toString() + ": Number of arguments does not match function definition");
        else {
            for (int i = 0; i < children.get(1).getChildren().size(); i++) {
                body.setVarValue(
                        children.get(1).getChildren().get(i).toString(),
                        args.get(i)
                );
            }
            return body.execute();
        }
    }

    public String toString() {
        return "function " + children.get(0).toString() + "(" + children.get(1).getChildren() + ")" + body.toString();
    }
}
