package ru.vsu.model.abstracts;

import java.util.HashMap;

public abstract class Namespace extends ExecNode {
    private HashMap<String, Object> var_table=new HashMap<String, Object>();
    protected Object returnValue = null;
    protected Boolean wasReturn = false;

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
        wasReturn = true;
    }

    public void setVarValue(String name, Object value){
        var_table.put(name, value);
    }

    public Object getVarValue(String name) throws Exception{
        if(isVarSet(name)){
            return var_table.get(name);
        }
        else
            throw new Exception(this.toString() + " does not contain variable " + name);
    }

    public Boolean isVarSet(String name){
        return var_table.containsKey(name);
    }
}
