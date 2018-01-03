package ru.vsu.model.nodes;

import ru.vsu.utils.ToBooleanConverter;
import ru.vsu.model.abstracts.ExecNode;

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

    public String GenerateCode() throws Exception {
        String code;
        String predCode=pred.GenerateCode();
        int cmdID1=Pointer++;
        String trueCode = caseTrue.GenerateCode();
        int jumpFalse;
        String falseCode="";
        int jumpEnd;
        int cmdID2;
        if(children.size()>2) {
            cmdID2=Pointer++;
            jumpFalse = Pointer;
            code = "\n%s%d: jumpFalse %d\n%s%d: jump %d\n%s\n";
            falseCode = caseFalse.GenerateCode();
            jumpEnd=Pointer;
            return String.format(code, predCode, cmdID1, jumpFalse, trueCode, cmdID2, jumpEnd, falseCode);
        }
        else {
            jumpFalse = Pointer;
            code = "\n%s%d: jumpFalse %d\n%s\n";
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
