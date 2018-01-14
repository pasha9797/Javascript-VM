package ru.vsu.vm.model.commands.impls.values;

import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.commands.Command;

public class pushFromVar extends Command{
    /**
     * useless
     */
    public void execute() throws Exception {
        if(args.size()==1){
            virtualMachine.pushStack(args.get(0).getConstantArg());
        }
        else{
            throw new Exception("pushFromVar needs only 1 argument");
        }
    }
}
