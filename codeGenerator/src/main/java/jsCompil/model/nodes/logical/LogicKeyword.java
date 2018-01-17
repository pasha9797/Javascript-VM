package jsCompil.model.nodes.logical;

import jsCompil.model.nodes.SomeType;
import jsCompil.model.abstracts.ExecNode;

public class LogicKeyword extends ExecNode {
    public SomeType execute() throws Exception {
        return new SomeType(Boolean.valueOf(value.toString()));
    }

    public String generateCode() throws Exception {
        int v;
        if(value.toString().toLowerCase().equals("true"))
            v=1;
        else
            v=0;

        return String.format("%d: push %d\n", Pointer++, v);
    }

    public String toString() {
        return value.toString();
    }
}
