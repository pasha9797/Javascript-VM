package jsVM.model.args;

import jsCompil.utils.ToBooleanConverter;

public class ConstantArgument implements Argument{

    private Object value;

    public Object getValue() {
        return value;
    }

    public ConstantArgument getConstantArg() {
        return this;
    }

    public ConstantArgument(Object val){
            if (val instanceof Number) {
                //integer
                if (((Number) val).floatValue() % 1 == 0) {
                    value = ((Number) val).intValue();
                } else {
                    value = ((Number) val).floatValue();
                }
            } else if (val instanceof ConstantArgument)
                value = ((ConstantArgument) val).getValue();
            else {
                value = val;
            }
    }

    public ConstantArgument add(ConstantArgument type2) {
        Object op1 = value;
        Object op2 = type2.getValue();

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new ConstantArgument(((Number) op1).floatValue() + ((Number) op2).floatValue());
        } else {
            return new ConstantArgument(op1.toString() + op2.toString());
        }
    }

    public ConstantArgument sub(ConstantArgument type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.getValue();

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new ConstantArgument(((Number) op1).floatValue() - ((Number) op2).floatValue());
        } else {
            throw new Exception("Unable to substitute non-numbers");
        }
    }

    public ConstantArgument div(ConstantArgument type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.getValue();

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new ConstantArgument(((Number) op1).floatValue() / ((Number) op2).floatValue());
        } else {
            throw new Exception("Unable to divide non-numbers");
        }
    }

    public ConstantArgument mod(ConstantArgument type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.getValue();

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new ConstantArgument(((Number) op1).floatValue() % ((Number) op2).floatValue());
        } else {
            throw new Exception("Unable to % non-numbers");
        }
    }

    public ConstantArgument mul(ConstantArgument type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.getValue();

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new ConstantArgument(((Number) op1).floatValue() * ((Number) op2).floatValue());
        } else {
            throw new Exception("Unable to multiply non-numbers");
        }
    }

    public ConstantArgument and(ConstantArgument type2) {
        Object op1 = value;
        Object op2 = type2.value;

        op1 = ToBooleanConverter.convert(op1);
        op2 = ToBooleanConverter.convert(op2);

        Boolean result=(Boolean) op1 && (Boolean) op2;
        return new ConstantArgument(result);
    }

    public ConstantArgument or(ConstantArgument type2) {
        Object op1 = value;
        Object op2 = type2.value;

        op1 = ToBooleanConverter.convert(op1);
        op2 = ToBooleanConverter.convert(op2);

        Boolean result=(Boolean) op1 || (Boolean) op2;
        return new ConstantArgument(result);
    }

    public ConstantArgument eq(ConstantArgument type2) {
        Object op1 = value;
        Object op2 = type2.value;

        return new ConstantArgument(op1.equals(op2));
    }

    public ConstantArgument gt(ConstantArgument type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.value;

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new ConstantArgument(((Number) op1).floatValue() > ((Number) op2).floatValue());
        } else if (op1 instanceof String && op2 instanceof String) {
            return new ConstantArgument(((String) op1).compareTo((String) op2) > 0);
        } else {
            throw new Exception("Unable to compare non-numbers and non-strings");
        }
    }

    public ConstantArgument ge(ConstantArgument type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.value;

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new ConstantArgument(((Number) op1).floatValue() >= ((Number) op2).floatValue());
        } else if (op1 instanceof String && op2 instanceof String) {
            return new ConstantArgument(((String) op1).compareTo((String) op2) >= 0);
        } else {
            throw new Exception("Unable to compare non-numbers and non-strings");
        }
    }

    public ConstantArgument lt(ConstantArgument type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.value;

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new ConstantArgument(((Number) op1).floatValue() < ((Number) op2).floatValue());
        } else if (op1 instanceof String && op2 instanceof String) {
            return new ConstantArgument(((String) op1).compareTo((String) op2) < 0);
        } else {
            throw new Exception("Unable to compare non-numbers and non-strings");
        }
    }

    public ConstantArgument le(ConstantArgument type2) throws Exception {
        Object op1 = value;
        Object op2 = type2.value;

        if (op1 instanceof java.lang.Number && op2 instanceof java.lang.Number) {
            return new ConstantArgument(((Number) op1).floatValue() <= ((Number) op2).floatValue());
        } else if (op1 instanceof String && op2 instanceof String) {
            return new ConstantArgument(((String) op1).compareTo((String) op2) <= 0);
        } else {
            throw new Exception("Unable to compare non-numbers and non-strings");
        }
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
