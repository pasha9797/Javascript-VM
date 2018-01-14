package ru.vsu.vm.model.commands;

import ru.vsu.vm.model.VirtualMachine;
import ru.vsu.vm.model.args.Argument;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    protected int id;
    protected VirtualMachine virtualMachine = VirtualMachine.getInstance();
    protected List<Argument> args=new ArrayList<>();

    public abstract void execute()throws Exception;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Argument> getArgs() {
        return args;
    }

    public void setArgs(List<Argument> args) {
        this.args = args;
    }

    public Command(){}
}
