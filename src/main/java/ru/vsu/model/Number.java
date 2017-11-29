package ru.vsu.model;

import ru.vsu.model.interfaces.Element;

public class Number implements Element{
    public Object proceed() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
