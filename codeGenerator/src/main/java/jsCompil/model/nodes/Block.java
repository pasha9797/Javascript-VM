package jsCompil.model.nodes;

import jsCompil.model.abstracts.ExecNode;
import jsCompil.model.abstracts.Namespace;

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

    public String generateCode() throws Exception {
        String s="";
        for (ExecNode cmd : children) {
            s+=cmd.generateCode();
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
