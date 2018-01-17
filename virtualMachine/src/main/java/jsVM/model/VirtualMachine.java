package jsVM.model;

import jsVM.model.commands.abstracts.Command;
import jsVM.model.commands.functions.functionEnd;
import jsVM.model.commands.functions.functionStart;
import jsVM.model.args.VariableArgument;
import jsVM.model.args.ConstantArgument;
import jsVM.model.commands.functions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class VirtualMachine {
    private static VirtualMachine instance;

    private List<Command> commands=new ArrayList<Command>();
    private int currentCmd=0;
    private HashMap<String, ConstantArgument> variablesTable = new HashMap<>();
    private List<ConstantArgument> stack = new LinkedList<>();
    private List<Integer> retIDStack=new LinkedList<>();

    private VirtualMachine() {
    }

    public void pushStack(ConstantArgument item){
        stack.add(item);
    }

    public ConstantArgument popStack(){
        return stack.remove(stack.size()-1);
    }

    public void funcCall(int startID, int retID){
        retIDStack.add(retID);
        setCurrentCmd(startID);
    }

    public void funcExit(){
        int id = retIDStack.remove(retIDStack.size()-1);
        setCurrentCmd(id-1);
    }

    public int funcGetStart(String name) throws Exception{
        for(Command cmd: commands){
            if(cmd instanceof functionStart && cmd.getArgs().get(0).getValue().equals(name)){
                return cmd.getId();
            }
        }
        return -1;
    }

    public int funcGetEnd(String name) throws Exception{
        for(Command cmd: commands){
            if(cmd instanceof functionEnd
                    && cmd.getArgs().get(0) instanceof VariableArgument
                    && ((VariableArgument)cmd.getArgs().get(0)).getName().equals(name)){
                return cmd.getId();
            }
        }
        return -1;
    }

    public ConstantArgument getVarValue(String name) throws Exception{
        if(variablesTable.containsKey(name)){
            return variablesTable.get(name);
        }
        else{
            throw new Exception("Namespace does not contain variable "+name);
        }
    }

    public void setVarValue(String name, ConstantArgument item){
        variablesTable.put(name, item);
    }

    public int getCurrentCmd() {
        return currentCmd;
    }

    public void setCurrentCmd(int currentCmd) {
        this.currentCmd = currentCmd;
    }

    public void setCommands(List<Command> commands){
        this.commands=commands;
    }

    public void run()throws Exception{
        functionsPreDeclaration();
        currentCmd=0;
        while(currentCmd<commands.size()){
            Command command = commands.get(currentCmd);
            if(currentCmd == command.getId()){
                //System.out.println(currentCmd);
                command.execute();
            }
            else{
                throw new Exception(String.format("ID mismatch: Expected %d,found %d", currentCmd,command.getId()));
            }

            currentCmd++;
        }
    }

    private void functionsPreDeclaration()throws Exception{
        for(Command cmd: commands){
            if(cmd instanceof functionStart)
                cmd.execute();
        }
    }

    public static VirtualMachine getInstance() {
        if (instance == null)
            instance = new VirtualMachine();
        return instance;
    }
}
