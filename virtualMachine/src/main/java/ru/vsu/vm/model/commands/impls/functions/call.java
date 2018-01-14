package ru.vsu.vm.model.commands.impls.functions;

import ru.vsu.vm.model.Function;
import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.commands.Command;

public class call extends Command {
    @Override
    public void execute() throws Exception {
        if(args.size()==0){
            Object item = virtualMachine.popStack().getValue();
            if(item instanceof Function){
                Function func = (Function)item;
                int retID = id+1;
                virtualMachine.funcCall(func.getStartID(), retID);
            }
            else{
                throw new Exception("call: stack top value is not function");
            }
        }
        else{
            throw new Exception("call does not need any elements");
        }
    }
}
