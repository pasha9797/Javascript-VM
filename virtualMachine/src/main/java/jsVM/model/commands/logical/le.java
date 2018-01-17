package jsVM.model.commands.logical;

import jsVM.model.args.ConstantArgument;
import jsVM.model.commands.abstracts.Command;

public class le extends Command {
    public void execute() throws Exception {
        if(args.size()==0){
            ConstantArgument op2 = virtualMachine.popStack();
            ConstantArgument op1 = virtualMachine.popStack();

            virtualMachine.pushStack(op1.le(op2));
        }
        else{
            throw new Exception("le does not need any arguments");
        }
    }
}
