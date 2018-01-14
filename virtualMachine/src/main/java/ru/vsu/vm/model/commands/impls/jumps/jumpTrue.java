package ru.vsu.vm.model.commands.impls.jumps;

import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.commands.Command;

public class jumpTrue extends Command{
    @Override
    public void execute() throws Exception {
        if(args.size()==1){
            if(args.get(0).getValue() instanceof Integer){
                Object item = virtualMachine.popStack().getValue();
                item= ToBooleanConverter.convert(item);
                if((Boolean)item)
                    virtualMachine.setCurrentCmd((Integer)args.get(0).getValue()-1);
            }
            else{
                throw new Exception("jumpTrue: 1 argument is not integer");
            }
        }
        else{
            throw new Exception("jumpTrue needs only 1 argument");
        }
    }
}
