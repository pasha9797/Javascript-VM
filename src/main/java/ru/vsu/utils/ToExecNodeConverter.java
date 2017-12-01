package ru.vsu.utils;

import org.antlr.runtime.tree.CommonTree;
import ru.vsu.model.abstracts.*;
import ru.vsu.MathLangParser;
import ru.vsu.model.nodes.*;
import ru.vsu.model.nodes.array.*;
import ru.vsu.model.nodes.function.*;
import ru.vsu.model.nodes.logical.*;
import ru.vsu.model.nodes.loops.*;
import ru.vsu.model.nodes.math.*;

public class ToExecNodeConverter {
    public static ExecNode convertTree(CommonTree node, ExecNode parent) throws Exception{
        ExecNode newNode= ToExecNodeConverter.convertNode(node);
        if(parent!=null) {
            newNode.setParent(parent);
            parent.getChildren().add(newNode);
        }

        if(node.getChildCount()>0) {
            for (Object child : node.getChildren()) {
                convertTree((CommonTree) child, newNode);
            }
        }

        if(newNode instanceof Func_Decl){
            newNode.execute();
        }

        return newNode;
    }

    private static ExecNode convertNode(CommonTree node) throws Exception {
        ExecNode ex;

        switch (node.getType()) {
            case MathLangParser.ADD:
                return new Add();
            case MathLangParser.SUB:
                return new Sub();
            case MathLangParser.MUL:
                return new Mul();
            case MathLangParser.DIV:
                return new Div();
            case MathLangParser.MOD:
                return new Mod();
            case MathLangParser.NUMBER:
                ex =new SomeType();
                ex.setValue(Float.valueOf(node.getText()));
                return ex;
            case MathLangParser.IDENT:
                ex =new Ident();
                ex.setValue(node.getText());
                return ex;
            case MathLangParser.STRING:
                ex =new SomeType();
                ex.setValue(node.getText().substring(1, node.getText().length()-1));
                return ex;
            case MathLangParser.GE:
                return new GE();
            case MathLangParser.GT:
                return new Gt();
            case MathLangParser.LE:
                return new LE();
            case MathLangParser.LT:
                return new Lt();
            case MathLangParser.EQUALS:
                return new Equals();
            case MathLangParser.NEQUALS:
                return new Nequals();
            case MathLangParser.ASSIGN:
                return new Assign();
            case MathLangParser.OR:
                return new Or();
            case MathLangParser.AND:
                return new And();
            case MathLangParser.NOT:
                return new Not();
            case MathLangParser.DECL:
                return new Decl();
            case MathLangParser.IF:
                return new If();
            case MathLangParser.FOR:
                return new For();
            case MathLangParser.FUNCTION:
                return new Func_Decl();
            case MathLangParser.FUNC_CALL:
                return new Func_Call();
            case MathLangParser.RETURN:
                return new Return();
            case MathLangParser.WHILE:
                return new While();
            case MathLangParser.DO:
                return new Do();
            case MathLangParser.INCR:
                return new Incr();
            case MathLangParser.DECR:
                return new Decr();
            case MathLangParser.ARRAY:
                return new Array_Decl();
            case MathLangParser.ARR_CALL:
                return new Array_Call();
            case MathLangParser.BLOCK:
                return new Block();
            case MathLangParser.PARAMS:
                return new Container();
            case MathLangParser.ARGS:
                return new Container();
            default:
                throw new Exception("Fail to convert AST tree: unknown token: " + node.getText());
        }
    }
}
