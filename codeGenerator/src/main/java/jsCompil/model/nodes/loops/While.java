package jsCompil.model.nodes.loops;

import jsCompil.model.nodes.Block;
import jsCompil.model.nodes.SomeType;
import jsCompil.utils.ToBooleanConverter;
import jsCompil.model.abstracts.ExecNode;

public class While extends ExecNode {
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

    public String generateCode() throws Exception {
        String s = "\t//While loop begin\n%s%d: jumpFalse %d\n%s%d: jump %d\n\t//While loop end\n";
        int startID=Pointer;
        String condCode = cond.generateCode();
        int curID = Pointer++;
        String blockCode = block.generateCode();
        int jumpID = Pointer+1;
        return String.format(s, condCode, curID, jumpID, blockCode, Pointer++, startID);
    }

    public String toString() {
        return "while("+children.get(0)+")\n"+children.get(1);
    }
}
