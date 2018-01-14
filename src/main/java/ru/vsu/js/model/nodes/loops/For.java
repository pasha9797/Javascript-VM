package ru.vsu.js.model.nodes.loops;

import ru.vsu.js.model.abstracts.ExecNode;
import ru.vsu.js.model.nodes.Block;
import ru.vsu.js.model.nodes.SomeType;
import ru.vsu.utils.ToBooleanConverter;

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

    public String generateCode() throws Exception {
        String s = "\t//For loop begin\n%s%s%d: jumpFalse %d\n%s%s%d: jump %d\n\t//For loop end\n";
        String initCode=init.generateCode();
        int jump2ID=Pointer;
        String condCode = cond.generateCode();
        int curID = Pointer++;
        String blockCode = block.generateCode();
        String repeatCode = repeat.generateCode();
        int jumpID = Pointer+1;
        return String.format(s, initCode, condCode, curID, jumpID, blockCode, repeatCode, Pointer++, jump2ID);
    }

    public String toString() {
        return "for("+children.get(0)+';'+children.get(1)+';'+children.get(2)+")\n"+children.get(3);
    }
}
