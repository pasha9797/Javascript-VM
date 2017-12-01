package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;

public class Block extends Namespace{

    public Object execute() throws Exception {
        for(ExecNode cmd: children){
            if(!wasReturn) {
                cmd.execute();
            }
        }
        wasReturn = false;
        return returnValue;
    }

    public String toString() {
        return "Block of code";
    }
}
