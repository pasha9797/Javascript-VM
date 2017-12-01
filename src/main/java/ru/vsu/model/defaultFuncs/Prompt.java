package ru.vsu.model.defaultFuncs;

import ru.vsu.model.nodes.Block;
import ru.vsu.model.nodes.Container;
import ru.vsu.model.nodes.function.Func_Decl;
import ru.vsu.model.nodes.Ident;

import java.util.Scanner;
import java.util.List;

public class Prompt extends Func_Decl{
    @Override
    public Object call(List<Object> args) throws Exception{
        if(args.size()==2){
            System.out.print(args.get(0)+": ");
            Scanner in = new Scanner(System.in);
            return in.nextLine();
        }
        else
            throw new Exception("Promt must accept 2 argument");
    }
    public Prompt(){
        Ident name = new Ident();
        name.setValue("prompt");

        Container args= new Container();

        Ident arg1 = new Ident();
        arg1.setValue("message");
        args.getChildren().add(arg1);

        Ident arg2 = new Ident();
        arg2.setValue("default value");
        args.getChildren().add(arg2);

        Block body = new Block();

        children.add(name);
        children.add(args);
        children.add(body);
    }
}
