package ru.vsu.model.nodes.logical;

import ru.vsu.model.abstracts.Logical;

public class Nequals extends Logical {
    public Object execute() throws Exception {
        Object op1= executeChild(0);
        Object op2=executeChild(1);

        return !op1.equals(op2);
    }

    @Override
    public String toString() {
        try {
            return "(" + children.get(0).toString() + "!=" + children.get(1).toString() + ")";
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
