package ru.vsu.model;

import ru.vsu.model.interfaces.Element;
import ru.vsu.model.interfaces.Namespace;

public class Ident implements Element {
    public Object proceed() throws Exception {
        Element par=parent;
        while(par != null){
            if(par instanceof Namespace){
                return ((Namespace)par).var_table.get(value);
            }
            else par=par.parent;
        }

        throw new Exception("Could not find parent of IDENT");
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
