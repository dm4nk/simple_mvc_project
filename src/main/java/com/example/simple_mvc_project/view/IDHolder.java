package com.example.simple_mvc_project.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IDHolder {
    private Integer id;
    private static final IDHolder INSTANCE = new IDHolder();

    private IDHolder() {

    }

    public static IDHolder getInstance() {
        return INSTANCE;
    }
}
