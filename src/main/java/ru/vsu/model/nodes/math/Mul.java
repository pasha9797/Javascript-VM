package ru.vsu.model.nodes.math;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.nodes.SomeType;

public class Mul extends ExecNode {
    public SomeType execute() throws Exception {
        SomeType op1= executeChild(0);
        SomeType op2=executeChild(1);

        return op1.mul(op2);
    }

    public String GenerateCode() throws Exception {
        String s = "";
        s += children.get(0).GenerateCode();
        s += children.get(1).GenerateCode();
        return s + String.format("%d: mul\n", Pointer++);
    }

    @Override
    public String toString() {
        try {
            return children.get(0).toString() + "*" + children.get(1).toString();
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
