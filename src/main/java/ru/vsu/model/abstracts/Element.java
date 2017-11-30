package ru.vsu.model.abstracts;

import java.util.List;

public abstract class Element {
    public int type = 0;
    public Element parent = null;
    public List<Element> children = null;
    public Object value=null;

    public abstract Object proceed() throws Exception;

    protected Object getOperand(int id) throws Exception{
        return children.get(id).proceed();
    }

    protected Namespace getParentNameSpace() throws  Exception{
        Element par = parent;
        while (par != null) {
            if (par instanceof Namespace) {
                return (Namespace) par;
            } else par = par.parent;
        }
        throw new Exception("Could not find parent of " + this.getClass().getName());
    }

    @Override
    public abstract String toString();
}
