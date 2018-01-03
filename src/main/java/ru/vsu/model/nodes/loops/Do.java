package ru.vsu.model.nodes.loops;

import ru.vsu.model.nodes.Block;
import ru.vsu.model.nodes.SomeType;
import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.model.abstracts.ExecNode;

public class Do extends ExecNode {
    Block block;
    ExecNode cond;
    public SomeType execute() throws Exception {

        if (!(children.get(0) instanceof Block)) { //AST modification
            block = new Block();
            block.setParent(this);
            block.getChildren().add(children.get(0));
            children.get(0).setParent(block);
            children.set(0, block);
        } else
            block = (Block) children.get(0);

        cond = children.get(1);
        cond.setParent(block);

        do {
            block.execute();
        } while (ToBooleanConverter.convert(cond.execute().getValue()) && !block.getWasReturn());

        return new SomeType(this);
    }

    public String GenerateCode() throws Exception {
        String s = "\n%s%s%d: jumpTrue %d\n\n";
        int jumpID = Pointer;
        String blockCode = block.GenerateCode();
        String condCode = cond.GenerateCode();
        int curID = Pointer++;
        return String.format(s, blockCode, condCode, curID, jumpID);
    }

    public String toString() {
        return "do\n"+children.get(0)+"\nwhile("+children.get(1)+")";
    }
}
