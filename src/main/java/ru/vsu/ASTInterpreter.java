package ru.vsu;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import ru.vsu.utils.Configurator;
import ru.vsu.utils.DefaultFuncsAdder;
import ru.vsu.model.abstracts.*;
import ru.vsu.utils.ToExecNodeConverter;

public class ASTInterpreter {
    public static void main(String[] args) throws Exception {
        CharStream stream = new ANTLRFileStream(Configurator.getInterpreterFile());
        MathLangLexer lexer = new MathLangLexer(stream);
        MathLangParser parser = new MathLangParser(new CommonTokenStream(lexer));
        CommonTree tree = (CommonTree) parser.execute().getTree();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.out.println((char) 27 + "[35m" + "Syntax analysis errors occurred (text above)" + (char)27 + "[0m");
            return;
        }

        try {
            ExecNode node = ToExecNodeConverter.convertTree(tree, null);
            DefaultFuncsAdder.addDefaultFuncs((Namespace) node);
            node.execute();
        } catch (Exception e) {
            System.out.println((char) 27 + "[35m" + "Semantic analysis error: " + e.getMessage() + (char)27 + "[0m");

        }
    }
}
