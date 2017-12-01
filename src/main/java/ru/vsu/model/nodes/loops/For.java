package ru.vsu.model.nodes.loops;

import ru.vsu.model.nodes.Block;
import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.model.abstracts.ExecNode;

public class For extends ExecNode {
    public Object execute() throws Exception {
        Block block;

        if (!(children.get(3) instanceof Block)) { //AST modification
            block = new Block();
            block.setParent(this);
            block.getChildren().add(children.get(3));
            children.get(3).setParent(block);
            children.set(3, block);
        } else
            block = (Block) children.get(3);

        ExecNode init = children.get(0);
        init.setParent(block);

        ExecNode cond = children.get(1);
        cond.setParent(block);

        ExecNode repeat = children.get(2);
        repeat.setParent(block);

        init.execute();
        while(ToBooleanConverter.convert(cond.execute())){
            block.execute();
            repeat.execute();
        }

        return this;
    }

    public String toString() {
        return "'For' loop";
    }
}
