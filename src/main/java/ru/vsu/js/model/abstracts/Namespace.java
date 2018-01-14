package ru.vsu.js.model.abstracts;

import ru.vsu.js.model.nodes.SomeType;
import ru.vsu.js.model.nodes.function.Func_Decl;

import java.util.HashMap;

public abstract class Namespace extends ExecNode {
    private HashMap<String, SomeType> var_table=new HashMap<String, SomeType>();
    protected SomeType returnValue = null;
    protected Boolean wasReturn = false;

    public void setReturnedValue(SomeType returnValue) {
        this.returnValue = returnValue;
        wasReturn = true;
        if(!(parent instanceof Func_Decl)){
            Namespace ns = getParentNameSpace();
            ns.setReturnedValue(returnValue);
        }
    }

    public Boolean getWasReturn(){
        return wasReturn;
    }

    public void setVarValue(String name, SomeType value){
        var_table.put(name, value);
    }

    public SomeType getVarValue(String name) throws Exception{
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
