package ru.vsu.vm;

import ru.vsu.vm.model.VirtualMachine;
import ru.vsu.vm.utils.CommandListBuilder;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VirtualMachineRunner {
    public static void main(String[] args) throws Exception{
        List<String> cmds = Files.readAllLines(new File("out.txt").toPath(), Charset.defaultCharset() );
        VirtualMachine machine = VirtualMachine.getInstance();
        machine.setCommands(CommandListBuilder.parseCommands(cmds));
        machine.run();
    }
}
