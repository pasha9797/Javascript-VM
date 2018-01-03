package ru.vsu.model.nodes.array;

import ru.vsu.model.abstracts.ExecNode;
import ru.vsu.model.nodes.SomeType;
import ru.vsu.utils.VarNameGenerator;

import java.util.ArrayList;
import java.util.List;

public class Array_Decl extends ExecNode{
    public SomeType execute() throws Exception {
        return new SomeType(getList());
    }

    public List getList() throws Exception{
        List<Object> arr = new ArrayList<Object>();
        for(ExecNode ex : children){
            arr.add(ex.execute());
        }
        return arr;
    }

    public String GenerateCode() throws Exception {
        String varName = VarNameGenerator.generateName(
                getList()
        );
        String s= String.format("%d: decl %s\n", Pointer++, varName);
        int i=0;
        for(ExecNode ex : children){
            s+=String.format("%s%d: popToArray %s %d\n", ex.GenerateCode(), Pointer++, varName, i);
            i++;
        }
        return s+String.format("%d: pushFromVar %s\n", Pointer++,varName);
    }
    public String toString() {
        return children.toString();
    }
}
