package com.tw.ioc;

public class GeneracMethods {

    public <T> void f(T a, Integer b) {
        System.out.println(a.getClass() + " " + b.getClass());
    }

    public static void main(String[] args) {
        new GeneracMethods().f("dsfas", 1);
    }
}
