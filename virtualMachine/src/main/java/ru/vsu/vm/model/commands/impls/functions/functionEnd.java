package ru.vsu.vm.model.commands.impls.functions;

import ru.vsu.vm.model.commands.Command;

public class functionEnd extends Command{
    @Override
    public void execute() throws Exception {
        if(args.size()==1){
            virtualMachine.funcExit();
        }
        else{
            throw new Exception("functionEnd needs only 1 argument");
        }
    }
}
