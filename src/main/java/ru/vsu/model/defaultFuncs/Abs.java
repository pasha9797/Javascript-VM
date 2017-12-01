package ru.vsu.model.defaultFuncs;

import ru.vsu.model.nodes.Block;
import ru.vsu.model.nodes.Container;
import ru.vsu.model.nodes.function.Func_Decl;
import ru.vsu.model.nodes.Ident;

import java.util.List;

public class Abs extends Func_Decl{
    @Override
    public Object call(List<Object> args) throws Exception{
        if(args.size()==1 && args.get(0) instanceof Number){
            return Math.abs(((Number) args.get(0)).floatValue());
        }
        else
            throw new Exception("Abs must accept 1 argument");
    }
    public Abs(){
        Ident name = new Ident();
        name.setValue("abs");

        Container args= new Container();
        Ident arg = new Ident();
        arg.setValue("value");
        args.getChildren().add(arg);

        Block body = new Block();

        children.add(name);
        children.add(args);
        children.add(body);
    }
}
