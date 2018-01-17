package jsVM.model.commands.functions;

import jsVM.model.commands.abstracts.Command;

public class vmReturn extends Command {
    @Override
    public void execute() throws Exception {
        if(args.size()==0){
            virtualMachine.funcExit();
        }
        else{
            throw new Exception("return does not need any elements");
        }
    }
}
