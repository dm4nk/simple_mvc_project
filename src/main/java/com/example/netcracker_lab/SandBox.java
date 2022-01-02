package com.example.netcracker_lab;

import java.util.List;

public class SandBox {
    public static void main(String[] args) {
        String s = "bad";
        List<Object> objectList = SingleFieldHandler.find(s);

        objectList.forEach(System.out::println);
    }
}
