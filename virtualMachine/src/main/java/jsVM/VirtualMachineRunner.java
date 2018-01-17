package jsVM;

import jsVM.utils.CommandListBuilder;
import jsVM.model.VirtualMachine;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class VirtualMachineRunner {
    public static void main(String[] args) throws Exception{
        String in;
        if(args.length<1){
            in="out.as";
        }
        else{
            in=args[0];
        }
        List<String> cmds = Files.readAllLines(new File(in).toPath(), Charset.defaultCharset() );
        VirtualMachine machine = VirtualMachine.getInstance();
        machine.setCommands(CommandListBuilder.parseCommands(cmds));
        machine.run();
    }
}
