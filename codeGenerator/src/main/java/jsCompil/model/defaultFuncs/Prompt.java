package jsCompil.model.defaultFuncs;

import jsCompil.model.nodes.Block;
import jsCompil.model.nodes.Ident;
import jsCompil.model.nodes.SomeType;
import jsCompil.utils.Configurator;
import jsCompil.model.nodes.Container;
import jsCompil.model.nodes.function.Func_Decl;

import java.util.Scanner;
import java.util.List;

public class Prompt extends Func_Decl {
    @Override
    public SomeType call(List<SomeType> args) throws Exception {
        if (args.size() == 2) {
            if (!Configurator.hiddenMode)
                System.out.print(args.get(0) + ": ");
            Scanner in = new Scanner(System.in);
            String line;
            if (!Configurator.hiddenMode)
                line = in.nextLine();
            else
                line = "";
            return new SomeType(line);
        } else
            throw new Exception("Promt must accept 2 argument");
    }

    public Prompt() {
        Ident name = new Ident();
        name.setValue("prompt");

        Container args = new Container();

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
