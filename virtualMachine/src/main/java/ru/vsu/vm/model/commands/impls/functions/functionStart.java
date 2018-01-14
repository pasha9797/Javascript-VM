package ru.vsu.vm.model.commands.impls.functions;

import ru.vsu.vm.model.Function;
import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.args.ConstantArgument;
import ru.vsu.vm.model.args.VariableArgument;
import ru.vsu.vm.model.commands.Command;

public class functionStart extends Command {
    private boolean wasDeclared = false;

    @Override
    public void execute() throws Exception {
        if (args.size() == 1) {
            int endID = getEndID();
            if (!wasDeclared)
                declare();
            virtualMachine.setCurrentCmd(endID);
        } else {
            throw new Exception("functionStart needs only 1 argument");
        }
    }

    private String getName() throws Exception {
        if (args.size() == 1) {
            if (args.get(0) instanceof VariableArgument) {
                return ((VariableArgument) args.get(0)).getName();
            } else {
                throw new Exception("functionStart: could not find matching functionEnd");
            }
        } else {
            throw new Exception("functionStart needs only 1 argument");
        }
    }

    private int getEndID() throws Exception {
        int id = virtualMachine.funcGetEnd(getName());
        if (id >= 0)
            return id;
        else
            throw new Exception("functionStart: could not find matching functionEnd");
    }

    public void declare() throws Exception {
        Function function = new Function();
        function.setStartID(this.id);
        function.setEndID(getEndID());
        virtualMachine.setVarValue(getName(), new ConstantArgument(function));
        wasDeclared=true;
    }
}
