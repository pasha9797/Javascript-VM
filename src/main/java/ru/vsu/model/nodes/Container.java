package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;

public class Container extends ExecNode{
    public Object execute() throws Exception {
        return null;
    }

    public String toString() {
        return children.toString();
    }
}
