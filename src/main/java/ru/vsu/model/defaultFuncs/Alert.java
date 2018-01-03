package ru.vsu.model.defaultFuncs;

import ru.vsu.model.nodes.Block;
import ru.vsu.model.nodes.Container;
import ru.vsu.model.nodes.SomeType;
import ru.vsu.model.nodes.function.Func_Decl;
import ru.vsu.model.nodes.Ident;

import java.util.List;

public class Alert extends Func_Decl{
    @Override
    public SomeType call(List<SomeType> args) throws Exception{
        if(args.size()==1){
            System.out.println(args.get(0));
            return new SomeType(this);
        }
        else
            throw new Exception("Alert must accept 1 argument");
    }
    public Alert(){
        Ident name = new Ident();
        name.setValue("alert");

        Container args= new Container();
        Ident arg = new Ident();
        arg.setValue("argument");
        args.getChildren().add(arg);

        Block body = new Block();

        children.add(name);
        children.add(args);
        children.add(body);
    }
}
