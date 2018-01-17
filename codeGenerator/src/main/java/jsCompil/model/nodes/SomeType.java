package jsCompil.model.nodes;

import jsCompil.model.abstracts.ExecNode;
import jsCompil.utils.ToBooleanConverter;

public class SomeType extends ExecNode {
    public SomeType execute() {
        return this;
    }

    public String generateCode() throws Exception {
        String val;
        if(value instanceof String){
            val="\""+value+"\"";
        }
        else{
            val=value.toString();
        }

        return String.format("%d: push %s\n", Pointer++, val);
    }

    public SomeType add(SomeType type2) {
        Object op1 = value;
        Object op2 = type2.getValue();

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new SomeType(((Number) op1).floatValue() + ((Number) op2).floatValue());
        } else {
            return new SomeType(op1.toString() + op2.toString());
        }
    }

    public SomeType sub(SomeType type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.getValue();

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new SomeType(((Number) op1).floatValue() - ((Number) op2).floatValue());
        } else {
            throw new Exception("Unable to substitute non-numbers");
        }
    }

    public SomeType div(SomeType type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.getValue();

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new SomeType(((Number) op1).floatValue() / ((Number) op2).floatValue());
        } else {
            throw new Exception("Unable to divide non-numbers");
        }
    }

    public SomeType mod(SomeType type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.getValue();

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new SomeType(((Number) op1).floatValue() % ((Number) op2).floatValue());
        } else {
            throw new Exception("Unable to % non-numbers");
        }
    }

    public SomeType mul(SomeType type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.getValue();

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new SomeType(((Number) op1).floatValue() * ((Number) op2).floatValue());
        } else {
            throw new Exception("Unable to multiply non-numbers");
        }
    }

    public SomeType and(SomeType type2) {
        Object op1 = value;
        Object op2 = type2.value;

        op1 = ToBooleanConverter.convert(op1);
        op2 = ToBooleanConverter.convert(op2);

        return new SomeType((Boolean) op1 && (Boolean) op2);
    }

    public SomeType or(SomeType type2) {
        Object op1 = value;
        Object op2 = type2.value;

        op1 = ToBooleanConverter.convert(op1);
        op2 = ToBooleanConverter.convert(op2);

        return new SomeType((Boolean) op1 || (Boolean) op2);
    }

    public SomeType eq(SomeType type2) {
        Object op1 = value;
        Object op2 = type2.value;

        return new SomeType(op1.equals(op2));
    }

    public SomeType neq(SomeType type2) {
        Object op1 = value;
        Object op2 = type2.value;

        return new SomeType(!op1.equals(op2));
    }

    public SomeType gt(SomeType type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.value;

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new SomeType(((Number) op1).floatValue() > ((Number) op2).floatValue());
        } else if (op1 instanceof String && op2 instanceof String) {
            return new SomeType(((String) op1).compareTo((String) op2) > 0);
        } else {
            throw new Exception("Unable to compare non-numbers and non-strings");
        }
    }

    public SomeType ge(SomeType type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.value;

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new SomeType(((Number) op1).floatValue() >= ((Number) op2).floatValue());
        } else if (op1 instanceof String && op2 instanceof String) {
            return new SomeType(((String) op1).compareTo((String) op2) >= 0);
        } else {
            throw new Exception("Unable to compare non-numbers and non-strings");
        }
    }

    public SomeType lt(SomeType type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.value;

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new SomeType(((Number) op1).floatValue() < ((Number) op2).floatValue());
        } else if (op1 instanceof String && op2 instanceof String) {
            return new SomeType(((String) op1).compareTo((String) op2) < 0);
        } else {
            throw new Exception("Unable to compare non-numbers and non-strings");
        }
    }

    public SomeType le(SomeType type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.value;

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new SomeType(((Number) op1).floatValue() <= ((Number) op2).floatValue());
        } else if (op1 instanceof String && op2 instanceof String) {
            return new SomeType(((String) op1).compareTo((String) op2) <= 0);
        } else {
            throw new Exception("Unable to compare non-numbers and non-strings");
        }
    }

    @Override
    public void setValue(Object val) {
        if (val instanceof Number) {
            //integer
            if (((Number) val).floatValue() % 1 == 0) {
                value = ((Number) val).intValue();
            } else {
                value = ((Number) val).floatValue();
            }
        } else if (val instanceof SomeType)
            value = ((SomeType) val).getValue();
        else {
            value = val;
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public SomeType(Object val) {
        setValue(val);
    }
}
