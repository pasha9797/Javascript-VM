package ru.vsu.model.nodes.math;

import ru.vsu.model.abstracts.ExecNode;

public class Div extends ExecNode {
    public Object execute() throws Exception {
        Object op1= executeChild(0);
        Object op2= executeChild(1);

        if(op1 instanceof java.lang.Number && op2 instanceof java.lang.Number){
            return ((Number) op1).floatValue() / ((Number) op2).floatValue();
        }
        else{
            throw new Exception("Unable to divide non-numbers");
        }
    }

    @Override
    public String toString() {
        try {
            return "(" + children.get(0).toString() + "/" + children.get(1).toString() + ")";
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
