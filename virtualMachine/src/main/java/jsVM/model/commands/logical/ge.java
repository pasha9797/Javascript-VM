package jsVM.model.commands.logical;

import jsVM.model.args.ConstantArgument;
import jsVM.model.commands.abstracts.Command;

public class ge extends Command {
    public void execute() throws Exception {
        if(args.size()==0){
            ConstantArgument op2 = virtualMachine.popStack();
            ConstantArgument op1 = virtualMachine.popStack();

            virtualMachine.pushStack(op1.ge(op2));
        }
        else{
            throw new Exception("ge does not need any arguments");
        }
    }
}
