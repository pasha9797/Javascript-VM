package ru.vsu.model.nodes.logical;

import ru.vsu.model.abstracts.Logical;

public class GE extends Logical {
    public Object execute() throws Exception {
        Object op1= executeChild(0);
        Object op2=executeChild(1);

        if(op1 instanceof java.lang.Number && op2 instanceof java.lang.Number){
            return ((Number) op1).floatValue() >= ((Number) op2).floatValue();
        }
        else if(op1 instanceof String && op2 instanceof String){
            return ((String) op1).compareTo((String) op2) >=0;
        }
        else{
            throw new Exception("Unable to compare non-numbers and non-strings");
        }
    }

    @Override
    public String toString() {
        try {
            return "(" + children.get(0).toString() + ">=" + children.get(1).toString() + ")";
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
