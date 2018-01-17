package jsVM.model.commands.values;

import jsVM.model.commands.abstracts.Command;

public class push extends Command {
    public void execute() throws Exception {
        if(args.size()==1){
            virtualMachine.pushStack(args.get(0).getConstantArg());
        }
        else{
            throw new Exception("push needs only 1 argument");
        }
    }
}
