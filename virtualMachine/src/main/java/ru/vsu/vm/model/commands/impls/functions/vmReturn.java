package ru.vsu.vm.model.commands.impls.functions;

import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.args.ConstantArgument;
import ru.vsu.vm.model.commands.Command;

public class vmReturn extends Command{
    @Override
    public void execute() throws Exception {
        if(args.size()==0){
            virtualMachine.funcExit();
        }
        else{
            throw new Exception("return does not need any elements");
        }
    }
}
