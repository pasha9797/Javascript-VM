package ru.vsu.vm.model.commands.impls.functions;

import ru.vsu.vm.model.args.ConstantArgument;
import ru.vsu.vm.model.args.VariableArgument;
import ru.vsu.vm.model.commands.Command;

import java.util.Scanner;

public class systemCall extends Command {
    @Override
    public void execute() throws Exception {
        if (args.size() == 1) {
            if (args.get(0) instanceof VariableArgument) {

                String name = ((VariableArgument) args.get(0)).getName();
                switch (name) {
                    case "alert":
                        Object item = virtualMachine.popStack().getValue();
                        System.out.println(item);
                        break;

                    case "prompt":
                        Object placeholder = virtualMachine.popStack().getValue();
                        Object title = virtualMachine.popStack().getValue();
                        System.out.print(String.format("%s(placeholder - %s):", title, placeholder));
                        Scanner in = new Scanner(System.in);
                        virtualMachine.pushStack(new ConstantArgument(in.nextLine()));
                        break;

                    case "Math.abs":
                        Object num = virtualMachine.popStack().getValue();
                        if (num instanceof Number) {
                            Float res = Math.abs(((Number) num).floatValue());
                            virtualMachine.pushStack(new ConstantArgument(res));
                        } else {
                            throw new Exception("abs argument must be a number");
                        }
                        break;

                    default:
                        throw new Exception("Unknown system call: " + name);
                }
            } else {
                throw new Exception("systemCall needs only 1 argument");
            }
        }
    }
}
