package ru.vsu.model.nodes;

import ru.vsu.model.abstracts.ExecNode;

public class SomeType extends ExecNode {
    public Object execute() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
