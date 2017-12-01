package ru.vsu.model.nodes.logical;

import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.model.abstracts.Logical;

public class Or extends Logical {
    public Object execute() throws Exception {
        Object op1 = executeChild(0);
        Object op2 = executeChild(1);

        op1 = ToBooleanConverter.convert(op1);
        op2 = ToBooleanConverter.convert(op2);

        return (Boolean) op1 || (Boolean) op2;
    }

    @Override
    public String toString() {
        try {
            return "(" + children.get(0).toString() + "||" + children.get(1).toString() + ")";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
