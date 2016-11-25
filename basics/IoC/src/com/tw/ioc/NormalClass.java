package com.tw.ioc;

public class NormalClass {
    private Dependency dependency;

    public NormalClass(Dependency dependency) {
        this.dependency = dependency;
    }

    String NormalMethod() {
        return dependency.depMethod();
    }
}
