package jsCompil;

import jsCompil.model.abstracts.Namespace;
import jsCompil.utils.Configurator;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import jsCompil.model.abstracts.ExecNode;
import jsCompil.MathLangLexer;
import jsCompil.MathLangParser;
import jsCompil.utils.DefaultFuncsAdder;
import jsCompil.utils.ToExecNodeConverter;

import java.io.FileWriter;
import java.io.IOException;

public class Compiler {
    public static void main(String[] args) throws Exception {
        String in, out;
        if(args.length<2){
            in= Configurator.getCompilerFile();
            out="out.as";
        }
        else{
            in=args[0];
            out=args[1];
        }

        CharStream stream = new ANTLRFileStream(in);
        MathLangLexer lexer = new MathLangLexer(stream);
        MathLangParser parser = new MathLangParser(new CommonTokenStream(lexer));
        CommonTree tree = (CommonTree) parser.execute().getTree();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.out.println((char) 27 + "[35m" + "Syntax analysis errors occurred (text above)" + (char) 27 + "[0m");
            return;
        }
        System.out.println((char) 27 + "[35m" + "Syntax analysis OK!" + (char)27 + "[0m");

        ExecNode node;
        try {
            node = ToExecNodeConverter.convertTree(tree, null);
            DefaultFuncsAdder.addDefaultFuncs((Namespace) node);
            node.execute();

        } catch (Exception e) {
            System.out.println((char) 27 + "[35m" + "Semantic analysis error: " + e.getMessage() + (char) 27 + "[0m");
            return;
        }
        System.out.println((char) 27 + "[35m" + "Semantic analysis OK!" + (char)27 + "[0m");

        try(FileWriter writer = new FileWriter(out, false))
        {
            writer.write(node.generateCode());
            writer.flush();
        }
        catch(IOException e){
            System.out.println((char) 27 + "[35m" + "Output file error: " + e.getMessage() + (char) 27 + "[0m");
            return;
        }
        System.out.println((char) 27 + "[35m" + "Code generation OK!" + (char)27 + "[0m");
    }
}
