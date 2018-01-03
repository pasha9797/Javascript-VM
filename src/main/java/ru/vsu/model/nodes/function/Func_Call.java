package ru.vsu.model.nodes.function;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.nodes.Ident;
import ru.vsu.model.nodes.SomeType;

import java.util.ArrayList;
import java.util.List;

public class Func_Call extends ExecNode {
    public SomeType execute() throws Exception {
        SomeType func = children.get(1).execute();
        if (func.getValue() instanceof Func_Decl) {
            List<SomeType> args = new ArrayList<SomeType>();
            for (ExecNode exec : children.get(0).getChildren()) {
                args.add(exec.execute());
            }

            return ((Func_Decl) func.getValue()).call(args);
        } else throw new Exception(children.get(1).toString() + " is not a function");
    }

    public String GenerateCode() throws Exception {
        String args = "";

        for (ExecNode exec : children.get(0).getChildren()) {
            args += exec.GenerateCode();
        }
        int curID=Pointer++;
        String getFunc=children.get(1).GenerateCode();
        return String.format("\n%s%d: push %d\n%s%d: call\n\n",args, curID, Pointer+1, getFunc, Pointer++);
    }

    public String toString() {
        return children.get(1).toString() + "(" + children.get(0).getChildren() + ")";
    }
}
