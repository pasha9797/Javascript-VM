package jsCompil.model.nodes.array;

import jsCompil.model.nodes.SomeType;
import jsCompil.model.abstracts.ExecNode;

import java.util.List;

public class Array_Call extends ExecNode {
    public SomeType execute() throws Exception {
        List arr = getList();
        int id = getID();

        return new SomeType(arr.get(id));
    }

    public String generateCode() throws Exception {
        String s = "\t//Array call begin\n";
        s += children.get(0).generateCode();
        s += children.get(1).generateCode();
        return String.format("%s%d: pushFromArray\n\t//Array call end\n", s, Pointer++);
    }

    public List getList() throws Exception {
        SomeType arr = executeChild(1);
        if (!((arr).getValue() instanceof List)) {
            throw new Exception(children.get(1).toString() + " is not array");
        }

        return (List) arr.getValue();
    }

    public String getName() {
        return children.get(1).toString();
    }

    public int getID() throws Exception {
        SomeType id = children.get(0).execute();
        if (!(id.getValue() instanceof Integer)) {
            throw new Exception(id.toString() + " is not integer (array index)");
        }

        return (Integer) id.getValue();
    }

    public String toString() {
        return children.get(1).toString() + "[" + children.get(0).toString() + "]";
    }
}
