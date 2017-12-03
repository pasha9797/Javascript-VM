package ru.vsu.model.nodes.loops;

import ru.vsu.model.nodes.Block;
import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.model.abstracts.ExecNode;

public class Do extends ExecNode {
    public Object execute() throws Exception {
        Block block;

        if (!(children.get(0) instanceof Block)) { //AST modification
            block = new Block();
            block.setParent(this);
            block.getChildren().add(children.get(0));
            children.get(0).setParent(block);
            children.set(0, block);
        } else
            block = (Block) children.get(0);

        ExecNode cond = children.get(1);
        cond.setParent(block);

        do {
            block.execute();
        } while (ToBooleanConverter.convert(cond.execute()) && !block.getWasReturn());

        return this;
    }

    public String toString() {
        return "do\n"+children.get(0)+"\nwhile("+children.get(1)+")";
    }
}
