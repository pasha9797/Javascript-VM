package ru.vsu.model.nodes;

import antlr.NameSpace;
import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;
import ru.vsu.model.nodes.array.Array_Call;

import javax.naming.Name;
import java.util.List;

public class Assign extends ExecNode {

    public Object execute() throws Exception {
        if(children.get(0) instanceof Ident || children.get(0) instanceof Decl) {
            Namespace ns = getParentNameSpace();
            ns.setVarValue(children.get(0).toString(), executeChild(1));
            return ns.getVarValue(children.get(0).toString());
        }
        else if(children.get(0) instanceof Array_Call){
            List list = ((Array_Call)children.get(0)).getList();
            int id = ((Array_Call) children.get(0)).getID();
            list.set(id, executeChild(1));
            return list.get(id);
        }
        else
            throw new Exception("Wrong assignment to " + children.get(0).toString());
    }

    @Override
    public String toString() {
        try {
            return "(" + children.get(0).toString() + ":=" + children.get(1).toString() + ")";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
