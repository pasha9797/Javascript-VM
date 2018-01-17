package jsVM.model.commands.math;

import jsVM.model.commands.abstracts.Command;
import jsVM.model.args.ConstantArgument;

public class add extends Command {
    @Override
    public void execute() throws Exception {
        if(args.size()==0){
            ConstantArgument op2 = virtualMachine.popStack();
            ConstantArgument op1 = virtualMachine.popStack();

            virtualMachine.pushStack(op1.add(op2));
        }
        else{
            throw new Exception("add does not need any arguments");
        }
    }
}
