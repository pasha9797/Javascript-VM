package ru.vsu.js.model.nodes.logical;

import ru.vsu.js.model.abstracts.Logical;
import ru.vsu.js.model.nodes.SomeType;
import ru.vsu.utils.ToBooleanConverter;

public class Not extends Logical {
    public SomeType execute() throws Exception {
        Object op= children.get(0).execute().getValue();

        op= ToBooleanConverter.convert(op);

        return new SomeType(!((Boolean)op));
    }

    public String generateCode() throws Exception {
        String s = children.get(0).generateCode();
        return s + String.format("%d: not\n", Pointer++);
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
