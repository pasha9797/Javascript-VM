package jsVM.model.commands.functions;

import jsVM.model.Function;
import jsVM.model.commands.abstracts.Command;

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
