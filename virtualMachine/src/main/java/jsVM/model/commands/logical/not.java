package jsVM.model.commands.logical;

import jsCompil.utils.ToBooleanConverter;
import jsVM.model.args.ConstantArgument;
import jsVM.model.commands.abstracts.Command;

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
