package ru.vsu.js.model.nodes.loops;

import ru.vsu.js.model.abstracts.ExecNode;
import ru.vsu.js.model.nodes.Block;
import ru.vsu.js.model.nodes.SomeType;
import ru.vsu.utils.ToBooleanConverter;

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

    public String generateCode() throws Exception {
        String s = "\t//Do loop begin\n%s%s%d: jumpTrue %d\n\t//Do loop end\n";
        int jumpID = Pointer;
        String blockCode = block.generateCode();
        String condCode = cond.generateCode();
        int curID = Pointer++;
        return String.format(s, blockCode, condCode, curID, jumpID);
    }

    public String toString() {
        return "do\n"+children.get(0)+"\nwhile("+children.get(1)+")";
    }
}
