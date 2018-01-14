package ru.vsu.js.model.nodes;

import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.js.model.abstracts.ExecNode;

public class If extends ExecNode {
    ExecNode pred, caseTrue, caseFalse;
    public SomeType execute() throws Exception {
        pred=children.get(0);
        caseTrue=children.get(1);

        Boolean predicate = ToBooleanConverter.convert(pred.execute().getValue());
        if(predicate){
            return caseTrue.execute();
        }
        else if(children.size()>2){
            caseFalse=children.get(2);
            return caseFalse.execute();
        }
        return null;
    }

    public String generateCode() throws Exception {
        String code;
        String predCode=pred.generateCode();
        int cmdID1=Pointer++;
        String trueCode = caseTrue.generateCode();
        int jumpFalse;
        String falseCode="";
        int jumpEnd;
        int cmdID2;
        if(children.size()>2) {
            cmdID2=Pointer++;
            jumpFalse = Pointer;
            code = "\t//If expression begin\n%s%d: jumpFalse %d\n%s%d: jump %d\n%s\t//If expression end\n";
            falseCode = caseFalse.generateCode();
            jumpEnd=Pointer;
            return String.format(code, predCode, cmdID1, jumpFalse, trueCode, cmdID2, jumpEnd, falseCode);
        }
        else {
            jumpFalse = Pointer;
            code = "\t//If expression begin\n%s%d: jumpFalse %d\n%s\t//If expression end\n";
            return String.format(code, predCode, cmdID1, jumpFalse, trueCode);
        }
    }

    public String toString() {
        String str;
        str = "if("+children.get(0)+")\n"+children.get(1);
        if(children.size()>2){
            str+="\nelse\n"+children.get(2);
        }
        return str;
    }
}
