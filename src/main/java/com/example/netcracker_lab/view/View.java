package com.example.netcracker_lab.view;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class View {
    static View instance = new View();

    private View() {
    }

    public static View getInstance() {
        return instance;
    }
}
