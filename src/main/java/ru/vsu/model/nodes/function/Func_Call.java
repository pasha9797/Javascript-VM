package ru.vsu.model.nodes.function;

import ru.vsu.model.abstracts.ExecNode;

import java.util.ArrayList;
import java.util.List;

public class Func_Call extends ExecNode {
    public Object execute() throws Exception {
        Object func = children.get(0).execute();
        if(func instanceof Func_Decl){
            List<Object> args = new ArrayList<Object>();
            for(ExecNode exec: children.get(1).getChildren()){
                args.add(exec.execute());
            }

            return ((Func_Decl)func).call(args);
        }
        else throw new Exception(children.get(0).toString() + " is not a function");
    }

    public String toString() {
        return "call " + children.get(0).toString() + "(" + children.get(1).getChildren() +  ")";
    }
}
