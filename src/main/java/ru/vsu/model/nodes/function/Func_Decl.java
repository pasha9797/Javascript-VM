package ru.vsu.model.nodes.function;

import ru.vsu.model.nodes.Block;
import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;

import java.util.List;

public class Func_Decl extends ExecNode {
    Block body;

    public Object execute() throws Exception {
       body = (Block)children.get(2);
       Namespace parent = getParentNameSpace();
       parent.setVarValue(children.get(0).toString(), this);
       return this;
    }

    public Object call(List<Object> args) throws Exception{
        if(args.size() != children.get(1).getChildren().size())
            throw new Exception(toString() + ": Number of arguments does not match function definition");
        else{
            for(int i=0; i< children.get(1).getChildren().size(); i++) {
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
