package ru.vsu.vm.model.commands.impls.values;

import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.args.ConstantArgument;
import ru.vsu.vm.model.args.VariableArgument;
import ru.vsu.vm.model.commands.Command;

public class pop extends Command{
    public void execute() throws Exception {
        if(args.size()==1){
            ConstantArgument item = virtualMachine.popStack();
            if(args.get(0) instanceof VariableArgument) {

                virtualMachine.setVarValue(((VariableArgument) args.get(0)).getName(), item);
            }
            else{
                throw new Exception("pop: argument is not variable");
            }
        }
        else{
            throw new Exception("pop needs only 1 argument");
        }
    }
}
