package jsVM.model.commands.logical;

import jsVM.model.commands.abstracts.Command;
import jsVM.model.args.ConstantArgument;

public class lt extends Command {
    public void execute() throws Exception {
        if(args.size()==0){
            ConstantArgument op2 = virtualMachine.popStack();
            ConstantArgument op1 = virtualMachine.popStack();

            virtualMachine.pushStack(op1.lt(op2));
        }
        else{
            throw new Exception("lt does not need any arguments");
        }
    }
}
