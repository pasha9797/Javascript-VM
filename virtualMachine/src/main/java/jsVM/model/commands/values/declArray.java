package jsVM.model.commands.values;

import jsVM.model.args.ConstantArgument;
import jsVM.model.args.VariableArgument;
import jsVM.model.commands.abstracts.Command;

import java.util.ArrayList;

public class declArray extends Command {
    @Override
    public void execute() throws Exception {
        if(args.size()==1){
            if(args.get(0) instanceof VariableArgument) {
                String name = ((VariableArgument) args.get(0)).getName();
                virtualMachine.setVarValue(name, new ConstantArgument(new ArrayList()));
            }
            else{
                throw new Exception("decl: argument is not variable");
            }
        }
        else{
            throw new Exception("declArray needs only 1 argument");
        }
    }
}
