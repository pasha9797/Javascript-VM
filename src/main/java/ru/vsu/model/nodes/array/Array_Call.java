package ru.vsu.model.nodes.array;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.nodes.Ident;
import ru.vsu.model.nodes.SomeType;
import ru.vsu.utils.VarNameGenerator;

import java.util.List;

public class Array_Call extends ExecNode {
    public SomeType execute() throws Exception {
        List arr = getList();
        int id = getID();

        return new SomeType(arr.get(id));
    }

    public String GenerateCode() throws Exception {
        String s = "";
        s += children.get(1).GenerateCode();

        s += children.get(0).GenerateCode();
        return String.format("\n%s%d: pushFromArray\n\n", s, Pointer++);
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
