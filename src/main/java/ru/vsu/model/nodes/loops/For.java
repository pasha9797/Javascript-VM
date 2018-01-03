package ru.vsu.model.nodes.loops;

import ru.vsu.model.nodes.Block;
import ru.vsu.model.nodes.SomeType;
import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.model.abstracts.ExecNode;

public class For extends ExecNode {
    Block block;
    ExecNode init, cond, repeat;
    public SomeType execute() throws Exception {

        if (!(children.get(3) instanceof Block)) { //AST modification
            block = new Block();
            block.setParent(this);
            block.getChildren().add(children.get(3));
            children.get(3).setParent(block);
            children.set(3, block);
        } else
            block = (Block) children.get(3);

        init = children.get(0);
        init.setParent(block);

        cond = children.get(1);
        cond.setParent(block);

        repeat = children.get(2);
        repeat.setParent(block);

        init.execute();
        while(ToBooleanConverter.convert(cond.execute().getValue()) && !block.getWasReturn()){
            block.execute();
            repeat.execute();
        }

        return new SomeType(this);
    }

    public String GenerateCode() throws Exception {
        String s = "\n%s%s%d: jumpFalse %d\n%s%s\n";
        String initCode=init.GenerateCode();
        String condCode = cond.GenerateCode();
        int curID = Pointer++;
        String blockCode = block.GenerateCode();
        String repeatCode = repeat.GenerateCode();
        int jumpID = Pointer;
        return String.format(s, initCode, condCode, curID, jumpID, blockCode, repeatCode);
    }

    public String toString() {
        return "for("+children.get(0)+';'+children.get(1)+';'+children.get(2)+")\n"+children.get(3);
    }
}
