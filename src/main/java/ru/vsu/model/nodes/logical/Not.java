package ru.vsu.model.nodes.logical;

import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.model.abstracts.Logical;

public class Not extends Logical {
    public Object execute() throws Exception {
        Object op= children.get(0).execute();

        op= ToBooleanConverter.convert(op);

        return !((Boolean)op);
    }

    @Override
    public String toString(){
        try {
            return "! " + children.get(0).toString();
        }
        catch(Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
