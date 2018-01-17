package jsCompil.model.nodes.math;

import jsCompil.model.abstracts.ExecNode;
import jsCompil.model.nodes.SomeType;

public class Add extends ExecNode {
    public SomeType execute() throws Exception {
        SomeType op1 = executeChild(0);
        SomeType op2 = executeChild(1);

        return op1.add(op2);
    }

    public String generateCode() throws Exception {
        String s = "";
        s += children.get(0).generateCode();
        s += children.get(1).generateCode();
        return s + String.format("%d: add\n", Pointer++);
    }

    @Override
    public String toString() {
        try {
            return children.get(0).toString() + "+" + children.get(1).toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}
