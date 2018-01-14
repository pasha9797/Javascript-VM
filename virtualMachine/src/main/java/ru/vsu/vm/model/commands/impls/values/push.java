package ru.vsu.vm.model.commands.impls.values;

import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.args.ConstantArgument;
import ru.vsu.vm.model.commands.Command;

public class push extends Command{
    public void execute() throws Exception {
        if(args.size()==1){
            virtualMachine.pushStack(args.get(0).getConstantArg());
        }
        else{
            throw new Exception("push needs only 1 argument");
        }
    }
}
