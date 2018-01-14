package ru.vsu.vm.model.commands.impls.logical;

import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.args.ConstantArgument;
import ru.vsu.vm.model.commands.Command;

public class le extends Command{
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
