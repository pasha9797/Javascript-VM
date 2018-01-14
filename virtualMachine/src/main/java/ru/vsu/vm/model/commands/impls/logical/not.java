package ru.vsu.vm.model.commands.impls.logical;

import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.args.ConstantArgument;
import ru.vsu.vm.model.commands.Command;

public class not extends Command {
    public void execute() throws Exception {
        if(args.size()==0){
            Object op= virtualMachine.popStack().getValue();

            op= ToBooleanConverter.convert(op);

            virtualMachine.pushStack(new ConstantArgument(!((Boolean)op)));
        }
        else{
            throw new Exception("not does not need any arguments");
        }
    }
}
