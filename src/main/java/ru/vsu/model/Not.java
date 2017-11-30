package ru.vsu.model;

import ru.vsu.model.abstracts.Element;

public class Not extends Element{
    public Object proceed() throws Exception {
        Object op= children.get(0).proceed();

        if(op instanceof java.lang.Number){
            op= (Float)op != 0;
        }

        if(op instanceof java.lang.Boolean){
            return !((Boolean)op);
        }
        else{
            throw new Exception("Unable to inverse");
        }
    }

    @Override
    public String toString(){
        try {
            return "! " + children.get(0).proceed().toString();
        }
        catch(Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
