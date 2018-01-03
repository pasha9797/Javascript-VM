package ru.vsu.model.nodes.loops;

import ru.vsu.model.nodes.Block;
import ru.vsu.model.nodes.SomeType;
import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.model.abstracts.ExecNode;

public class While extends ExecNode{
    Block block;
    ExecNode cond;
    public SomeType execute() throws Exception {

        if (!(children.get(1) instanceof Block)) { //AST modification
            block = new Block();
            block.setParent(this);
            block.getChildren().add(children.get(1));
            children.get(1).setParent(block);
            children.set(1, block);
        } else
            block = (Block) children.get(1);

        cond = children.get(0);

        while(ToBooleanConverter.convert(cond.execute().getValue()) && !block.getWasReturn()){
            block.execute();
        }

        return new SomeType(this);
    }

    public String GenerateCode() throws Exception {
        String s = "\n%s%d: jumpFalse %d\n%s\n";
        String condCode = cond.GenerateCode();
        int curID = Pointer++;
        String blockCode = block.GenerateCode();
        int jumpID = Pointer;
        return String.format(s, condCode, curID, jumpID, blockCode);
    }

    public String toString() {
        return "while("+children.get(0)+")\n"+children.get(1);
    }
}
