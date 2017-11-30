package ru.vsu.model;

import ru.vsu.model.abstracts.Element;

public class And extends Element {
    public Object proceed() throws Exception {
        Object op1= getOperand(0);
        Object op2= getOperand(1);

        if(op1 instanceof java.lang.Number){
           op1= (Float)op1 != 0;
        }
        if(op2 instanceof java.lang.Number){
            op2= (Float)op2 != 0;
        }

        if(op1 instanceof java.lang.Boolean && op2 instanceof java.lang.Boolean){
            return (Boolean)op1 && (Boolean)op2 ;
        }
        else{
            throw new Exception("Unable to &&");
        }
    }

    @Override
    public String toString() {
        try {
            return getOperand(0).toString() + "&&" + getOperand(1).toString();
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
