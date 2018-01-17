package jsVM.model.args;

public interface  Argument {
    Object getValue() throws Exception;
    ConstantArgument getConstantArg()throws Exception;
}
