package jsCompil.model.defaultFuncs;

import jsCompil.model.nodes.Block;
import jsCompil.model.nodes.Ident;
import jsCompil.model.nodes.SomeType;
import jsCompil.model.nodes.Container;
import jsCompil.model.nodes.function.Func_Decl;

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
