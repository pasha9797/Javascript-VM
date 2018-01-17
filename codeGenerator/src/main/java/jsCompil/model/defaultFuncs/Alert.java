package jsCompil.model.defaultFuncs;

import jsCompil.model.nodes.Ident;
import jsCompil.model.nodes.Block;
import jsCompil.model.nodes.Container;
import jsCompil.model.nodes.SomeType;
import jsCompil.model.nodes.function.Func_Decl;
import jsCompil.utils.Configurator;

import java.util.List;

public class Alert extends Func_Decl {
    @Override
    public SomeType call(List<SomeType> args) throws Exception{
        if(args.size()==1){
            if(!Configurator.hiddenMode)
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
