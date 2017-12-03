package ru.vsu.model.nodes.loops;

import ru.vsu.model.nodes.Block;
import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.model.abstracts.ExecNode;

public class While extends ExecNode{
    public Object execute() throws Exception {
        Block block;

        if (!(children.get(1) instanceof Block)) { //AST modification
            block = new Block();
            block.setParent(this);
            block.getChildren().add(children.get(1));
            children.get(1).setParent(block);
            children.set(1, block);
        } else
            block = (Block) children.get(1);

        ExecNode cond = children.get(0);

        while(ToBooleanConverter.convert(cond.execute()) && !block.getWasReturn()){
            block.execute();
        }

        return this;
    }

    public String toString() {
        return "while("+children.get(0)+")\n"+children.get(1);
    }
}
