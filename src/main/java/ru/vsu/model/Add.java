package ru.vsu.model;

import ru.vsu.model.abstracts.Element;

public class Add extends Element{
    public Object proceed() throws Exception {
        if(getOperand(0) instanceof java.lang.Number && getOperand(1) instanceof java.lang.Number){
            return (Float)getOperand(0) + (Float)getOperand(1);
        }
        else{
            return getOperand(0).toString() + getOperand(1).toString();
        }
    }

    @Override
    public String toString() {
        try {
            return getOperand(0).toString() + "+" + getOperand(1).toString();
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

}
