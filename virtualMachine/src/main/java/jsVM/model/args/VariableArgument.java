package jsVM.model.args;

import jsVM.model.VirtualMachine;

public class VariableArgument implements Argument{
    private String name;
    private VirtualMachine virtualMachine = VirtualMachine.getInstance();

    public String getName() {
        return name;
    }

    @Override
    public Object getValue()throws Exception{
        return virtualMachine.getVarValue(name).getValue();
    }

    @Override
    public ConstantArgument getConstantArg() throws Exception{
        return virtualMachine.getVarValue(name);
    }

    public VariableArgument(String name){
        this.name=name;
    }
}
