package ru.vsu.vm.model.commands.impls.values;

import ru.vsu.js.model.nodes.SomeType;
import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.args.ConstantArgument;
import ru.vsu.vm.model.commands.Command;

import java.util.List;

public class pushFromArray extends Command{
    public void execute() throws Exception {
        if(args.size()==0){
            Object arr = virtualMachine.popStack().getValue();
            Object index = virtualMachine.popStack().getValue();
            if(index instanceof Integer) {
                if(arr instanceof List) {
                    virtualMachine.pushStack((ConstantArgument) ((List) arr).get((Integer)index));
                }
                else{
                    throw new Exception("pushFromArray: stack second value is not array name");
                }
            }
            else{
                throw new Exception("pushFromArray: Stack top value is not integer");
            }
        }
        else{
            throw new Exception("push does not need any arguments");
        }
    }
}
