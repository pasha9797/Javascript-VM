package jsCompil.model.nodes.function;

import jsCompil.model.abstracts.ExecNode;
import jsCompil.model.nodes.SomeType;

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

    public String generateCode() throws Exception {
        String args = "";

        for (ExecNode exec : children.get(0).getChildren()) {
            args += exec.generateCode();
        }

        if(!isSystemCall()) {
            String getFunc = children.get(1).generateCode();
            return String.format("\t//Function call begin\n%s%s%d: call\n\t//Function call end\n", args, getFunc, Pointer++);
        }
        else{
            return String.format("\t//System call begin\n%s%d: systemCall %s\n\t//System call end\n", args, Pointer++, children.get(1).toString());
        }
    }

    private boolean isSystemCall() {
        String name = children.get(1).toString();
        return name.equals("alert")
                || name.equals("alert")
                || name.equals("Math.abs")
                || name.equals("prompt");
    }

    public String toString() {
        return children.get(1).toString() + "(" + children.get(0).getChildren() + ")";
    }
}
