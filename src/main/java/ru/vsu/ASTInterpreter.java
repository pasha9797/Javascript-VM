package ru.vsu;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import ru.vsu.utils.DefaultFuncsAdder;
import ru.vsu.model.abstracts.*;
import ru.vsu.utils.ToExecNodeConverter;

public class ASTInterpreter {
    public static void main(String[] args) throws Exception{
        CharStream stream = new ANTLRFileStream("input.txt");
        MathLangLexer lexer = new MathLangLexer(stream);
        MathLangParser parser = new MathLangParser(new CommonTokenStream(lexer));
        CommonTree tree = (CommonTree) parser.execute().getTree();

        ExecNode node = ToExecNodeConverter.convertTree(tree, null);
        DefaultFuncsAdder.addDefaultFuncs((Namespace)node);
        node.execute();
    }
}
