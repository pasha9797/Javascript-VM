package ru.vsu.model;

import ru.vsu.model.abstracts.Element;
import ru.vsu.model.abstracts.Namespace;

public class Ident extends Element {
    public Object proceed() throws Exception {
        return getParentNameSpace().var_table.get(value);
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
