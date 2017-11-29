package ru.vsu.model;

import ru.vsu.model.interfaces.Element;

import java.lang.Number;

public class Sub implements Element{
    Object getOperand(int id) throws Exception{
        return children.get(id).proceed();
    }

    public Object proceed() throws Exception {
        if(getOperand(0) instanceof java.lang.Number && getOperand(1) instanceof java.lang.Number){
            return (Float)getOperand(0) - (Float)getOperand(1);
        }
        else{
            throw new Exception("Unable to substitute non-numbers");
        }
    }

    @Override
    public String toString() {
        try {
            return getOperand(0).toString() + "-" + getOperand(1).toString();
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
