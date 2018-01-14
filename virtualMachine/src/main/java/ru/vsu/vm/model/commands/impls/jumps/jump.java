package ru.vsu.vm.model.commands.impls.jumps;

import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.args.ConstantArgument;
import ru.vsu.vm.model.commands.Command;

public class jump extends Command {
    public void execute() throws Exception {
        if(args.size()==1){
            if(args.get(0).getValue() instanceof Integer){
                virtualMachine.setCurrentCmd((Integer)args.get(0).getValue()-1);
            }
            else{
                throw new Exception("jump: 1 argument is not integer");
            }
        }
        else{
            throw new Exception("jump needs only 1 argument");
        }
    }
}
