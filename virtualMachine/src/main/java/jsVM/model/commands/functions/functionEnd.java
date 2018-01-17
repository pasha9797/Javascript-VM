package jsVM.model.commands.functions;

import jsVM.model.commands.abstracts.Command;

public class functionEnd extends Command {
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
