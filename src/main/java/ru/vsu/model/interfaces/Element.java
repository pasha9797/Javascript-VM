package ru.vsu.model.interfaces;

import java.util.List;

public interface Element {
    int type = 0;
    Element parent = null;
    List<Element> children = null;
    Object value=null;

    Object proceed() throws Exception;
}
