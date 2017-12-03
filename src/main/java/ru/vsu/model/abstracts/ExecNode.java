package ru.vsu.model.abstracts;

import ru.vsu.model.nodes.function.Func_Decl;

import java.util.ArrayList;
import java.util.List;

public abstract class ExecNode {
    protected ExecNode parent = null;
    protected List<ExecNode> children = new ArrayList<ExecNode>();
    protected Object value = null;

    public ExecNode getParent() {
        return parent;
    }

    public void setParent(ExecNode parent) {
        this.parent = parent;
    }

    public List<ExecNode> getChildren() {
        return children;
    }

    public void setChildren(List<ExecNode> children) {
        this.children = children;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public abstract Object execute() throws Exception;

    protected Object executeChild(int id) throws Exception {
        return children.get(id).execute();
    }

    protected Namespace getParentNameSpace() {
        ExecNode par = parent;
        while (par != null) {
            if (par instanceof Namespace) {
                return (Namespace) par;
            } else par = par.parent;
        }
        return null;
    }

    protected Func_Decl getParentFunction() {
        ExecNode par = parent;
        while (par != null) {
            if (par instanceof Func_Decl) {
                return (Func_Decl) par;
            } else par = par.parent;
        }
        return null;
    }

    protected Namespace getParentNameSpaceWithVar(String name) {
        ExecNode par = parent;
        while (par != null) {
            if (par instanceof Namespace && ((Namespace) par).isVarSet(name)) {
                return (Namespace) par;
            } else par = par.parent;
        }
        return null;
    }

    @Override
    public abstract String toString();
}
