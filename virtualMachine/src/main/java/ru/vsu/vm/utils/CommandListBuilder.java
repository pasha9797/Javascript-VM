package ru.vsu.vm.utils;

import ru.vsu.vm.model.args.Argument;
import ru.vsu.vm.model.args.ConstantArgument;
import ru.vsu.vm.model.args.VariableArgument;
import ru.vsu.vm.model.commands.*;
import ru.vsu.vm.model.commands.impls.math.*;
import ru.vsu.vm.model.commands.impls.logical.*;
import ru.vsu.vm.model.commands.impls.values.*;
import ru.vsu.vm.model.commands.impls.jumps.*;
import ru.vsu.vm.model.commands.impls.functions.*;

import java.util.ArrayList;
import java.util.List;
public class CommandListBuilder {
    public static List<Command> parseCommands(List<String> cmds) throws Exception {
        ArrayList<Command> commands = new ArrayList<>();

        for (String com : cmds) {
            String cmd = com.trim();
            if (cmd.length() > 0 && !cmd.startsWith("//")) {
                String[] parts = cmd.split("\\s+");

                if (parts.length >= 2) {
                    Command command;
                    int id;
                    String name;
                    List<String> strArgs = new ArrayList<>();
                    List<Argument> args = new ArrayList<>();

                    if (parts[0].matches("^\\d+:$")) {
                        id = Integer.parseInt(parts[0].substring(0, parts[0].length() - 1));
                    } else {
                        throw new Exception("Invalid command ID: " + parts[0]);
                    }

                    name = parts[1];

                    int i = 2;
                    while (i < parts.length) {
                        String arg = parts[i];
                        if (arg.startsWith("\"")) {
                            int j = i + 1;
                            i++;
                            while (j < parts.length && (!arg.endsWith("\"") || arg.length() == 1)) {
                                arg += " " + parts[j];
                                j++;
                                i++;
                            }
                        } else
                            i++;
                        strArgs.add(arg);
                    }

                    for (String arg : strArgs) {
                        if ((arg.matches("^-?\\d+(\\.\\d+)?$"))) {
                            Float num = Float.parseFloat(arg);
                            args.add(new ConstantArgument(num));
                        } else if (arg.matches("^(\\w|-|\\.)+$")) {
                            args.add(new VariableArgument(arg));
                        } else if (arg.matches("^\".*\"$")) {
                            String str = arg.substring(1, arg.length() - 1);
                            args.add(new ConstantArgument(str));
                        } else {
                            throw new Exception("Invalid argument: " + arg);
                        }
                    }

                    command = createCommand(name);
                    command.setId(id);
                    command.setArgs(args);

                    commands.add(command);
                } else {
                    throw new Exception("Invalid command: " + cmd);
                }
            }
        }

        return commands;
    }

    public static Command createCommand(String name) throws Exception {
        switch (name) {
            case "add":
                return new add();
            case "sub":
                return new sub();
            case "div":
                return new div();
            case "mul":
                return new mod();
            case "mod":
                return new mul();
            case "and":
                return new and();
            case "or":
                return new or();
            case "equals":
                return new equals();
            case "not":
                return new not();
            case "ge":
                return new ge();
            case "gt":
                return new gt();
            case "le":
                return new le();
            case "lt":
                return new lt();
            case "jump":
                return new jump();
            case "jumpTrue":
                return new jumpTrue();
            case "jumpFalse":
                return new jumpFalse();
            case "decl":
                return new decl();
            case "declArray":
                return new declArray();
            case "pop":
                return new pop();
            case "popToArray":
                return new popToArray();
            case "push":
                return new push();
            case "pushFromArray":
                return new pushFromArray();
            case "pushFromVar":
                return new pushFromVar();
            case "call":
                return new call();
            case "systemCall":
                return new systemCall();
            case "functionEnd":
                return new functionEnd();
            case "functionStart":
                return new functionStart();
            case "return":
                return new vmReturn();
            default:
                throw new Exception("Unknown command: " + name);
        }
    }
}
