package ru.vsu.model.nodes;

import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.model.abstracts.ExecNode;

public class If extends ExecNode {
    public Object execute() throws Exception {
        Boolean predicate = ToBooleanConverter.convert(executeChild(0));
        if(predicate){
            return children.get(1).execute();
        }
        else if(children.size()>2){
            return children.get(2).execute();
        }
        return null;
    }

    public String toString() {
        String str;
        str = "if("+children.get(0)+")\n"+children.get(1);
        if(children.size()>2){
            str+="\nelse\n"+children.get(2);
        }
        return str;
    }
}
