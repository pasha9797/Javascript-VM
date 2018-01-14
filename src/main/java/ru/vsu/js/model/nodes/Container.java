package ru.vsu.js.model.nodes;

import ru.vsu.js.model.abstracts.ExecNode;

public class Container extends ExecNode {
    public SomeType execute() throws Exception {
        return null;
    }

    public String generateCode() throws Exception {
        return null;
    }

    public String toString() {
        return children.toString();
    }
}
