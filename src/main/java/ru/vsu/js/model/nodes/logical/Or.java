package ru.vsu.js.model.nodes.logical;

import ru.vsu.js.model.abstracts.Logical;
import ru.vsu.js.model.nodes.SomeType;

public class Or extends Logical {
    public SomeType execute() throws Exception {
        SomeType op1= executeChild(0);
        SomeType op2= executeChild(1);

        return op1.or(op2);
    }

    public String generateCode() throws Exception {
        String s = "";
        s += children.get(0).generateCode();
        s += children.get(1).generateCode();
        return s + String.format("%d: or\n", Pointer++);
    }

    @Override
    public String toString() {
        try {
            return children.get(0).toString() + "||" + children.get(1).toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
