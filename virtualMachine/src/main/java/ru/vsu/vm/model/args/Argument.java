package ru.vsu.vm.model.args;

public interface  Argument {
    Object getValue() throws Exception;
    ConstantArgument getConstantArg()throws Exception;
}
