package ru.vsu.model.nodes.logical;

import ru.vsu.model.abstracts.ExecNode;

public class LogicKeyword extends ExecNode {
    public Object execute() throws Exception {
        return Boolean.valueOf(value.toString());
    }

    public String toString() {
        return value.toString();
    }
}
