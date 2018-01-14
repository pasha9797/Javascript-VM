package ru.vsu.js.model.defaultFuncs;

import ru.vsu.js.model.nodes.Block;
import ru.vsu.js.model.nodes.Container;
import ru.vsu.js.model.nodes.SomeType;
import ru.vsu.js.model.nodes.function.Func_Decl;
import ru.vsu.js.model.nodes.Ident;

import java.util.List;

public class Abs extends Func_Decl {
    @Override
    public SomeType call(List<SomeType> args) throws Exception{
        if(args.size()==1){
            SomeType val = args.get(0);
            if(val.getValue() instanceof Number) {
                Float res = Math.abs(((Number) val.getValue()).floatValue());
                return new SomeType(res);
            }
            else
                throw new Exception("Abs argument must be a number");
        }
        else
            throw new Exception("Abs must accept 1 argument");
    }
    public Abs(){
        Ident name = new Ident();
        name.setValue("Math.abs");

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
