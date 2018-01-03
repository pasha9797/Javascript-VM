package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.abstracts.Namespace;
import ru.vsu.model.nodes.function.Func_Decl;

public class Block extends Namespace {

    public SomeType execute() throws Exception {
        wasReturn = false;
        for (ExecNode cmd : children) {
            if (!wasReturn || getParentFunction() == null) {
                cmd.execute();
            }
        }
        return returnValue;
    }

    public String GenerateCode() throws Exception {
        String s="";
        for (ExecNode cmd : children) {
            s+=cmd.GenerateCode();
        }
        return s;
    }

    public String toString() {
        String str = "{\n";
        for (ExecNode node : children) {
            str += node.toString() + '\n';
        }
        return str + "}";
    }
}
