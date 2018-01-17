package jsCompil.model.nodes.array;

import jsCompil.model.abstracts.ExecNode;
import jsCompil.model.nodes.SomeType;
import jsCompil.utils.VarNameGenerator;

import java.util.ArrayList;
import java.util.List;

public class Array_Decl extends ExecNode {
    public SomeType execute() throws Exception {
        return new SomeType(getList());
    }

    public List getList() throws Exception {
        List<Object> arr = new ArrayList<Object>();
        for (ExecNode ex : children) {
            arr.add(ex.execute());
        }
        return arr;
    }

    public String generateCode() throws Exception {
        String varName = VarNameGenerator.generateName(
                "tmp",
                getList()
        );
        String s = "\t//Array declaration begin\n";
        s += String.format("%d: declArray %s\n", Pointer++, varName);
        int i = 0;
        for (ExecNode ex : children) {
            s += String.format("\t//Insert value %d begin\n", i);
            s += ex.generateCode();
            s += String.format("%d: push %d\n", Pointer++, i);
            s += String.format("%d: pushFromVar %s\n", Pointer++, varName);
            s += String.format("%d: popToArray\n", Pointer++);
            i++;
        }
        s+="\t//Array filled, pushing it to stack\n";
        return s + String.format("%d: pushFromVar %s\n\t//Array declaration end\n", Pointer++, varName);
    }

    public String toString() {
        return children.toString();
    }
}
