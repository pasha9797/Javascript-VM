package ru.vsu.model.abstracts;

import ru.vsu.model.nodes.function.Func_Decl;

import javax.naming.Name;
import java.util.HashMap;

public abstract class Namespace extends ExecNode {
    private HashMap<String, Object> var_table=new HashMap<String, Object>();
    protected Object returnValue = null;
    protected Boolean wasReturn = false;

    public void setReturnedValue(Object returnValue) {
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
