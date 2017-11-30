package ru.vsu.model;

import ru.vsu.model.abstracts.Element;

public class Number extends Element{
    public Object proceed() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
