package jsCompil.model.nodes;

import jsCompil.model.abstracts.Namespace;
import jsCompil.model.nodes.array.Array_Call;
import jsCompil.utils.VarNameGenerator;
import jsCompil.model.abstracts.ExecNode;

import java.util.List;

public class Assign extends ExecNode {

    public SomeType execute() throws Exception {
        if(children.get(0) instanceof Ident || children.get(0) instanceof Decl) {
            String name = children.get(0).toString();
            Namespace ns = getParentNameSpaceWithVar(name);
            if(ns == null){
                ns = getParentNameSpace();
            }
            ns.setVarValue(name, executeChild(1));
            return ns.getVarValue(name);
        }
        else if(children.get(0) instanceof Array_Call){
            List list = ((Array_Call)children.get(0)).getList();
            int id = ((Array_Call) children.get(0)).getID();
            list.set(id, executeChild(1));
            return new SomeType(list.get(id));
        }
        else
            throw new Exception("Wrong assignment to " + children.get(0).toString());
    }

    public String generateCode() throws Exception {

        if(children.get(0) instanceof Ident || children.get(0) instanceof Decl) {
            String name = children.get(0).toString();
            Namespace ns = getParentNameSpaceWithVar(name);
            if(ns == null){
                ns = getParentNameSpace();
            }

            String varName = VarNameGenerator.generateName(
                    name,
                    ns
            );
            String str = children.get(1).generateCode();
            return str+String.format("%d: pop %s\n", Pointer++, varName);
        }
        else if(children.get(0) instanceof Array_Call){
            String str = children.get(1).generateCode();
            String str2=children.get(0).getChildren().get(0).generateCode();
            String str3=children.get(0).getChildren().get(1).generateCode();
            return str+str2+str3+String.format("%d: popToArray\n", Pointer++);
        }
        else
            return null;
    }

    @Override
    public String toString() {
        try {
            return children.get(0).toString() + "=" + children.get(1).toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
