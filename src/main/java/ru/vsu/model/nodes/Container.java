package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;

public class Container extends ExecNode{
    public SomeType execute() throws Exception {
        return null;
    }

    public String GenerateCode() throws Exception {
        return null;
    }

    public String toString() {
        return children.toString();
    }
}
